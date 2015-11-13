package com.chinanetwork.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.dao.DepartmentManageDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class DepartmentManageService {
	
	private DepartmentManageDao departmentManageDao = new DepartmentManageDao();
	
	public List<Department> getAllDepartments() throws Exception
	{
		return departmentManageDao.getAllDepartment();
	}
	
	//---------------------------------------------------分页代码开始
	/**
	 * 分页List
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public List<Department> getAllPageDepartment(int pageNo)throws Exception{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<Department> departmentList=departmentManageDao.getAllDepartmentPage(pageStart, pageSize);
		if(departmentList.size()==0){
			return null;
		}
		return departmentList;
	}
	
	public Map<String,Integer> getTotalData()throws Exception{
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		int countRecored=departmentManageDao.getTotalRecored();
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

	
	public Department getDepartment(int id) throws Exception {
		// TODO Auto-generated method stub
		return departmentManageDao.getDepartmentById(id);
	}

	public void addDepartment(Department department) throws Exception {
		departmentManageDao.addDepartment(department);
		return ;
	}

	public void deleteDepartment(int id) throws Exception {
		// TODO Auto-generated method stub
		departmentManageDao.deleteDepartment(id);
		
	}



	public void modifyDepartment(Department departmentToModify) throws Exception {
		// TODO Auto-generated method stub
		departmentManageDao.modifyDepartmentById(departmentToModify);
	}





	

}
