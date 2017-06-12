package cn.tedu.dao;

import java.sql.SQLException;

import cn.tedu.domain.User;
import cn.tedu.utils.BeanHandler;
import cn.tedu.utils.DaoUtils;

public class UserDaoImpl implements UserDao{
	/**
	 * 根据用户名查询用户是否存在
	 * @param username
	 * @return 
	 */
	public Object findUserByUsername(String username) {
		try {
			return DaoUtils.query("select * from user where username=?", new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	/**
	 * 将用户注册数据存入数据库
	 * @param user
	 */
	public void addUser(User user){
		try {
			DaoUtils.update("insert into user values(null, ?, ?, ?, ?)", user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	/**
	 * 根据用户名和密码查询用户的方法
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		try {
			return DaoUtils.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

}
