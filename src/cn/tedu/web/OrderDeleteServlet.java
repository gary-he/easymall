package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.OrderService;

public class OrderDeleteServlet extends HttpServlet {
	private OrderService service=BasicFactory.getFactory().getInstance(OrderService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收订单id
		String oid=request.getParameter("oid");
		System.out.println(oid);
		//调用业务层订单删除的方法
		try {
			service.deleteOrderInfoByOid(oid);
			//提示删除成功
			response.getWriter().write("删除成功！，3秒后自动转跳，如果没有转跳请<a href='"+request.getContextPath()+"/OrderListServlet'>点击此处</a>");
		} catch (MsgException e) {
			//提示删除失败
			response.getWriter().write(e.getMessage()+"3秒后自动转跳，如果没有转跳请<a href='"+request.getContextPath()+"/OrderListServlet'>点击此处</a>");
		}
		//设置自动转跳
		response.setHeader("Refresh", "3;url="+request.getContextPath()+"/OrderListServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
