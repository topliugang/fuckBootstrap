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
import com.chinanetwork.performance.bean.TrainingPlan;
import com.chinanetwork.performance.util.DataBaseUtil;
import com.chinanetwork.performance.dao.StaffManageDao;

public class TrainingPlanDao {


	private StaffManageDao staffManageDao = new StaffManageDao();
	private DepartmentManageDao departmentManageDao = new DepartmentManageDao();
	
	public List<TrainingPlan> selectWeekMeetingLoad(Department department,Date startDate,Date endDate)throws Exception{
		List<TrainingPlan> trainingPlanList=new ArrayList<TrainingPlan>();
		String sql="select training_plan_id " +
				"from training_plan " +
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
				TrainingPlan trainingPlan=new TrainingPlan();
				trainingPlan.setTrainingPlanId(resultSet.getInt("training_plan_id"));
				trainingPlanList.add(trainingPlan);
			}
			
			return trainingPlanList;
		}catch(Exception e){
			e.printStackTrace();
			return trainingPlanList;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	/**
	 * 从数据库中查找所有上传周例会的部门List
	 * @return
	 * @throws Exception
	 */
	public List<Department> selectWeekMeetingLoadDepartments()throws Exception{
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
	
	public void insertTrainingPlan(TrainingPlan trainingPlan) throws Exception{
		String sql="insert into training_plan (training_plan_content,upload_time,upload_staff,upload_department) values(?,?,?,?);";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, trainingPlan.getTrainingPlanContent());
			preparedStatement.setTimestamp(2, new Timestamp(trainingPlan.getUploadTime().getTime()));
			preparedStatement.setInt(3, trainingPlan.getUploadStaff().getStaffId());
			preparedStatement.setInt(4, trainingPlan.getUploadDepartment().getDepartmentId());
			
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	
	public List<TrainingPlan> selectTrainingPlansByDepartment(Department department)throws Exception{
		List<TrainingPlan> trainingPlans=new ArrayList<TrainingPlan>();
		String sql="select wmr.upload_staff,s.staff_name,wmr.upload_department,d.department_name," +
				   "wmr.training_plan_id,wmr.training_plan_content,wmr.upload_time "+ 
				   "from training_plan wmr,staff s,department d "+  
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
				TrainingPlan trainingPlan=new TrainingPlan();
				Staff staff=new Staff();
				staff.setStaffId(resultSet.getInt("wmr.upload_staff"));
				staff.setStaffName(resultSet.getString("s.staff_name"));
				Department dep=new Department();
				dep.setDepartmentId(resultSet.getInt("wmr.upload_department"));
				dep.setDepartmentName(resultSet.getString("d.department_name"));
				trainingPlan.setTrainingPlanId(resultSet.getInt("wmr.training_plan_id"));
				trainingPlan.setUploadTime(resultSet.getTimestamp("wmr.upload_time"));
				trainingPlan.setUploadStaff(staff);
				trainingPlan.setUploadDepartment(dep);
				trainingPlan.setTrainingPlanContent(resultSet.getString("wmr.training_plan_content"));
				trainingPlans.add(trainingPlan);
			}
			
			return trainingPlans;
		}catch(Exception e){
			e.printStackTrace();
			return trainingPlans;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public List<TrainingPlan> selectAllTrainingPlan(int startRecord,int pageSize)throws Exception{
		List<TrainingPlan> trainingPlanList=new ArrayList<TrainingPlan>();
		String sql="select wmr.training_plan_id,wmr.upload_staff,s.staff_name,wmr.upload_department,d.department_name,wmr.upload_time " +
				   "from training_plan wmr,staff s,department d " +
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
				TrainingPlan trainingPlan=new TrainingPlan();
				trainingPlan.setTrainingPlanId(resultSet.getInt("wmr.training_plan_id"));
				trainingPlan.setUploadStaff(staff);
				trainingPlan.setUploadDepartment(department);
				trainingPlan.setUploadTime(resultSet.getTimestamp("wmr.upload_time"));
				trainingPlanList.add(trainingPlan);
			}
			return trainingPlanList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	public List<TrainingPlan> selectAllTrainingPlan()throws Exception{
		List<TrainingPlan> trainingPlanList=new ArrayList<TrainingPlan>();
		String sql="select wmr.training_plan_id,wmr.upload_staff,s.staff_name,wmr.upload_department,d.department_name,wmr.upload_time " +
				   "from training_plan wmr,staff s,department d " +
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
				TrainingPlan trainingPlan=new TrainingPlan();
				trainingPlan.setTrainingPlanId(resultSet.getInt("wmr.training_plan_id"));
				trainingPlan.setUploadStaff(staff);
				trainingPlan.setUploadDepartment(department);
				trainingPlan.setUploadTime(resultSet.getTimestamp("wmr.upload_time"));
				trainingPlanList.add(trainingPlan);
			}
			return trainingPlanList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
	
	/*
	public int selectQuantityOfTrainingPlan()throws Exception{
		int quantity=0;
		String sql="select count(*) quantity from training_plan";
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
	*/
	public TrainingPlan selectTrainingPlanById(int trainingPlanId)throws Exception{
		TrainingPlan trainingPlan=null;
		String sql="select training_plan_id,training_plan_content,upload_time,upload_staff,upload_department " +
				"from training_plan where training_plan_id=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, trainingPlanId);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				Staff staff=staffManageDao.getStaffById(resultSet.getInt("upload_staff"));
				Department department = departmentManageDao.getDepartmentById(resultSet.getInt("upload_department"));
				trainingPlan=new TrainingPlan(resultSet.getInt("training_plan_id"), 
						resultSet.getString("training_plan_content"), 
						staff, department,resultSet.getTimestamp("upload_time"));
			}
			return trainingPlan;
		}catch(Exception e){
			e.printStackTrace();
			return trainingPlan;
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}
}
