package com.chinanetwork.performance.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.WeekMeetingRecord;

public class MyTest {
	/**
	 * 	
	 * 	Monday       1   Mon
		Tuesday      2   Tues
		Wednesday    3   Web
		Thursday     4   Thurs
		Friday       5   Fir
		Saturday     6   Sat
		Sunday       7   Sun
		
		
		
	 * @param args
	 * @throws Exception
	
	 */
	
	public boolean judgeLeapYear(int year){
		//闰年，非整百的年份能被4整除的
		//      整百的年份能被400整除的
		if(year%100==0){
			if(year%400==0){
				return true;
			}else{
				return false;
			}
		}else{
			if(year%4==0){
				return true;
			}else{
				return false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		/*System.out.println("--------------------------------------------------");
		Calendar calendar=Calendar.getInstance();
		calendar.set(2015, 5, 1, 0, 0,0);
		Date date=calendar.getTime();
		
		Department department=new Department();
		department.setDepartmentId(1);
		Staff staff=new Staff();
		staff.setStaffId(1);
		
		String com="HHHHHHHHHHHHHHHHHHHHH";
		
		WeekMeetingRecord weekMeetingRecord=new WeekMeetingRecord();
		
		weekMeetingRecord.setUploadDepartment(department);
		weekMeetingRecord.setUploadStaff(staff);
		weekMeetingRecord.setUploadTime(date);
		weekMeetingRecord.setWeekMeetingRecordContent(com);
		
		String sql="insert into week_meeting_record(week_meeting_record_content,upload_time,upload_staff,upload_department) values(?,?,?,?);";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, weekMeetingRecord.getWeekMeetingRecordContent());
			preparedStatement.setTimestamp(2, new Timestamp(weekMeetingRecord.getUploadTime().getTime()));
			preparedStatement.setInt(3, weekMeetingRecord.getUploadStaff().getStaffId());
			preparedStatement.setInt(4, weekMeetingRecord.getUploadDepartment().getDepartmentId());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
			System.out.println("========================================================");
		}
		System.out.println("-------------------------------------------------------");*/
		/*System.out.println("--------------------------------------------------");
		WeekMeetingRecord weekMeetingRecord=new WeekMeetingRecord();
		String sql="select * from week_meeting_record";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				weekMeetingRecord.setUploadTime(resultSet.getTimestamp("upload_time"));
			}
			System.out.println(weekMeetingRecord.getUploadTime().getTime());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		System.out.println("--------------------------------------------------");*/
		/*Calendar calendar=Calendar.getInstance();
		calendar.set(2015, 5, 2,0,0,0);
		System.out.println(calendar.get(Calendar.YEAR)+"年");
		System.out.println((calendar.get(Calendar.MONTH))+"月");
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH)+"号");
		System.out.println(calendar.getTime());*/
		
		/*long time=System.currentTimeMillis();
		System.out.println(time);
		Date date=new Date(time);
		long t=date.getTime();
		System.out.println(t);*/
		/*Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.HOUR));
		System.out.println(calendar.get(Calendar.MINUTE));
		System.out.println(calendar.get(Calendar.SECOND));*/
		long thisTime=System.currentTimeMillis();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(thisTime));
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(week);
	}
}
