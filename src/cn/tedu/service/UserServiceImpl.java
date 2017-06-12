package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BasicFactory;

public class UserServiceImpl implements UserService{
	private UserDao dao= BasicFactory.getFactory().getInstance(UserDao.class);
	/**
	 * 注册用户
	 * @param user
	 * @throws MsgException
	 */
	public void registUser(User user) throws MsgException{
		//1、检查用户名是否存在
		if(dao.findUserByUsername(user.getUsername())!=null){
			throw new MsgException("用户名已存在");
		}
		//调用dao层的方法将用户诛注册数据添加到数据库中
		dao.addUser(user);
	}
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean hasUser(String username){
		return dao.findUserByUsername(username)!=null;
	}
	/**
	 * 用户登录检查
	 * @param username
	 * @param password
	 * @return
	 */
	public User loginUser(String username,String password){
		return dao.findUserByUsernameAndPassword(username,password);
	}
}
