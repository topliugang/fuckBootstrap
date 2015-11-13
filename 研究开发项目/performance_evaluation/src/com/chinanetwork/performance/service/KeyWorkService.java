package com.chinanetwork.performance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentKeyWork;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.dao.DepartmentKeyWorkDao;
import com.chinanetwork.performance.dao.GetScoreDao;
import com.chinanetwork.performance.dao.ScoreDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class KeyWorkService {

	private DepartmentKeyWorkDao departmentKeyWorkDao=new DepartmentKeyWorkDao();
	private GetScoreDao getScoreDao=new GetScoreDao();
	
	
	/**
	 * 根据登录用户，找到其所属部门的所有重点工作列表
	 * @param staff
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentKeyWork> getLoginStaffKeyWorkList()throws Exception{
		//Department department=staff.getDepartment();
		//int departmentId=department.getDepartmentId();
		List<DepartmentKeyWork> departmentkeyWorkList=departmentKeyWorkDao.getMyDepartmentKeyWorkList();
		return departmentkeyWorkList;
	}
	
	//----------------------------------------------------------------分页代码开始
	public List<DepartmentKeyWork> getLoginStaffKeyWorkList(int pageNo)throws Exception{
		//Department department=staff.getDepartment();
		//int departmentId=department.getDepartmentId();
		
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<DepartmentKeyWork> departmentKeyWorkList=departmentKeyWorkDao.getMyDepartmentKeyWorkList( pageStart, pageSize);
		
		if(departmentKeyWorkList.size()==0){
			return null;
		}
		
		return departmentKeyWorkList;
	}
	
	public Map<String,Integer> getTotalData()throws Exception{
		//Department department=staff.getDepartment();
		//int departmentId=department.getDepartmentId();
		
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		
		int countRecored=departmentKeyWorkDao.getTotalRecored();
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
	//----------------------------------------------------------------分页代码结束
	/**
	 * 根据登陆用户，找到其所属部门
	 * @param staffId
	 * @return
	 * @throws Exception
	 */
	public Department getDepartment(Staff staff)throws Exception{
		int staffId=staff.getStaffId();
		return departmentKeyWorkDao.getDepartment(staffId);
	}
	/**
	 * 获取重点工作表中编号最大的编号
	 * @return
	 * @throws Exception
	 */
	public int getMaxKeyWorkId()throws Exception{
		int max=departmentKeyWorkDao.getMaxKeyWorkId();
		if(max==0||max==-1){
			max=1001;
		}else{
			max=max+1;
		}
		return max;
	}
	/**
	 * 添加重点工作
	 * @param keyWorkId
	 * @param departmentId
	 * @param content
	 * @param sYear
	 * @param sMonth
	 * @param eYear
	 * @param eMonth
	 * @throws Exception
	 */
	public void addDepartmentKeyWork(int keyWorkId,Staff staff,String keyWorkContent,String startYear,String startMonth,String endYear,String endMonth)throws Exception{
		int departmentId=staff.getDepartment().getDepartmentId();
		departmentKeyWorkDao.insertDepartmentKeyWork(keyWorkId, departmentId, keyWorkContent,Integer.parseInt(startYear), 
				Integer.parseInt(startMonth), Integer.parseInt(endYear), Integer.parseInt(endMonth));
	}
	
	private ScoreDao scoreDao = new ScoreDao();
	
	public List<DepartmentKeyWork> getDepartmentKeyWork(String departmentIdStr)throws Exception{
		int departmentId=Integer.parseInt(departmentIdStr);
		List<DepartmentKeyWork> departmentKeyWorkList=new ArrayList<DepartmentKeyWork>();
		List<DepartmentKeyWork> newDepartmentKeyWorkList=new ArrayList<DepartmentKeyWork>();
		
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		departmentKeyWorkList=getScoreDao.getDepartmentKeyWorkList(departmentId);
		System.out.println("重点工作数量："+departmentKeyWorkList.size());
		if(departmentKeyWorkList!=null&&departmentKeyWorkList.size()!=0){
			for(int i=0;i<departmentKeyWorkList.size();i++){
				DepartmentKeyWork departmentKeyWork=departmentKeyWorkList.get(i);
				/*if(departmentKeyWork.getStartYear()<=year&&departmentKeyWork.getStartMonth()<=month&&departmentKeyWork.getEndYear()>=year&&departmentKeyWork.getEndMonth()>=month){
					newDepartmentKeyWorkList.add(departmentKeyWork);
				}*/
				if(departmentKeyWork.getStartYear()<year){
					if(departmentKeyWork.getEndYear()>year){
						newDepartmentKeyWorkList.add(departmentKeyWork);
					}else if(departmentKeyWork.getEndYear()==year){
						if(departmentKeyWork.getEndMonth()>=month){
							newDepartmentKeyWorkList.add(departmentKeyWork);
						}
					}
				}else if(departmentKeyWork.getStartYear()==year){
					if(departmentKeyWork.getStartMonth()<=month){
						if(departmentKeyWork.getEndYear()>year){
							newDepartmentKeyWorkList.add(departmentKeyWork);
						}else if(departmentKeyWork.getEndYear()==year){
							if(departmentKeyWork.getEndMonth()>=month){
								newDepartmentKeyWorkList.add(departmentKeyWork);
							}
						}
					}
				}
			}
		}
		System.out.println(newDepartmentKeyWorkList.size());
		return newDepartmentKeyWorkList;
	}
	
	
	/**
	 * 返回所有有重点工作打分条目还未打分的部门列表
	 * @return
	 * @throws Exception
	 */
	public List<Department> getNoScoredKeyworkDepartment(Department departmentFrom)throws Exception{
		if(ConfigurationDataUtil.getStringValue("start_score").equals("false"))
			return new ArrayList<Department>();
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		List<Department> ret = scoreDao.getNoScoreDepartment(departmentFrom, year, month, 3);
		if (ret == null)
			ret = new ArrayList<Department>();
		return ret;
		
	
		
	}
	
	/**
	 * 根据下拉列表所选择的的部门名称，返回其对应的可用重点工作条目
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	
	public List<DepartmentScoreItem> getDepartmentWorkScoreItem(String departmentIdStr)throws Exception{
		int departmentId=Integer.parseInt(departmentIdStr);
		List<DepartmentScoreItem> departmentScoreItemList=getScoreDao.getDepartmentWorkScoreItem(departmentId);
		List<DepartmentScoreItem> departmentScoreItems=new ArrayList<DepartmentScoreItem>();
		if(departmentScoreItemList!=null&&departmentScoreItemList.size()!=0){
			for(DepartmentScoreItem item:departmentScoreItemList){
				if(item.getScoreInUse()==1){
					departmentScoreItems.add(item);
				}
			}
			return departmentScoreItems;
		}
		return departmentScoreItemList;
	}
	
	
}
