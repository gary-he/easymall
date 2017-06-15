package cn.tedu.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.tedu.anno.Tran;
import cn.tedu.dao.Dao;
import cn.tedu.service.Service;
import cn.tedu.utils.PropUtils;
import cn.tedu.utils.TranManager;

/**
 * 各种接口的工厂类
 * 
 * @author Administrator
 * 
 */
public class BasicFactory {
	// 单例模式设计--饿汉式
	private static BasicFactory factory = new BasicFactory();

	public BasicFactory() {
		super();
	}

	/**
	 * 获取工厂实例
	 * @return
	 */
	public static BasicFactory getFactory() {
		return factory;
	}

	/**
	 * 
	 * 根据配置文件中的配置项生产实例
	 * 设计泛型方法，当调用时传入参数的类型确定返回值类型
	 * @param clazz
	 * @return
	 */
	public <T> T getInstance(Class<T> clazz) {
		try {
			// 利用工具类PropUtils读取配置文件的内容
			// 通过接口的字节码对象提取配置项中的“key”
			String classname = PropUtils.getProperty(clazz.getSimpleName());
			// 通过反射获取类的字节码对象
			Class clz = Class.forName(classname);
			//判断要实例化的对象是否是service层的对象
			if(Service.class.isAssignableFrom(clz)){
				//是service层的对象，返回动态代理对象
				//先创建委托对象
				final T t=(T) clz.newInstance();
				//创建动态代理对象
				@SuppressWarnings("unchecked")
				T proxy=(T) Proxy.newProxyInstance(clz.getClassLoader(), clz.getInterfaces(), new InvocationHandler() {
					//代理方法实现--对有tran注解的业务方法启用事务，对没有tran注解的业务方法正常调用
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						Object result=null;
						//判断业务方法上是否有添加Tran注解
						if(method.isAnnotationPresent(Tran.class)){
							//有Tran注解则启用事务
							try {
								//开启事务
								TranManager.startTran();
								//执行业务方法
								result=method.invoke(t, args);
								//提交事务
								TranManager.commitTran();
							} catch (InvocationTargetException ite) {//封装了自定义异常，捕获并重新抛出自定义异常
								//回滚
								TranManager.rollbackTran();
								//抛出自定义异常
								throw ite.getTargetException();
							} catch (Exception e) {
								//回滚
								TranManager.rollbackTran();
								e.printStackTrace();
							} finally{
								//关闭事务并还连接
								TranManager.release();
							}
						} else{
							//没有Tran注解则不启用事务
							try {
								result=method.invoke(t, args);
							} catch (InvocationTargetException ite) {
								//抛出自定义异常
								throw ite.getTargetException();
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								//还连接
								TranManager.release();
							}
						}
						return result;
					}
				});
				//返回代理对象
				return proxy;
			} else if(Dao.class.isAssignableFrom(clz)){
				//是Dao层对象
				//返回普通实例对象
				return (T) clz.newInstance();
				
			} else {
				System.out.println("工厂不生产这个类的实例哦，想生产的话该工厂方法代码吧。。。");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
