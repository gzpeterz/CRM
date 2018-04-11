package com.ithc.interceptor;

import org.apache.struts2.ServletActionContext;

import com.ithc.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class MyInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		//判断用户是否登入
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			return "login";
		}
		
		return invocation.invoke();
	}

}
