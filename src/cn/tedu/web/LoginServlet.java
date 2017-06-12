package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.utils.WebUtils;

public class LoginServlet extends HttpServlet {
	private UserService service = BasicFactory.getFactory().getInstance(UserService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 处理乱码问题
		// request.setCharacterEncoding("utf-8");

		// 获取请求参数
		String username = request.getParameter("username");
		String password = WebUtils.md5(request.getParameter("password"));
		String remname = request.getParameter("remname");
		String autologin=request.getParameter("autologin");

		// 处理是否记住用户名
		if ("true".equals(remname)) {// 记住用户名则发送包含用户名的cookie
			Cookie remnameCookie = new Cookie("user", URLEncoder.encode(
					username, "utf-8"));
			remnameCookie.setMaxAge(3600 * 24 * 30);// 记住30天
			remnameCookie.setPath(request.getContextPath() + "/");
			response.addCookie(remnameCookie);// 向浏览器发送记住用户名的cookie
		} else {// 不记住用户名则发送同名cookie，将生存时间设置为0。，覆盖掉之前的（不管有无）
			Cookie remnameCookie = new Cookie("user", "");
			remnameCookie.setMaxAge(0);
			remnameCookie.setPath(request.getContextPath() + "/");
			response.addCookie(remnameCookie);
		}
		
		//处理是否30天自动登录
		if("true".equals(autologin)){
			Cookie autoLoginCookie=new Cookie("autologin", URLEncoder.encode(
					username, "utf-8")+":"+password);
			autoLoginCookie.setMaxAge(3600*24*30);
			autoLoginCookie.setPath(request.getContextPath()+"/");
			response.addCookie(autoLoginCookie);
		}

		// 调用service层的方法根据用户名和密码查询用户
		User user = service.loginUser(username, password);
		// 根据返回结果控制用户登录是否成功
		if (user == null) {
			request.setAttribute("msg", "用户名或密码不正确");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			// 在session中保存登录标记
			request.getSession().setAttribute("user", user);
			// 登录成功，重定向回首页
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
