package com.chinanetwork.performance.service;

import java.util.List;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffClass;
import com.chinanetwork.performance.dao.StaffClassManageDao;

public class StaffClassManageService {
	
	private StaffClassManageDao staffClassManageDao = new StaffClassManageDao();
	
	public List<StaffClass> getAllStaffClasses() throws Exception
	{
		return staffClassManageDao.getAllStaffClasses();
	}
	
	public List<Staff> getStaffByClassId(String classId) throws Exception
	{
		return staffClassManageDao.getStaffByClassId(classId);
	}
	
	

}
