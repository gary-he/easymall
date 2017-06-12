package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("全站乱码解决过滤器初始化成功...");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1.解决响应正文乱码
		response.setContentType("text/html;charset=utf-8");
		//2.解决请求参数乱码--（利用装饰设计模式对request对象进行包装）
		HttpServletRequest myReq=new MyHttpServletRequest((HttpServletRequest)request);
		//3.放行过滤器
		chain.doFilter(myReq, response);
	}
	public void destroy() {
		
	}
	
}
/**
 * 包装request对象
 * @author Administrator
 *
 */
class MyHttpServletRequest extends HttpServletRequestWrapper{
	//将request对象保存在类的内部
	private HttpServletRequest request;
	//定义flag，控制getParameterMap（）方法中的map只会遍历一次
	private boolean flag=true;
	/**
	 * 通过HttpServletRequestWrapper类对request对象进行初步包装
	 * @param request
	 */
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	/**
	 * 获取单个参数值
	 */
	public String getParameter(String name){
		return getParameterValues(name)==null?null:getParameterValues(name)[0];
	}
	/**
	 * 获取由多个参数值组成的数组
	 */
	public String[] getParameterValues(String name){
		return (String[]) getParameterMap().get(name);
	}
	/**
	 * 包装request对象的getParameterMap（）方法，提供解决乱码的具体实现
	 */
	public Map<String, String[]> getParameterMap(){
		try {
			//获取请求方式
			String method =request.getMethod();
			//判断请求方式
			if("POST".equals(method)){
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			}else if("GET".equals(method)){
				//GET提交方式需要手动编解码解决乱码问题
				//先获取乱码的参数组成的map
				Map<String, String[]> map=request.getParameterMap();
				if(flag){
					for(Map.Entry<String, String[]> entry:map.entrySet()){
						String[] values=entry.getValue();
						for(int i=0;i<values.length;i++){
							values[i]=new String(values[i].getBytes("iso8859-1"),"utf-8");
						}
					}
					//遍历完map，更改标志，防止多次遍历map重复编解码
					flag=false;
				}
				return map;
			} else{
				return request.getParameterMap();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}


