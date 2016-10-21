package login;

import java.sql.SQLException;
import java.util.Map;

import sql.query;

public class LoginDao implements LoginService{
	query que;
	public LoginDao(){
		que=new query();
	}
	@Override
	public boolean login(String[] params){
		boolean flag=false;
		String sql="select * from album_diary where name=? and password=?";
		try {
			Map<String, Object> map=que.getResult(sql, params);
			flag=map.isEmpty()?false:true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			que.releaseCon();
		}
		return flag;
	}
}
