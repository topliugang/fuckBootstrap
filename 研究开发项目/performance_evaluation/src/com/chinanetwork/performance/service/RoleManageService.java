package com.chinanetwork.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.dao.RoleManageDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class RoleManageService {
	
	private RoleManageDao roleManageDao = new RoleManageDao();
	
	public List<Role> getAllRoles() throws Exception
	{
		return roleManageDao.getAllRole();
	}
	
	//-------------------------------------------------------------分页代码开始
	public List<Role> getAllRoles(int pageNo)throws Exception{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<Role> roleList=roleManageDao.getAllRole(pageStart, pageSize);
		if(roleList.size()==0){
			return null;
		}
		
		return roleList;
	}
	
	public Map<String,Integer> getTotalData()throws Exception{
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		int countRecored=roleManageDao.getTotalRecored();
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
	//-------------------------------------------------------------分页代码结束
	

	public Role getRole(int parseInt) throws Exception {
		return roleManageDao.getRoleById(parseInt);
	}
	
	public void addRole(Role role) throws Exception {
		roleManageDao.addRole(role);
		return ;
	}

	public void deleteRole(int parseInt) throws Exception {
		roleManageDao.deleteRole(parseInt);
	}

	public void modifyRole(int parseInt, Role roleToModify) throws Exception {
		roleManageDao.modifyRoleById(parseInt, roleToModify);
	}
	
	public void modifyFuncage(Role role, List<Integer> funcpageIds) throws Exception{
		roleManageDao.modifyFuncpage(role, funcpageIds);
	}

	public List<Funcpage> getFuncpages(int roleId) throws Exception {
		return roleManageDao.getFuncpages(roleId);
	}
	
	public List<Funcpage> getAllFuncpages() throws Exception
	{
		return roleManageDao.getAllFuncpages();
	}

	public List<Integer> getFuncpageIdsByRoleId(int roleId) throws Exception{
		return roleManageDao.getFuncpageIdsByRoleId(roleId);
	}


	

}
