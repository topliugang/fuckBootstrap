package com.chinanetwork.performance.service;

import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.FuncpageCategory;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.dao.ToMenuDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class ToMenuService {

	ToMenuDao toMenuDao=new ToMenuDao();
	
	public List<Funcpage> getFuncpages(Staff staff)throws Exception{
		List<Funcpage> funcpages=new ArrayList<Funcpage>();
		try{
			funcpages=toMenuDao.getFuncpageList(staff.getRole().getRoleId());
			if(funcpages!=null&&funcpages.size()!=0){
				//如果绩效考评没有开始
				if(!this.judgeGradeStart()){
					for(int i=0;i<funcpages.size();i++){
						String funcpageName=funcpages.get(i).getName();
						if(funcpageName.equals("部门打分")||funcpageName.equals("部门打分记录")||funcpageName.equals("员工打分")||
								funcpageName.equals("员工打分记录")||funcpageName.equals("重点工作得分")||funcpageName.equals("重点工作得分修改")){
							funcpages.remove(i);
							i--;
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return funcpages;
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
	
	
	public List<FuncpageCategory> getFuncpageCategories(List<Funcpage> funcpageList)throws Exception{
		List<FuncpageCategory> categorys=new ArrayList<FuncpageCategory>();
		if( funcpageList != null ){
			for( Funcpage funcpage : funcpageList )
			{
				boolean contains = false;
				for( FuncpageCategory category : categorys )
				{
					if(category.equals(funcpage.getCategory()))
					{
						contains = true;
						break;
					}
				}
				if( !contains )
					categorys.add(funcpage.getCategory());
			}
		}
		return categorys;
	}
}
