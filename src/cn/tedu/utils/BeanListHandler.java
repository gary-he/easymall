package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 将结果集中的多行记录封装成多个javabean对象，存入list并返回,若没有记录则返null
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public class BeanListHandler<T> implements ResultSetHandler<List<T>> {
	// 接收要封装数据的javabean类的字节码对象
	private Class clz;

	public BeanListHandler(Class clz) {
		this.clz = clz;
	}

	/**
	 * 将结果集中的多行记录封装成多个javabean对象，存入list并返回
	 */
	public List<T> handler(ResultSet rs) throws Exception {
		T t = null;
		List<T> list=new ArrayList<T>();
		while (rs.next()) {
			t = (T) clz.newInstance();
			// 解析Class对象，获知Class对象所属的类中有哪些属性及公共方法，封装进BeanInfo对象中
			// 用到工具类Introspector的getBeanInfo（Class clz）方法
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);

			// 调用BeanInfo的getPropertyDescriptor方法
			// 返回每组属性及方法组成的PropertyDescriptor
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				// 获取当前组中的属性
				String name = pd.getName();
				// 获取当前组中的set方法
				Method method = pd.getWriteMethod();
				try {
					// 调用set方法将结果集中对应的列的值封装进javabean中对应的属性
					method.invoke(t, rs.getObject(name));
				} catch (Exception e) {
					// 如果遍历到得属性在rs中没有对应的列，则跳过
					// pds中还封装了从父类（Object）中继承的属性和方法
					continue;
				}
			}
			//将javabean对象存入list
			list.add(t);
		}
		return list.size()==0?null:list;
	}
}
