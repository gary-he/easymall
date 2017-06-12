package cn.tedu.utils;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {
	/**
	 * 将结果集封装成一个object并返回
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	T handler(ResultSet rs) throws Exception;
}
