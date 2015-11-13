package com.chinanetwork.performance.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session=new HashMap<String,Object>();
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public Map<String,Object> getSession(){
		return session;
	}
}
