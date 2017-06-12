package cn.tedu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropUtils {
	private static Properties prop=new Properties();
	private PropUtils(){
		
	}
	static{
		try {
			//通过类加载器加载资源文件的硬盘路径
			String path=PropUtils.class.getClassLoader().getResource("conf.properties").getPath();
			//加载配置文件
			prop.load(new FileInputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取配置文件对应键的值的工具方法
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	
}
