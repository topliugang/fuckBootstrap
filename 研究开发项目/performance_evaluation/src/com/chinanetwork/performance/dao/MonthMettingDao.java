package com.chinanetwork.performance.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.MonthMeetingRecord;
import com.chinanetwork.performance.util.DataBaseUtil;
import com.chinanetwork.performance.dao.StaffManageDao;

public class MonthMettingDao {


	private StaffManageDao staffManageDao = new StaffManageDao();
	private DepartmentManageDao departmentManageDao = new DepartmentManageDao();
	
	public List<MonthMeetingRecord> selectMonthMeetingLoad(Department department,Date startDate,Date endDate)throws Exception{
		List<MonthMeetingRecord> monthMeetingRecordList=new ArrayList<MonthMeetingRecord>();
		String sql="select month_meeting_record_id " +
				"from month_meeting_record " +
				"where upload_department=? and unic_timestamp(upload_time) between ? and ?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, department.getDepartmentId());
			preparedStatement.setLong(2, startDate.getTime());
			preparedStatement.setLong(3, endDate.getTime());
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				MonthMeetingRecord monthMeetingRecord=new MonthMeetingRecord();
				monthMeetingRecord.setMonthMeetingRecordId(resultSet.getInt("month_meeting_record_id"));
				monthMeetingRecordList.add(monthMeetingRecord);
			}
			
			return monthMeetingRecordList;
		}catch(Exception e){
			e.printStackTrace();
			return monthMeetingRecordList;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	/**
	 * 从数据库中查找所有上传周例会的部门List
	 * @return
	 * @throws Exception
	 */
	public List<Department> selectMonthMeetingLoadDepartments()throws Exception{
		List<Department> departmentList=new ArrayList<Department>();
		String sql="select distinct d.department_id,d.department_name,d.higher_department_id "+
					"from department d,department_score_item dsi "+ 
					"where dsi.department_id=d.department_id "+
					"and dsi.department_id not in("+
							"select ind.department_id "+
							"from department ind,staff s,post p "+
							"where ind.department_id=s.department_id and s.post_id=p.post_id "+ 
							"and dsi.score_in_use=1 "+
							"and p.post_name in ('区营业部科室科长','区营业部副经理','总经理','区营业部经理'))";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Department department=new Department();
				department.setDepartmentId(resultSet.getInt("d.department_id"));
				department.setDepartmentName(resultSet.getString("d.department_name"));
				department.setHigherDepartmentId(resultSet.getInt("d.higher_department_id"));
				departmentList.add(department);
			}
			return departmentList;
		}catch(Exception e){
			e.printStackTrace();
			return departmentList;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public void insertMonthMeetingRecord(MonthMeetingRecord monthMeetingRecord) throws Exception{
		String sql="insert into month_meeting_record (month_meeting_record_content,upload_time,upload_staff,upload_department) values(?,?,?,?);";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, monthMeetingRecord.getMonthMeetingRecordContent());
			preparedStatement.setTimestamp(2, new Timestamp(monthMeetingRecord.getUploadTime().getTime()));
			preparedStatement.setInt(3, monthMeetingRecord.getUploadStaff().getStaffId());
			preparedStatement.setInt(4, monthMeetingRecord.getUploadDepartment().getDepartmentId());
			
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	
	public List<MonthMeetingRecord> selectMonthMeetingRecordsByDepartment(Department department)throws Exception{
		List<MonthMeetingRecord> monthMeetingRecords=new ArrayList<MonthMeetingRecord>();
		String sql="select wmr.upload_staff,s.staff_name,wmr.upload_department,d.department_name," +
				   "wmr.month_meeting_record_id,wmr.month_meeting_record_content,wmr.upload_time "+ 
				   "from month_meeting_record wmr,staff s,department d "+  
				   "where  wmr.upload_staff=s.staff_id and wmr.upload_department=d.department_id "+ 
				   "and wmr.upload_department=?;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, department.getDepartmentId());
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				MonthMeetingRecord monthMeetingRecord=new MonthMeetingRecord();
				Staff staff=new Staff();
				staff.setStaffId(resultSet.getInt("wmr.upload_staff"));
				staff.setStaffName(resultSet.getString("s.staff_name"));
				Department dep=new Department();
				dep.setDepartmentId(resultSet.getInt("wmr.upload_department"));
				dep.setDepartmentName(resultSet.getString("d.department_name"));
				monthMeetingRecord.setMonthMeetingRecordId(resultSet.getInt("wmr.month_meeting_record_id"));
				monthMeetingRecord.setUploadTime(resultSet.getTimestamp("wmr.upload_time"));
				monthMeetingRecord.setUploadStaff(staff);
				monthMeetingRecord.setUploadDepartment(dep);
				monthMeetingRecord.setMonthMeetingRecordContent(resultSet.getString("wmr.month_meeting_record_content"));
				monthMeetingRecords.add(monthMeetingRecord);
			}
			
			return monthMeetingRecords;
		}catch(Exception e){
			e.printStackTrace();
			return monthMeetingRecords;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public List<MonthMeetingRecord> selectAllMonthMeetingRecord(int startRecord,int pageSize)throws Exception{
		List<MonthMeetingRecord> monthMeetingRecordList=new ArrayList<MonthMeetingRecord>();
		String sql="select wmr.month_meeting_record_id,wmr.upload_staff,s.staff_name,wmr.upload_department,d.department_name,wmr.upload_time " +
				   "from month_meeting_record wmr,staff s,department d " +
				   "where wmr.upload_staff=s.staff_id " +
				   "and wmr.upload_department=d.department_id " +
				   "order by upload_time desc limit ?,?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, startRecord);
			preparedStatement.setInt(2, pageSize);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Staff staff=new Staff();
				staff.setStaffId(resultSet.getInt("wmr.upload_staff"));
				staff.setStaffName(resultSet.getString("s.staff_name"));
				Department department=new Department();
				department.setDepartmentId(resultSet.getInt("wmr.upload_department"));
				department.setDepartmentName(resultSet.getString("d.department_name"));
				MonthMeetingRecord monthMeetingRecord=new MonthMeetingRecord();
				monthMeetingRecord.setMonthMeetingRecordId(resultSet.getInt("wmr.month_meeting_record_id"));
				monthMeetingRecord.setUploadStaff(staff);
				monthMeetingRecord.setUploadDepartment(department);
				monthMeetingRecord.setUploadTime(resultSet.getTimestamp("wmr.upload_time"));
				monthMeetingRecordList.add(monthMeetingRecord);
			}
			return monthMeetingRecordList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public List<MonthMeetingRecord> selectAllMonthMeetingRecord()throws Exception{
		List<MonthMeetingRecord> monthMeetingRecordList=new ArrayList<MonthMeetingRecord>();
		String sql="select wmr.month_meeting_record_id,wmr.upload_staff,s.staff_name,wmr.upload_department,d.department_name,wmr.upload_time " +
				   "from month_meeting_record wmr,staff s,department d " +
				   "where wmr.upload_staff=s.staff_id " +
				   "and wmr.upload_department=d.department_id " +
				   "order by upload_time desc ;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Staff staff=new Staff();
				staff.setStaffId(resultSet.getInt("wmr.upload_staff"));
				staff.setStaffName(resultSet.getString("s.staff_name"));
				Department department=new Department();
				department.setDepartmentId(resultSet.getInt("wmr.upload_department"));
				department.setDepartmentName(resultSet.getString("d.department_name"));
				MonthMeetingRecord monthMeetingRecord=new MonthMeetingRecord();
				monthMeetingRecord.setMonthMeetingRecordId(resultSet.getInt("wmr.month_meeting_record_id"));
				monthMeetingRecord.setUploadStaff(staff);
				monthMeetingRecord.setUploadDepartment(department);
				monthMeetingRecord.setUploadTime(resultSet.getTimestamp("wmr.upload_time"));
				monthMeetingRecordList.add(monthMeetingRecord);
			}
			return monthMeetingRecordList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public int selectQuantityOfMonthMeetingRecord()throws Exception{
		int quantity=0;
		String sql="select count(*) quantity from month_meeting_record";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				quantity=resultSet.getInt("quantity");
			}
			return quantity;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public MonthMeetingRecord selectMonthMeetingRecordById(int monthMeetingRecordId)throws Exception{
		MonthMeetingRecord monthMeetingRecord=null;
		String sql="select month_meeting_record_id,month_meeting_record_content,upload_time,upload_staff,upload_department " +
				"from month_meeting_record where month_meeting_record_id=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, monthMeetingRecordId);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				Staff staff=staffManageDao.getStaffById(resultSet.getInt("upload_staff"));
				Department department = departmentManageDao.getDepartmentById(resultSet.getInt("upload_department"));
				monthMeetingRecord=new MonthMeetingRecord(resultSet.getInt("month_meeting_record_id"), 
						resultSet.getString("month_meeting_record_content"), 
						resultSet.getTimestamp("upload_time"), 
						staff, department);
			}
			return monthMeetingRecord;
		}catch(Exception e){
			e.printStackTrace();
			return monthMeetingRecord;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
}
