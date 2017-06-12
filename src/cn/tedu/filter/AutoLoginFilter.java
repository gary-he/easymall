package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.tedu.domain.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter {
	private UserService service=BasicFactory.getFactory().getInstance(UserService.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("自动登录过滤器初始化成功...");
	}
	/**
	 * 实现30天自动登录
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest) request;
		//用户是未登录状态
		if(req.getSession().getAttribute("user")==null){
			//获取名为autologin的cookie
			Cookie[] cookies=req.getCookies();
			Cookie autoLoginCookie=null;
			if(cookies!=null){
				for(Cookie c:cookies){
					if("autologin".equals(c.getName())){
						autoLoginCookie=c;
					}
				}
			}
			//判断autologin是否为空，必须要有autologin Cookie才能自动登录
			if(autoLoginCookie!=null){
				//取出用户名和密码字符串进行分割,在进行URL解码，存入字符串数组
				@SuppressWarnings("deprecation")
				String[] autoLoginMsg=URLDecoder.decode(autoLoginCookie.getValue(),"utf-8").split(":");
				String username=autoLoginMsg[0];
				String password=autoLoginMsg[1];
				//检查Cookie中的用户名和密码必须正确
				User user=service.loginUser(username, password);
				if(user!=null){
					//实现自动登录
					req.getSession().setAttribute("user", user);
				}
			}
			
		}
		
		//!!!无论是否实现自动登录，一定要放行过滤器
		chain.doFilter(req, response);
	}

	public void destroy() {
		
	}

}
