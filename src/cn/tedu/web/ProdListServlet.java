package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.Product;
import cn.tedu.factory.BasicFactory;
import cn.tedu.service.ProdService;

public class ProdListServlet extends HttpServlet {
	private ProdService service=BasicFactory.getFactory().getInstance(ProdService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数(商品名称，商品分类，最低价格，最高价格)
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		String minPrice=request.getParameter("minprice");
		String maxPrice=request.getParameter("maxprice");
		
		//为搜索条件设置默认值
		String _name="";
		String _category="";
		double _minPrice=0;
		double _maxPrice=Double.MAX_VALUE;
		//检查搜索条件是否合法
		if(name!=null&&!"".equals(name.trim())){
			_name=name;
		}
		if(category!=null&&!"".equals(category.trim())){
			_category=category;
		}
		String reg="^\\d+$";
		if(minPrice!=null&&!"".equals(minPrice.trim())&&minPrice.matches(reg)){
			_minPrice=Double.parseDouble(minPrice);
		}
		if(maxPrice!=null&&!"".equals(maxPrice.trim())&&maxPrice.matches(reg)){
			//最高价要大于等于最低价
			if(Double.parseDouble(maxPrice)>=_minPrice){
				_maxPrice=Double.parseDouble(maxPrice);
			}
		}
		//调用service层的findAll()方法，根据条件查询所有符合条件的商品组成的list集合
		List<Product> prodList=service.findAll(_name,_category,_minPrice,_maxPrice);
		//将商品list存入request域
		request.setAttribute("prodList", prodList);
		//转发到prod_list.jsp进行显示
		request.getRequestDispatcher("/prod_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
