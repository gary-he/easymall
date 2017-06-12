package cn.tedu.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
/**
 * 将结果集的第一行数据封装成一个javabean对象并返回，若没有结果则返回null
 * 泛型参数的类型由创建对象时传入的javabean类型决定
 * @author Administrator
 *
 * @param <T>
 */
public class BeanHandler<T> implements ResultSetHandler<T> {
	//接收要封装数据的javabean类的字节码对象
	private Class clz;
	public BeanHandler(Class clz) {
		this.clz=clz;
	}
	/**
	 * 将结果集中的第一行记录封装成一个javabean对象并返回，如果没有记录则返回null
	 */
	public T handler(ResultSet rs) throws Exception {
		T t=null;
		if(rs.next()){
			t=(T) clz.newInstance();
			//解析Class对象，获知Class对象所属的类中有哪些属性及公共方法，封装进BeanInfo对象中
			//用到工具类Introspector的getBeanInfo（Class clz）方法
			BeanInfo beanInfo=Introspector.getBeanInfo(clz);
			
			//调用BeanInfo的getPropertyDescriptor方法
			//返回每组属性及方法组成的PropertyDescriptor
			PropertyDescriptor[] pds=beanInfo.getPropertyDescriptors(); 
			for(PropertyDescriptor pd:pds){
				//获取当前组中的属性
				String name=pd.getName();
				//获取当前组中的set方法
				Method method=pd.getWriteMethod();
				try {
					//调用set方法将结果集中对应的列的值封装进javabean中对应的属性
					method.invoke(t, rs.getObject(name));
				} catch (Exception e) {
					//如果遍历到得属性在rs中没有对应的列，则跳过
					//pds中还封装了从父类（Object）中继承的属性和方法
					continue;
				}
			}
		}
		return t;
	}
	
}
