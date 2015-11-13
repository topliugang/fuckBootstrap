package com.chinanetwork.performance.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("我的拦截器");
		ActionContext actionContext = actionInvocation.getInvocationContext();
		//获取session
		Map<String,Object> session=actionContext.getSession();
		//从session中获取登录用户
		Object user=session.get("staff");
		
		if(user!=null){
			return actionInvocation.invoke();
		}
		
		return "noLogin";
	}

}
