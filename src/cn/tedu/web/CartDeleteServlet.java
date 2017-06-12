package cn.tedu.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;

public class CartDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收商品id
		String pid=request.getParameter("pid");
		//从session中获取cart
		Map<Product, Integer> cart=(Map<Product, Integer>) request.getSession().getAttribute("cart");
		//判断cart是否为null
		if(cart==null){
			//为null，session已经失效，转发到index.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			//return返回,防止出现IllegalStateException异常(多次转发导致)
			return;
		}
		//不为null，从cart中删除对应id的商品
		//创建对应id的prod对象
		Product prod=new Product();
		prod.setId(pid);
		//删除
		cart.remove(prod);
		//转发到cart.jsp
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
