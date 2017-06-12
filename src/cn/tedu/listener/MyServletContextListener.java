package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		//在ServletContext对象创建之后将web应用的url存入该域中
		//获取ServletContext域
		ServletContext context=sce.getServletContext();
		//获取web应用的虚拟路径
		String contextPath=context.getContextPath();
		//存入SevletContext域中
		context.setAttribute("app", contextPath);
		
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
