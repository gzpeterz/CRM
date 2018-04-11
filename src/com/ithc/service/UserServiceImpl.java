package com.ithc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.User;
import com.ithc.dao.UserDao;
import com.ithc.util.Base64;
import com.ithc.util.State;

@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 登入
	public User login(User user) {
		// 登入的密码加密去和数据库判断
		String user_password = user.getUser_password();
		String encode = Base64.getEncode(user_password);
		user.setUser_password(encode);
		// 加入状态
		return userDao.login(user, State.getState());
	}

	// ajax注册登入名时判断
	public User registerName(String user_code) {
		return userDao.registerName(user_code);
	}

	// 注册
	public void register(User user) {
		// 1.密码需要加密
		String user_password = user.getUser_password();
		String encode = Base64.getEncode(user_password);
		user.setUser_password(encode);
		// 2.需要加入状态
		user.setUser_state(State.getState());
		userDao.register(user);
	}

	@Override
	public User findByName(String user_code) {

		return userDao.findByName(user_code);
	}

	@Override
	public User checkPassword(String user_code, String user_password) {
		// 加密
		String newuser_password = Base64.getEncode(user_password);

		return userDao.checkPassword(user_code, newuser_password);
	}

	@Override
	public void password(User user) {
		// 1.密码需要加密
		String user_password = user.getUser_password();
		String encode = Base64.getEncode(user_password);
		user.setUser_password(encode);

		userDao.password(user);

	}

	//查询所有
	public List<User> findByAll() {
		
		return userDao.findByAll();
	}
}