package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;

public class CartEditServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收参数
		String pid=request.getParameter("pid");
		int newNum=Integer.parseInt(request.getParameter("newNum"));
		//从session中获取cart
		Map<Product, Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		//判断cart是否为null
		if(cart==null){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		//不为null，更新cart中对应id的商品数量
		Product prod=new Product();
		prod.setId(pid);
		cart.put(prod, newNum);
		//转发到cart.jsp更新显示
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
