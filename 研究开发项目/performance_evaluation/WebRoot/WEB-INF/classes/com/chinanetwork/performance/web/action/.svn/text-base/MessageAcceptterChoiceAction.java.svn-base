package com.chinanetwork.performance.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.service.MessageAccepterChoiceService;

/**
 * 用于消息接受者选择系统的Action
 * @author Administrator
 *
 */
public class MessageAcceptterChoiceAction {

	private MessageAccepterChoiceService messageAccepterChoiceService=new MessageAccepterChoiceService();
	
	private List<Department> departmentList;
	private List<Staff> staffList;
	private String departmentIdStr;
	public List<Department> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	public List<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	public String getDepartmentIdStr() {
		return departmentIdStr;
	}
	public void setDepartmentIdStr(String departmentIdStr) {
		this.departmentIdStr = departmentIdStr;
	}
	public String toAccepter(){
		try {
			departmentList=messageAccepterChoiceService.getAllDepartment();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "toAccepter";
	}
	
	public String getStaff(){
		try {
			List<Staff> staffList=messageAccepterChoiceService.getStaffListByDepartmentId(departmentIdStr);
			String info="";
			for(int i=0;i<staffList.size();i++){
				info=info+staffList.get(i).getStaffId()+","+staffList.get(i).getStaffName()+";";
			}
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return null;
	}
	
	public String submitMeassage(){
		System.out.println("部门提交数："+departmentList.size()+","+"员工提交数："+staffList.size());
		return "";
	}
}
