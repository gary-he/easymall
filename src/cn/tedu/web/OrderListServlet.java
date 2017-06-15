package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.OrderInfo;
import cn.tedu.domain.User;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderListServlet extends HttpServlet {
	private OrderService service=BasicFactory.getFactory().getInstance(OrderService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取session中的用户信息
		//判断是否登录
		if(request.getSession()==null||request.getSession().getAttribute("user")==null){
			//未登录，转跳到登录页面
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//已登录，获取用户信息
		User user=(User)request.getSession().getAttribute("user");
		//获取用户id
		int uid=user.getId();
		//调用service层的方法获取对应id的所有订单详情，封装到list中
		List<OrderInfo> oinList =service.getOrderInfoByUserId(uid);
		//将结果存进request域中
		request.setAttribute("oinList", oinList);
		//转发到order_list.jsp页面显示数据
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
