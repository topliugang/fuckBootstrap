package com.chinanetwork.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.ScoreItemCategory;
import com.chinanetwork.performance.dao.DepartmentScoreItemManageDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class DepartmentScoreItemManageService {
	
	private DepartmentScoreItemManageDao departmentScoreItemManageDao = new DepartmentScoreItemManageDao();
	
//	public List<DepartmentScoreItem> getAllDepartmentScoreItems() throws Exception
//	{
//		return departmentScoreItemManageDao.getAllDepartmentScoreItem();
//	}
	
	public List<DepartmentScoreItem> getDepartmentScoreItemsByDepartmentId(int id) throws Exception
	{
		return departmentScoreItemManageDao.getDepartmentScoreItemsByDepartmentId(id);
	}
	//----------------------------------------------------------------------------------------分页代码开始
	public List<DepartmentScoreItem> getDepartmentScoreItemsByDepartmentId(int departmentId,int pageNo)throws Exception{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<DepartmentScoreItem> departmentScoreItems=departmentScoreItemManageDao.getDepartmentScoreItemsByDepartmentId(departmentId, pageStart, pageSize);
		
		if(departmentScoreItems.size()==0){
			return null;
		}
		return departmentScoreItems;
	}
	public Map<String,Integer> getTotalData(int departmentId)throws Exception{
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		int countRecored=departmentScoreItemManageDao.getTotalRecored(departmentId);
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
	//----------------------------------------------------------------------------------------分页代码结束
	public DepartmentScoreItem getDepartmentScoreItem(int id) throws Exception {
		// TODO Auto-generated method stub
		return departmentScoreItemManageDao.getDepartmentScoreItemById(id);
	}
	
	public List<Integer> getFromDepartmentsIdById(int id) throws Exception
	{
		return departmentScoreItemManageDao.getFromDepartmentsIdById(id);
	}

	public void addDepartmentScoreItem(DepartmentScoreItem departmentScoreItem, List<Integer> departmentsFromId) throws Exception {
		departmentScoreItemManageDao.addDepartmentScoreItem(departmentScoreItem, departmentsFromId);
		return ;
	}

	public void deleteDepartmentScoreItem(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		departmentScoreItemManageDao.deleteDepartmentScoreItem(parseInt);
		
	}
	
	public List<ScoreItemCategory> getAllCategories( ) throws Exception
	{
		return departmentScoreItemManageDao.getAllCategories();
	}

	public void modifyDepartmentScoreItem(
			DepartmentScoreItem departmentScoreItemWillModify,
			List<Integer> departmentsFromId) throws Exception {
		departmentScoreItemManageDao.modifyDepartmentScoreItem(departmentScoreItemWillModify, departmentsFromId);
	}




	

}
