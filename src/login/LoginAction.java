package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class LoginAction extends HttpServlet{
	LoginService ls;
	public LoginAction(){
		super();
	}
	@Override
	public void init(){
		ls=new LoginDao();
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path=request.getContextPath();
		String username=request.getParameter("username");
		String pw=request.getParameter("password");
		boolean flag=ls.login(new String[]{username, pw});
		if(flag){
			request.getSession().setAttribute("username", username);
			response.sendRedirect(path+"/main.jsp");
		}else{
			response.sendRedirect(path+"/index.jsp");
		}
	}
}
