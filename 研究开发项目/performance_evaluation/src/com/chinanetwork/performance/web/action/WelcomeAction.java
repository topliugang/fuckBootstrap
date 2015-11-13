package com.chinanetwork.performance.web.action;


public class WelcomeAction {
	private String name;
	
	public String execute(){
		System.out.println("welcomeaction.execute()");
		System.out.println("name: "+name);
		if("fuck".equalsIgnoreCase(name)){
			return "fail";
		}
		return "success";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
