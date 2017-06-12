package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdInfoServlet extends HttpServlet {
	private ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取所查询商品的id
		String pid=request.getParameter("pid");
		//调用service层的findProdById方法查询指定id的商品信息
		Product prod=service.findProdById(pid);
		//将商品product对象存入request域中
		request.setAttribute("prod", prod);
		//转发回商品详情页面prod_info.jsp
		request.getRequestDispatcher("/prod_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
