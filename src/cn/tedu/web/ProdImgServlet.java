package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 浏览器无法直接访问WEB-INF目录下的资源，通过访问servlet，将请求转发给目标资源
 * @author Administrator
 *
 */
public class ProdImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取商品url
		String imgurl=request.getParameter("imgurl"); 
		//在服务器内部的servlet将请求转发到对应商品图片的资源上
		request.getRequestDispatcher(imgurl).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
