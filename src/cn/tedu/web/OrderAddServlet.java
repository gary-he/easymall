package cn.tedu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Order;
import cn.tedu.domain.Product;
import cn.tedu.domain.User;
import cn.tedu.domain.orderItem;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderAddServlet extends HttpServlet {
	private OrderService service=BasicFactory.getFactory().getInstance(OrderService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session中获取用户信息
		User user=(User) request.getSession().getAttribute("user");
		//未登录转发到登录页面，已登录获取用户id
		if(user==null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		int userId=user.getId();
		//从session中获取cart
		Map<Product, Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		//cart为null怎重定向至首页
		if(cart==null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		//定义order,oiList
		Order order=new Order();
		List<orderItem> oiList=new ArrayList<orderItem>();
		//封装数据
		order.setId(UUID.randomUUID().toString());//随机生成唯一的订单号
		order.setOrderTime(new Date());//记录订单提交时间
		order.setPayState(0);//设置订单状态
		order.setReceiverInfo(request.getParameter("receiverInfo"));//设置收货人详细信息
		order.setUserId(userId);//添加订单用户名
		double money=0;//定义订单金额
		//遍历购物车，将每件商品与当前订单关联起来，封装到OrderItem，并存入list
		for(Map.Entry<Product, Integer> entry:cart.entrySet()){
			orderItem oi=new orderItem();//实例化订单项javabean
			oi.setOrderId(order.getId());//设置当前订单id
			oi.setProdId(entry.getKey().getId());//设置当前循环的商品id
			oi.setBuyNum(entry.getValue());//设置当前商品的购买数量
			money+=entry.getKey().getPrice()*entry.getValue();//金额小计
			oiList.add(oi);//将订单项添加到list
		}
		order.setMoney(money);//设置订单金额
		try {
			//调用service层的方法添加订单
			service.addOrder(order,oiList);
			//添加成功，提示信息并跳转至订单页面
			response.getWriter().write("订单提交成功！3秒后转跳至订单页面。");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
		} catch (MsgException e) {
			//添加失败，将信息存入request域中，转发到cart.jsp页面进行提示
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
