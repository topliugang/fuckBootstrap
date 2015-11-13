package com.chinanetwork.performance.service;

import java.util.List;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.dao.LoginDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class LoginService {

	private LoginDao loginDao = new LoginDao();
	
	public Staff login(String username, String staff_password) throws Exception{
		Staff staff = loginDao.selectStaffByIdAndPassword(username, staff_password);
		return staff;
	}

	public List<Funcpage> getFuncpages(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		return loginDao.getFuncpages(staff);
	}
	
	public boolean judgeGradeStart()throws Exception{
		//获取绩效开始时间
		long startTime=ConfigurationDataUtil.getLongVlaue("start_time");
		//获取绩效结束时间
		long endTime=ConfigurationDataUtil.getLongVlaue("end_time");
		//获取当前时间
		long nowTime=System.currentTimeMillis()/1000;
		
		//如果当前时间大于结束时间或者是小于开始时间
		if(nowTime>=startTime && nowTime<=endTime){
			return true;
		}
		return false;
	}
}
