package cn.tedu.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class AjaxProdPnumEditServlet extends HttpServlet {
	private ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收请求参数，获取商品id和新的商品库存数量
		String pid=request.getParameter("pid");
		int pnum=Integer.parseInt(request.getParameter("pnum"));
		//调用ProdService层的updateProdNum方法，更新指定id的商品数量
		boolean result=service.updatePnum(pid, pnum);
		//作出相应 true--修改成功，false--修改失败
		response.getWriter().write(result+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
