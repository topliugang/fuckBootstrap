package com.chinanetwork.performance.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.util.DataBaseUtil;

public class LoginDao {

	
	/*
	public Staff selectStaffByID(int staffId) throws Exception {
		Staff staff = null;

		String sql1 = "select * from staff where staff_id=?";
		String sql2 = "select * from department where department_id=?";
		String sql3 = "select * from post where post_id=?";
		String sql4 = "select * from";

		Connection connection = null;
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		ResultSet reultSet = null;

		//
		return staff;
	}

*/
	public Staff selectStaffByIdAndPassword(String username, String staff_password)
			throws Exception {
		System.out.println("logging in...");
		Staff staff = null;
		String sql1 = " select staff_id, staff_name, staff_no, staff_sex, staff_password, "
				+ " staff.department_id, department_name, higher_department_id, "
				+ " staff.role_id, role_name, role_description, staff.post_id, post_name "
				+ " from staff, department, role, post "
				+ " where staff.department_id = department.department_id "
				+ " and staff.role_id = role.role_id "
				+ " and staff.post_id = post.post_id "
				+ " and staff_no=? and staff_password=? ";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, staff_password);
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffNo(rs.getInt("staff_no"));
			staff.setStaffSex(rs.getString("staff_sex"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setPassword(rs.getString("staff_password"));
			staff.setDepartment(new Department(rs.getInt("department_id"), rs
					.getString("department_name"), rs
					.getInt("higher_department_id")));
			staff.setRole(new Role(rs.getInt("role_id"), rs
					.getString("role_name"), rs.getString("role_description")));
			staff.setPost(new Post(rs.getInt("post_id"), rs
					.getString("post_name")));
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return staff;

	}

	public List<Funcpage> getFuncpages(Staff staff) throws Exception {
		
		RoleManageDao manageDao = new RoleManageDao();
		return manageDao.getFuncpages(staff.getRole().getRoleId());
	}
}
