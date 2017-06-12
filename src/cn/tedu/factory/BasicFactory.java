package cn.tedu.factory;

import cn.tedu.utils.PropUtils;

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
	 * 根据配置文件中的配置项生产UserDao实例
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
			// 通过字节码对象返回UserDao实例对象
			return (T) clz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
