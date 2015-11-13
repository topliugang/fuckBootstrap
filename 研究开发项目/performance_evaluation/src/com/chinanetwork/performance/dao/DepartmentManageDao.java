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
import com.chinanetwork.performance.util.DataBaseUtil;

public class DepartmentManageDao {

	public List<Department> getAllDepartment() throws Exception {
		List<Department> departments = null;

		String sql1 = " select department_id, department_name, higher_department_id from department;";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (departments == null)
				departments = new ArrayList<Department>();
			Department department = new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs
							.getInt("higher_department_id"));
			departments.add(department);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return departments;
	}

	// ------------------------------------------------------分页代码开始
	/**
	 * 分页Dao
	 * 
	 * @param pageStart
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Department> getAllDepartmentPage(int pageStart, int pageSize)
			throws Exception {
		List<Department> departments = null;

		String sql1 = " select department_id, department_name, higher_department_id from department "
				+ "order by department_id asc limit ?,?; ";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);

		preparedStatement.setInt(1, pageStart);
		preparedStatement.setInt(2, pageSize);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (departments == null)
				departments = new ArrayList<Department>();
			Department department = new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs
							.getInt("higher_department_id"));
			departments.add(department);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return departments;

	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getTotalRecored() throws Exception {
		int count = 0;
		String sql = "select count(*) crecored from department;";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				count = resultSet.getInt("crecored");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return count;
	}

	// -------------------------------------------------------------------分页代码结束

	public void addDepartment(Department department) throws Exception {
		String sql1 = " insert into department(department_name, higher_department_id) values(?, ?) ;";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, department.getDepartmentName());
		ps.setInt(2, department.getHigherDepartmentId());
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
	}

	public void deleteDepartment(int id) throws Exception {
		// TODO Auto-generated method stub
		String sql1 = " delete from department where department_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);

	}

	public Department getDepartmentById(int id) throws Exception {
		Department department = null;
		String sql1 = " select department_id, department_name, higher_department_id from department where department_id=? ";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			department = new Department(rs.getInt("department_id"), rs
					.getString("department_name"), rs
					.getInt("higher_department_id"));
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return department;
	}

	public void modifyDepartmentById(Department departmentToModify)
			throws Exception {
		// TODO Auto-generated method stub
		String sql1 = " update department set department_id = ?, department_name = ?, higher_department_id = ? where department_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, departmentToModify.getDepartmentId());
		ps.setString(2, departmentToModify.getDepartmentName());
		ps.setInt(3, departmentToModify.getHigherDepartmentId());
		ps.setInt(4, departmentToModify.getDepartmentId());
		ps.executeUpdate();
		DataBaseUtil.close(ps, conn);

	}
	
	public List<Integer> getStaffIdsByDepartmentId(int departmentId) throws Exception {
		List<Integer> staffIds = null;
		String sql1="select staff_id from staff where department_id = ? ";

		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, departmentId);
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next()){
			if(staffIds == null)
				staffIds = new ArrayList<Integer>();
			staffIds.add(rs.getInt("staff_id"));
		}
		return staffIds;
	}
	
	
	public List<Staff> getStaffByDepartment(Department department) throws Exception
	{
		List<Staff> staffs = null;
		
		String sql1=" select s.staff_id, s.staff_name, s.staff_sex, s.staff_password, " +
				"s.department_id, s.post_id, s.role_id, " +
				"d.department_name, d.higher_department_id, r.role_name, r.role_description, p.post_name " +
				"from staff s, department d, role r, post p " +
				"where s.department_id=d.department_id and " +
				"s.role_id=r.role_id and s.post_id=p.post_id" +
				"and s.department_id = ?; ";

		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, department.getDepartmentId());
		ResultSet rs=preparedStatement.executeQuery();

		while(rs.next()){
			if(staffs == null)
				staffs = new ArrayList<Staff>();
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffSex(rs.getString("staff_sex"));
			staff.setPassword(rs.getString("staff_password"));
			staff.setDepartment(new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs.getInt("higher_department_id")));
			staff.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("role_description")));
			staff.setPost(new Post(rs.getInt("post_id"), rs.getString("post_name")));
			
			staffs.add(staff);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return staffs;
	}


}
