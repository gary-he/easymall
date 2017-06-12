package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class AjaxCheckUsernameServlet extends HttpServlet {
	private UserService service=BasicFactory.getFactory().getInstance(UserService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理乱码问题
		// ServletContext sContext=this.getServletContext();
		// String encoding=sContext.getInitParameter("encoding");
		// request.setCharacterEncoding(encoding);
		// response.setContentType("text/html;charset="+encoding);
		//获取请求参数
		String username=request.getParameter("username");
		
		//调用service层的方法查询用户名是否存在并作出回应
		if(service.hasUser(username)){
			response.getWriter().write("用户名已存在！");
		}else{
			response.getWriter().write("恭喜你，用户名可以使用！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
