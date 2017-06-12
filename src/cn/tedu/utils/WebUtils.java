package cn.tedu.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class WebUtils {
	/**
	 * 判断字符串是否为空或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return (str == null||"".equals(str.trim()));
	}
	/**
	 * 使用MD5算法进行加密
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText){
		byte[] secretBytes =null;
		try {
			secretBytes=MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("没有MD5这个算法");
		}
		String md5code=new BigInteger(1,secretBytes).toString(16);
		for(int i=0;i<32-md5code.length();i++){
			md5code="0"+md5code;
		}
		return md5code;
	}
}
