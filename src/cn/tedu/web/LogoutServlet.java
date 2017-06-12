package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//销毁session
		if(request.getSession(false)!=null){
			request.getSession().invalidate();
		}
		//销毁自动登录cookie
		Cookie autoLoginCookie=new Cookie("autologin", "");
		autoLoginCookie.setMaxAge(0);
		autoLoginCookie.setPath(request.getContextPath()+"/");
		response.addCookie(autoLoginCookie);
		//转发到login页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
