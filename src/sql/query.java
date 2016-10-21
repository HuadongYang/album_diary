package sql;

import java.sql.*;
import java.util.*;

public class query {
	private final String USERNAME="root";
	private final String PASSWORD="123456";
	private final String DRIVER="com.mysql.jdbc.Driver";
	private final String URL="jdbc:mysql://localhost:3306/mysql";
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	private Statement stmt;
	public query(){
		
			try {
				Class.forName(DRIVER);
				connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
				System.out.println("驱动成功");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("驱动失败");
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	

	public Map<String, Object> getResult(String sql, String[] params) throws SQLException{
		Map<String, Object> map=new HashMap<String, Object>();
		pstmt=connection.prepareStatement(sql);
		if(params!=null && sql!=null){
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
		}
		resultSet=pstmt.executeQuery();
		ResultSetMetaData metaData = pstmt.getMetaData();
		int cols_len = metaData.getColumnCount();
		while(resultSet.next()){
			for(int i=0;i<cols_len;i++){
				String col_name=metaData.getColumnClassName(i+1);
				Object col_value=resultSet.getObject(col_name);
				if(col_value==null)
					col_value="";
				map.put(col_name, col_value);
			}
		}
		return map;
	}
	public void releaseCon(){
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		if(stmt!=null){
			
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
