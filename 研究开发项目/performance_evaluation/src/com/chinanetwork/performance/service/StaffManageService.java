package com.chinanetwork.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.dao.StaffManageDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class StaffManageService {
	
	private StaffManageDao staffManageDao = new StaffManageDao();
	
	public List<Staff> getAllStaffs() throws Exception
	{
		return staffManageDao.getAllStaff();
	}
//---------------------------------------------------分页代码开始
	/**
	 * 分页List
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public List<Staff> getAllPageStaff(int pageNo)throws Exception{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<Staff> staffList=staffManageDao.getAllStaffPage(pageStart, pageSize);
		if(staffList.size()==0){
			return null;
		}
		return staffList;
	}
	
	public Map<String,Integer> getTotalData()throws Exception{
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		int countRecored=staffManageDao.getTotalRecored();
		//计算总页数
		int countPage=countRecored/pageSize;
		int mod=countRecored%pageSize;
		if(mod!=0){
			countPage=countPage+1;
		}
		
		dataMap.put("countRecored", countRecored);
		dataMap.put("countPage", countPage);
		
		return dataMap;
	}
//--------------------------------------------------分页代码结束
	public List<Float> getSalarysToList(List<Staff> staffsToList) throws Exception {
		// TODO Auto-generated method stub
		return staffManageDao.getSalarysToList(staffsToList);
	}

	
	public Staff getStaff(int id) throws Exception {
		// TODO Auto-generated method stub
		return staffManageDao.getStaffById(id);
	}

	public void addStaff(Staff staff) throws Exception {
		staffManageDao.addStaff(staff);
		return ;
	}

	public void deleteStaff(int id) throws Exception {
		// TODO Auto-generated method stub
		staffManageDao.deleteStaff(id);
		
	}



	public void modifyStaff(int id, Staff staffToModify) throws Exception {
		// TODO Auto-generated method stub
		staffManageDao.modifyStaffById(id, staffToModify);
	}
	public void passwordmodify(Staff staff, String newpassword) throws Exception {
		// TODO Auto-generated method stub
		staff.setPassword(newpassword);
		staffManageDao.modifyStaffById(staff.getStaffId(), staff);
	}



	public List<Staff> getScoredStaffs() throws Exception
	{
		return staffManageDao.getScoredStaff();
	} 

	public float getSalaryByStaffId(int staffId)throws Exception
	{
		return staffManageDao.getSalaryByStaffId(staffId);
	}


	

}
