package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxProdDelServlet extends HttpServlet {
	private ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取商品id
		String pid=request.getParameter("pid");
		//调用prodservice层的delProdById方法
		boolean result=service.delProdById(pid);
		//响应
		response.getWriter().write(result+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
