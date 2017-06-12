package cn.tedu.factory;

import cn.tedu.dao.UserDao;
import cn.tedu.utils.PropUtils;
/**
 * 已过时
 * UserDao接口的工厂类，UserService接口也要一个类似的工厂类，所以设计了BasicFactory工厂类代替
 * @author Administrator
 * @deprecated
 */
public class UserDaoFactory {
	//单例模式设计--饿汉式
	private static UserDaoFactory factory= new UserDaoFactory();

	public UserDaoFactory() {
		super();
	}
	
	//对外公开的方法获取工厂实例
	public static UserDaoFactory getFactory(){
		return factory;
	}
	
	//根据配置文件中的配置项生产UserDao实例
	public UserDao getInstance(){
		try {
			//利用工具类PropUtils读取配置文件的内容
			String classname=PropUtils.getProperty("UserDao");
			//通过反射获取类的字节码对象
			Class clz=Class.forName(classname);
			//通过字节码对象返回UserDao实例对象
			return (UserDao)clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
