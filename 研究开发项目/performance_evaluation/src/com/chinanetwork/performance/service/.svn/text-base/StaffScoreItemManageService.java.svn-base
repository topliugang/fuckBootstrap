package com.chinanetwork.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.dao.StaffScoreItemManageDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class StaffScoreItemManageService {
	
	private StaffScoreItemManageDao staffScoreItemManageDao = new StaffScoreItemManageDao();
	

	
	public List<StaffScoreItem> getAllStaffScoreItems() throws Exception
	{
		return staffScoreItemManageDao.getAllStaffScoreItem();
	}
	
//--------------------------------------------------------------------------------分页代码开始
	
	public List<StaffScoreItem> getAllStaffScoreItems(int pageNo) throws Exception
	{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<StaffScoreItem> staffScoreItems=staffScoreItemManageDao.getAllStaffScoreItem(pageStart,pageSize);
		
		if(staffScoreItems.size()==0){
			return null;
		}
		return staffScoreItems;
	}
	
	public Map<String,Integer> getTotalData()throws Exception{
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		int countRecored=staffScoreItemManageDao.getTotalRecored();
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
//--------------------------------------------------------------------------------分页代码结束
	public StaffScoreItem getStaffScoreItem(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		return staffScoreItemManageDao.getStaffScoreItemById(parseInt);
	}

	public void addStaffScoreItem(StaffScoreItem staffScoreItem) throws Exception {
		staffScoreItemManageDao.addStaffScoreItem(staffScoreItem);
		return ;
	}

	public void deleteStaffScoreItem(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		staffScoreItemManageDao.deleteStaffScoreItem(parseInt);
		
	}



	public void modifyStaffScoreItem(StaffScoreItem staffScoreItemToModify) throws Exception {
		// TODO Auto-generated method stub
		staffScoreItemManageDao.modifyStaffScoreItemById(staffScoreItemToModify);
	}

	public List<StaffScoreItem> getStaffScoreItemsByDepartmentId(
			int departmentIdChosen) throws Exception {
		return staffScoreItemManageDao.getStaffScoreItemsByDepartmentId(departmentIdChosen);
	}

	/*
	public List<Post> getPostsToList(List<StaffScoreItem> staffScoreItemsToList)  throws Exception {
		// TODO Auto-generated method stub
		return staffScoreItemManageDao.getPostsToLst(staffScoreItemsToList);
	}
	
	*/




	

}
