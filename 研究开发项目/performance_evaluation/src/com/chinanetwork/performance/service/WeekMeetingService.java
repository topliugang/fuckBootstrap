package com.chinanetwork.performance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.WeekMeetingRecord;
import com.chinanetwork.performance.dao.WeekMettingDao;
import com.chinanetwork.performance.util.WedQuantityOfMonthUtil;

public class WeekMeetingService {
	
	WeekMettingDao weekMeettingDao=new WeekMettingDao();
	
	/**
	 * 根据各部门周例会上传情况，更新各部门的分数
	 * 算法还没有加上月例会的检测
	 * @param departmentScoreMap
	 * @param beginEndDates
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,Float> updateDepartmentScoreByWeekAndMonthMeeting(Map<Integer,Float> departmentScoreMap,int year,int month)throws Exception{
		Map<Integer, Float> newMap = new HashMap<Integer, Float>();
		
		List<Date> beginEndDates=WedQuantityOfMonthUtil.getBeginEndDates(year, month);
		List<Date> monthBeginEndDate = WedQuantityOfMonthUtil.getMonthBeginEndDate(year, month);
		Date beginDate = monthBeginEndDate.get(0);
		Date endDate = monthBeginEndDate.get(1);
		
		Iterator<Map.Entry<Integer, Float>> iterator=departmentScoreMap.entrySet().iterator();
		while(iterator.hasNext()){//every department
			//int departmentId = (Integer)iterator.next();
			Map.Entry<Integer, Float> sbneirong=iterator.next();    
			
			
			
			
			int departmentId = sbneirong.getKey();
			float departmentScore = sbneirong.getValue();
			////week score
			int weekMeetingScore = 5; //total 
			/*
			for (int i = 0; i < beginEndDates.size(); i+=2)
			{
				List<WeekMeetingRecord> weekMeetingRecords=null;
				weekMeetingRecords=weekMeettingDao.selectWeekMeetingLoad(departmentId, (java.sql.Date)beginEndDates.get(i), (java.sql.Date)beginEndDates.get(i+1));
				if(weekMeetingRecords == null)
					weekMeetingScore -= 2;
			}
			if (weekMeetingScore < 0)
				weekMeetingScore = 0;
				
			*/
			////month score
			int monthMeetingScore = 5;
			
			/*
			List<MonthMeetingRecord> monthMeetingRecords = null;
			monthMeetingRecords = weekMeettingDao.selectMonthMeetingLoad(departmentId, (java.sql.Date)beginDate, (java.sql.Date)endDate);
			if (monthMeetingRecords == null)
				monthMeetingScore = 0;
			*/
			////training plan
			int trainingPlanScore = 5;
			/*
			List<TrainingPlan> trainingPlans = null;
			trainingPlans = weekMeettingDao.selectTrainingPlanLoad(departmentId, (java.sql.Date)beginDate, (java.sql.Date)endDate);
			if(trainingPlans == null)
				trainingPlanScore = 0;
			*/
			
			
			//test 用
			newMap.put(departmentId, departmentScore+weekMeetingScore+monthMeetingScore+trainingPlanScore);
		}
		
		
		return newMap;
	}
	/**
	 * 在月底绩效考评的时候，检测还有哪个部门没有提交周例会
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 
	public List<String> checkDepartmentWeekMeetingLoad(int year,int month)throws Exception{
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
	public boolean loadWeekMeeting(WeekMeetingRecord weekMeetingRecord)throws Exception{
		try{
			weekMeettingDao.insertWeekMeetingRecord(weekMeetingRecord);
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
	public List<WeekMeetingRecord> getWeekMeetingRecordsByDepartment(Department department)throws Exception{
		try{
			List<WeekMeetingRecord> weekMeetingRecords=weekMeettingDao.selectWeekMeetingRecordsByDepartment(department);
			return weekMeetingRecords;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<WeekMeetingRecord> getAllWeekMeetingRecord(int pageNo,int pageSize)throws Exception{
		int startRecord=(pageNo-1)*pageSize;
		return weekMeettingDao.selectAllWeekMeetingRecord(startRecord,pageSize);
	}
	
	public List<WeekMeetingRecord> getAllWeekMeetingRecord()throws Exception{
		return weekMeettingDao.selectAllWeekMeetingRecord();
	}
	
	
	public WeekMeetingRecord getWeekMeetingRecordById(int weekMeetingRecordId)throws Exception{
		try{
			WeekMeetingRecord weekMeetingRecord=weekMeettingDao.selectWeekMeetingRecordById(weekMeetingRecordId);
			return weekMeetingRecord;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	} 
	
	public int getTotalRecordSize()throws Exception{
		return weekMeettingDao.selectQuantityOfWeekMeetingRecord();
	}
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
