package com.ithc.service;

import java.util.List;

import com.ithc.bean.User;

public interface UserService {
	//登入
	User login(User user);
	//ajax注册登入名时判断
	User registerName(String user_code);
	//注册
	void register(User user);
	//用名字查询
	User findByName(String user_code);
	//校验密码
	User checkPassword(String user_code, String user_password);
	//修改密码
	void password(User user);
	//查询所有
	List<User> findByAll();

}
