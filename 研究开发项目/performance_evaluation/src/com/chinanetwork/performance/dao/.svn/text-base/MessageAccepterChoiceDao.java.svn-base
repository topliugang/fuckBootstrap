package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffClass;
import com.chinanetwork.performance.util.DataBaseUtil;

/**
 * 消息接受者选择系统的DAO
 * @author Administrator
 *
 */
public class MessageAccepterChoiceDao {

	public List<Department> getAllDepartment()throws Exception{
		List<Department> departmentList=new ArrayList<Department>();
		String sql="select * from department";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Department department=new Department();
				department.setDepartmentId(resultSet.getInt("department_id"));
				department.setDepartmentName(resultSet.getString("department_name"));
				department.setHigherDepartmentId(resultSet.getInt("higher_department_id"));
				
				departmentList.add(department);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return departmentList;
	}
	
	public List<Staff> getStaffByDepartmentId(int departmentId)throws Exception{
		List<Staff> staffList=new ArrayList<Staff>();
		String sql=" select s.staff_id, s.staff_name, s.staff_sex, s.staff_password, " +
		"s.department_id, s.post_id, s.role_id, " +
		"d.department_name, d.higher_department_id, r.role_name, r.role_description, p.post_name " +
		"from staff s, department d, role r, post p " +
		"where s.department_id=d.department_id and " +
		"s.role_id=r.role_id and s.post_id=p.post_id and s.department_id=?;";
		
		Connection  connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, departmentId);
			resultSet=preparedStatement.executeQuery();
			
			
			while(resultSet.next()){
				Staff staff = new Staff();
				staff.setStaffId(resultSet.getInt("staff_id"));
				staff.setStaffName(resultSet.getString("staff_name"));
				staff.setStaffSex(resultSet.getString("staff_sex"));
				staff.setPassword(resultSet.getString("staff_password"));
				staff.setDepartment(new Department(resultSet.getInt("department_id"),
						resultSet.getString("department_name"), resultSet.getInt("higher_department_id")));
				staff.setRole(new Role(resultSet.getInt("role_id"), resultSet.getString("role_name"), resultSet.getString("role_description")));
				staff.setPost(new Post(resultSet.getInt("post_id"), resultSet.getString("post_name")));
				
				staffList.add(staff);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return staffList;
	}
	
	public List<StaffClass> selectAllStaffClass()throws Exception{
		List<StaffClass> staffClassList=new ArrayList<StaffClass>();
		String sql="select class_id,class_name,class_comment from staff_class;";
		Connection  connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				StaffClass staffClass=new StaffClass();
				staffClass.setClassId(resultSet.getString("class_id"));
				staffClass.setClassName(resultSet.getString("class_name"));
				staffClass.setClassComment(resultSet.getString("class_comment"));
				
				staffClassList.add(staffClass);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffClassList;
	}

	public List<Staff> selectStaffsByClassId(String classId)throws Exception{
		List<Staff> staffList=new ArrayList<Staff>();
		String sql="select staff_id,staff_name " +
				"from staff " +
				"where class_id like ?;";
		Connection  connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+classId+"%");
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Staff staff=new Staff();
				staff.setStaffId(resultSet.getInt("staff_id"));
				staff.setStaffName(resultSet.getString("staff_name"));
				
				staffList.add(staff);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffList;
	}
}
