package com.chinanetwork.performance.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.TrainingPlan;
import com.chinanetwork.performance.dao.TrainingPlanDao;
import com.chinanetwork.performance.dao.WeekMettingDao;
import com.chinanetwork.performance.util.WedQuantityOfMonthUtil;

public class TrainingPlanService {
	
	TrainingPlanDao trainingPlanDao = new TrainingPlanDao();
	
	/**
	 * 根据各部门周例会上传情况，更新各部门的分数
	 * 算法还没有加上月例会的检测
	 * @param departmentScoreMap
	 * @param beginEndDates
	 * @return
	 * @throws Exception
	 
	public Map<Department,Float> updateDepartmentScoreByWeekAndMonthMeeting(Map<Department,Float> departmentScoreMap,int year,int month)throws Exception{
		List<Date> beginEndDates=WedQuantityOfMonthUtil.getBeginEndDates(year, month);
		Map<Department,Float> newDepartmentScoreMap=departmentScoreMap;
		Iterator iterator=newDepartmentScoreMap.keySet().iterator();
		while(iterator.hasNext()){
			Department departmentKey=(Department)iterator.next();
			int i=0;
			boolean flag=true;
			while(i<beginEndDates.size()){
				List<TrainingPlan> trainingPlans=null;
				trainingPlans=trainingPlanDao.selectTrainingPlanLoad(departmentKey, beginEndDates.get(i), beginEndDates.get(i+1));
				if(trainingPlans==null||trainingPlans.size()==0){
					flag=false;
					break;
				}
			}
			if(flag){
				float score=0;
				score=newDepartmentScoreMap.get(departmentKey)+5;
				newDepartmentScoreMap.put(departmentKey, score);
			}
			i=i+2;
		}
		return newDepartmentScoreMap;
	}
	*/
	/**
	 * 在月底绩效考评的时候，检测还有哪个部门没有提交周例会
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 
	public List<String> checkDepartmentTrainingPlanLoad(int year,int month)throws Exception{
		List<Department> departmentList=trainingPlanDao.selectTrainingPlanLoadDepartments();
		List<String> messageList=new ArrayList<String>();
		List<Date> beginEndDates=WedQuantityOfMonthUtil.getBeginEndDates(year, month);
		for(int i=0;i<departmentList.size();i++){
			Department department=departmentList.get(i);
			int j=0;
			while(j<beginEndDates.size()){
				List<TrainingPlan> trainingPlans=null;
				trainingPlans=trainingPlanDao.selectTrainingPlanLoad(department, beginEndDates.get(j), beginEndDates.get(j+1));
				if(trainingPlans==null||trainingPlans.size()==0){
					Calendar calendar=Calendar.getInstance();
					calendar.setTime(beginEndDates.get(j));
					String message=department.getDepartmentName()+"还没有提交"+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)-1)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"号周例会";
					messageList.add(message);
				}
				j=j+2;
			}
		}
		return messageList;
	}
	*/
	/**
	 * 提交部门周例会
	 * @param trainingPlan
	 * @return
	 * @throws Exception
	 */
	public boolean loadTrainingPlan(TrainingPlan trainingPlan)throws Exception{
		try{
			trainingPlanDao.insertTrainingPlan(trainingPlan);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据部门查找该部门所有会议上传记录
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public List<TrainingPlan> getTrainingPlansByDepartment(Department department)throws Exception{
		try{
			List<TrainingPlan> trainingPlans=trainingPlanDao.selectTrainingPlansByDepartment(department);
			return trainingPlans;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<TrainingPlan> getAllTrainingPlan(int pageNo,int pageSize)throws Exception{
		int startRecord=(pageNo-1)*pageSize;
		return trainingPlanDao.selectAllTrainingPlan(startRecord,pageSize);
	}
	
	public List<TrainingPlan> getAllTrainingPlan()throws Exception{
		return trainingPlanDao.selectAllTrainingPlan();
	}
	
	
	public TrainingPlan getTrainingPlanById(int trainingPlanId)throws Exception{
		try{
			TrainingPlan trainingPlan=trainingPlanDao.selectTrainingPlanById(trainingPlanId);
			return trainingPlan;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	} 
	/*
	public int getTotalRecordSize()throws Exception{
		return trainingPlanDao.selectQuantityOfTrainingPlan();
	}
	*/
	public int getTotalPage(int pageSize,int totalRecord)throws Exception{
		if(totalRecord%pageSize==0){
			return totalRecord/pageSize;
		}else{
			return totalRecord/pageSize+1;
		}
	}
	/*
	public List<TrainingPlan> addSignForTrainingPlans(List<TrainingPlan> trainingPlans)throws Exception{
		if(trainingPlans!=null){
			if(trainingPlans.size()!=0){
				for(int i=0;i<trainingPlans.size();i++){
					TrainingPlan trainingPlan=trainingPlans.get(i);
					Date uploadTime=trainingPlan.getUploadTime();
					Calendar calendar=Calendar.getInstance();
					calendar.setTime(uploadTime);
					int thisYear=calendar.get(Calendar.YEAR);
					int thisMonth=calendar.get(Calendar.MONTH)+1;
					List<Date> beginEndDates=WedQuantityOfMonthUtil.getBeginEndDates(thisYear,thisMonth);
					int j=0;
					int week=1;
					boolean flag=false;
					while(j<beginEndDates.size()){
						if(uploadTime.getTime()>=beginEndDates.get(j).getTime()&&uploadTime.getTime()<=beginEndDates.get(j+1).getTime()){
							//trainingPlans.get(i).setSign(thisYear+"年"+thisMonth+"月"+"第"+week+"周");
							flag=true;
							break;
						}
						j=j+2;
						week=week+1;
					}
					if(!flag){
						//trainingPlans.get(i).setSign(thisYear+"年"+(thisMonth+1)+"月"+"第1周");
					}
				}
			}
			return trainingPlans;
		}else{
			trainingPlans=new ArrayList<TrainingPlan>();
			return trainingPlans;
		}
		
	}
	
	*/
}
