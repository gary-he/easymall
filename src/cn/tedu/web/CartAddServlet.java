package cn.tedu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class CartAddServlet extends HttpServlet {
	private ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收商品id
		String pid=request.getParameter("pid");
		//从session中获取cart
		Map<Product, Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new HashMap<Product, Integer>();
			request.getSession().setAttribute("cart", cart);
		}
		//调用service层的方法根据id获取商品对象
		Product prod=service.findProdById(pid);
		//将prod保存到cart中
		if(cart.containsKey(prod)){
			cart.put(prod, cart.get(prod)+1);
		}else{
			cart.put(prod, 1);
		}
		//转发到cart.jsp
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
