package com.chinanetwork.performance.web.action;

import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.service.LoginService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {
	private String username;
	private String password;

	private String error;

	private LoginService loginService = new LoginService();

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String y;
	public String m;
	public String start;
	public String end;

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String login() {
		System.out.println("hello-------------------------------------");
		System.out.println(username);
		System.out.println(password);
		if (username==null||password==null){
			return "failure";
		}else if(username.equals("")||password.equals("")){
			return "failure";
		}
		
		String result = "";
		int staff_id = Integer.parseInt(username);
		String staff_password = password;
		try {
			Staff staff = loginService.login(username, staff_password);
			if (staff != null) {
				this.getSession().put("staff", staff);
				//funcpages
				List<Funcpage> funcpages = loginService.getFuncpages(staff);
				if(!loginService.judgeGradeStart()){
					for(int i=0;i<funcpages.size();i++){
						String funcpageName=funcpages.get(i).getName();
						if(funcpageName.equals("部门打分")||funcpageName.equals("部门打分记录")||funcpageName.equals("员工打分")||
								funcpageName.equals("员工打分记录")||funcpageName.equals("重点工作得分")||funcpageName.equals("重点工作得分修改")){
							funcpages.remove(i);
							i--;
						}
					}
				}
				this.getSession().put("funcpages", funcpages);
				
				/*//未读message
				boolean hasUnreadMessage = false;
				MessageService messageService = new MessageService();
				if( messageService.listUnread(staff) != null )
					hasUnreadMessage = true;
				this.getSession().put("hasUnreadMessage", hasUnreadMessage);*/
				
				result = "success";
			} else {
				result = "failure";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String exit()
	{
		ActionContext ac = ActionContext.getContext();
		Map session = ac.getSession();
		session.remove("staff");
		session.remove("funcpages");

		return "exit";
	}

	@Override
	public String execute() {
		if ("success".equalsIgnoreCase(this.login())) {
			return "success";
		} else {
			setError("用户名或密码错误！");
			return "failure";
		}

	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

}
