package com.chinanetwork.performance.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.MonthMeetingRecord;
import com.chinanetwork.performance.bean.WeekMeetingRecord;
import com.chinanetwork.performance.dao.MonthMettingDao;
import com.chinanetwork.performance.dao.WeekMettingDao;
import com.chinanetwork.performance.util.WedQuantityOfMonthUtil;

public class MonthMeetingService {
	
	MonthMettingDao monthMeettingDao=new MonthMettingDao();
	
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
				List<WeekMeetingRecord> weekMeetingRecords=null;
				weekMeetingRecords=weekMeettingDao.selectWeekMeetingLoad(departmentKey, beginEndDates.get(i), beginEndDates.get(i+1));
				if(weekMeetingRecords==null||weekMeetingRecords.size()==0){
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
	 
	public List<String> checkDepartmentMonthMeetingLoad(int year,int month)throws Exception{
		List<Department> departmentList=weekMeettingDao.selectWeekMeetingLoadDepartments();
		List<String> messageList=new ArrayList<String>();
		List<Date> beginEndDates=WedQuantityOfMonthUtil.getBeginEndDates(year, month);
		for(int i=0;i<departmentList.size();i++){
			Department department=departmentList.get(i);
			int j=0;
			while(j<beginEndDates.size()){
				List<WeekMeetingRecord> weekMeetingRecords=null;
				weekMeetingRecords=weekMeettingDao.selectWeekMeetingLoad(department, beginEndDates.get(j), beginEndDates.get(j+1));
				if(weekMeetingRecords==null||weekMeetingRecords.size()==0){
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
	 * @param weekMeetingRecord
	 * @return
	 * @throws Exception
	 */
	public boolean loadMonthMeeting(MonthMeetingRecord monthMeetingRecord)throws Exception{
		try{
			monthMeettingDao.insertMonthMeetingRecord(monthMeetingRecord);
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
	public List<MonthMeetingRecord> getMonthMeetingRecordsByDepartment(Department department)throws Exception{
		try{
			List<MonthMeetingRecord> monthMeetingRecords=monthMeettingDao.selectMonthMeetingRecordsByDepartment(department);
			return monthMeetingRecords;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	public List<MonthMeetingRecord> getAllMonthMeetingRecord(int pageNo,int pageSize)throws Exception{
		int startRecord=(pageNo-1)*pageSize;
		return weekMeettingDao.selectAllWeekMeetingRecord(startRecord,pageSize);
	}
	*/
	
	public List<MonthMeetingRecord> getAllMonthMeetingRecord()throws Exception{
		return monthMeettingDao.selectAllMonthMeetingRecord();
	}
	
	
	public MonthMeetingRecord getMonthMeetingRecordById(int monthMeetingRecordId)throws Exception{
		try{
			MonthMeetingRecord monthMeetingRecord=monthMeettingDao.selectMonthMeetingRecordById(monthMeetingRecordId);
			return monthMeetingRecord;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	} 
	/*
	public int getTotalRecordSize()throws Exception{
		return weekMeettingDao.selectQuantityOfWeekMeetingRecord();
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
	public List<WeekMeetingRecord> addSignForWeekMeetingRecords(List<WeekMeetingRecord> weekMeetingRecords)throws Exception{
		if(weekMeetingRecords!=null){
			if(weekMeetingRecords.size()!=0){
				for(int i=0;i<weekMeetingRecords.size();i++){
					WeekMeetingRecord weekMeetingRecord=weekMeetingRecords.get(i);
					Date uploadTime=weekMeetingRecord.getUploadTime();
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
							//weekMeetingRecords.get(i).setSign(thisYear+"年"+thisMonth+"月"+"第"+week+"周");
							flag=true;
							break;
						}
						j=j+2;
						week=week+1;
					}
					if(!flag){
						//weekMeetingRecords.get(i).setSign(thisYear+"年"+(thisMonth+1)+"月"+"第1周");
					}
				}
			}
			return weekMeetingRecords;
		}else{
			weekMeetingRecords=new ArrayList<WeekMeetingRecord>();
			return weekMeetingRecords;
		}
		
	}
	
	*/
}
