package com.chinanetwork.performance.service;

import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffClass;
import com.chinanetwork.performance.dao.MessageAccepterChoiceDao;

/**
 * 消息接受者选择系统的Service
 * @author Administrator
 *
 */
public class MessageAccepterChoiceService {

	MessageAccepterChoiceDao messageAccepterChoiceDao=new MessageAccepterChoiceDao();
	
	public List<Department> getAllDepartment()throws Exception{
		List<Department> departmentList=messageAccepterChoiceDao.getAllDepartment();
		return departmentList;
	}
	
	public List<StaffClass> getAllStaffClass()throws Exception{
		List<StaffClass> staffClassList=messageAccepterChoiceDao.selectAllStaffClass();
		return staffClassList;
	}
	
	public List<Staff> getStaffListByDepartmentId(String departmentIdStr)throws Exception{
		int departmentId=Integer.parseInt(departmentIdStr);
		List<Staff> staffList=messageAccepterChoiceDao.getStaffByDepartmentId(departmentId);
		return staffList;
	}
	
	public List<Staff> getStaffListByClassId(String classId)throws Exception{
		List<Staff> staffList=messageAccepterChoiceDao.selectStaffsByClassId(classId);
		return staffList;
	}
}
