package cn.tedu.dao;

import cn.tedu.domain.User;

public interface UserDao extends Dao {
	/**
	 * 根据用户名查询用户是否存在
	 * @param username
	 * @return
	 */
	public Object findUserByUsername(String username);
	/**
	 * 将用户注册数据存入数据库
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 根据用户名和密码查询用户的方法
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByUsernameAndPassword(String username, String password);

}
