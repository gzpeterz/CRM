package com.ithc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ithc.bean.User;
import com.ithc.service.UserService;
import com.ithc.util.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QDecoderStream;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private String viCode;
	
	public void setViCode(String viCode) {
		this.viCode = viCode;
	}
	HttpSession session = ServletActionContext.getRequest().getSession();
	//登入
	public String login(){
		String str = (String) session.getAttribute("validateCode");
		
		if(viCode.equalsIgnoreCase(str)){
			User user = userService.login(this.user);
			if(user != null){
				session.setAttribute("user",user);
				return "index";
			}
		}
		session.setAttribute("msg", "您输入错误！！！");
		return "login";
	}
	
	HttpServletResponse resp = ServletActionContext.getResponse();
	//判断注册时登入名唯一
	public String registerName() throws Exception{
		//获取ajax请求的参数
		String user_code = user.getUser_code();
		// 用得到的参数去查询，如果查的出来就不能注册
		User user = userService.registerName(user_code);
		PrintWriter writer = resp.getWriter();
		if(user == null){//可以注册
			writer.print(1);
		}else{
			writer.print(0);
		}
		return null;
	}
	
	//注册
	public String register(){
		userService.register(user);
		return "login";
	}
	
	
	//安全退出
	public String exit(){
		session.invalidate();
		return "login";
	}
	
	//跳转修改密码
	public String updatePassword(){
		String user_code = user.getUser_code();
		user = userService.findByName(user_code);
		if(user == null){
			return "login";
		}
		return "updatePassword";
	}
	
	
	//校验密码
	public String checkPassword() throws Exception{
		String user_code = user.getUser_code();
		String user_password = user.getUser_password();
		user = userService.checkPassword(user_code,user_password);
		PrintWriter writer = resp.getWriter();
		if(user != null){
			//正确
			writer.print(1);
		}else{
			//错误
			writer.print(0);
		}
		return null;
	}
	
	
	//修改密码
	public String password(){
		String user_code = user.getUser_code();
		User user = userService.findByName(user_code);
		user.setUser_password(this.user.getUser_password());
		
		userService.password(user);
		
		return "login";
	}
	
	
	//查询所有
	public String findByAll(){
		
		List<User> list = userService.findByAll();
		if(list != null){
			HttpServletResponse response = ServletActionContext.getResponse();
			
			String jsonString = FastJsonUtil.toJSONString(list);
			FastJsonUtil.write_json(response, jsonString);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
}