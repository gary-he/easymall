package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.UserService;
import cn.tedu.utils.WebUtils;



public class RegistServlet extends HttpServlet {
	private UserService service=BasicFactory.getFactory().getInstance(UserService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//解决乱码问题
			// request.setCharacterEncoding("utf-8");
			// response.setContentType("text/html;charset=utf-8");
			//验证密码是否正确
			//获取用户验证码
			String valistr=request.getParameter("valistr");
			//从session中获取验证码文本
			String valiCode=(String) request.getSession().getAttribute("valiCode");
			if(!valistr.equalsIgnoreCase(valiCode)){
				//校验不通过，将提示消息存入request域
				request.setAttribute("msg", "验证码不正确");
				//通过转发转跳到注册页，并提取消息惊醒提示
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}
			//将数据封装进javabean
			User user=new User();
			//利用BeanUtiles工具类封装数据到javabean
			//populate(bean,map),第二个参数传入参数组成的map，用getParameterMap
			BeanUtils.populate(user, request.getParameterMap());
			
			//调用javabean中的方法校验数据
			user.checkData();
			
			//调用service层的方法进行注册
			//用MD5算法加密用户密码
			user.setPassword(WebUtils.md5(user.getPassword()));
			service.registUser(user);
			//注册成功，3秒之后跳回首页
			response.getWriter().write("<h1 style='color:red;text-align:center'>恭喜您注册成功, 3秒之后跳转回首页......</h1>");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
		} catch (MsgException e) {
			//获取异常提示消息，存入request域
			request.setAttribute("msg",e.getMessage());
			//转发到regist.jsp，取出消息进行提示
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
