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

public class StaffManageDao {

	public List<Staff> getAllStaff() throws Exception
	{
		List<Staff> staffs = null;
		
		String sql1=" select s.staff_id, s.staff_name, s.staff_sex, s.staff_password, " +
				"s.department_id, s.post_id, s.role_id, " +
				"d.department_name, d.higher_department_id, d.nature,r.role_name, r.role_description, p.post_name " +
				"from staff s, department d, role r, post p " +
				"where s.department_id=d.department_id and " +
				"s.role_id=r.role_id and s.post_id=p.post_id " +
				"order by s.staff_id asc;";

		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);

		ResultSet rs=preparedStatement.executeQuery();

		while(rs.next()){
			if(staffs == null)
				staffs = new ArrayList<Staff>();
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffSex(rs.getString("staff_sex"));
			staff.setPassword(rs.getString("staff_password"));
			Department department=new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs.getInt("higher_department_id"));
			department.setNature(rs.getInt("d.nature"));
			staff.setDepartment(department);
			staff.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("role_description")));
			staff.setPost(new Post(rs.getInt("post_id"), rs.getString("post_name")));
			
			staffs.add(staff);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return staffs;
	}
	
	//------------------------------------------------------分页代码开始
	/**
	 * 分页Dao
	 * @param pageStart
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Staff> getAllStaffPage(int pageStart,int pageSize)throws Exception{
		List<Staff> staffList=new ArrayList<Staff>();
		
		String sql=" select s.staff_id, s.staff_name, s.staff_no, s.staff_sex, s.staff_password, " +
		"s.department_id, s.post_id, s.role_id, " +
		"d.department_name, d.higher_department_id, r.role_name, r.role_description, p.post_name " +
		"from staff s, department d, role r, post p " +
		"where s.department_id=d.department_id and " +
		"s.role_id=r.role_id and s.post_id=p.post_id order by staff_id asc limit ?,?";
		
		Connection  connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, pageStart);
			preparedStatement.setInt(2, pageSize);
			
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Staff staff = new Staff();
				staff.setStaffId(resultSet.getInt("staff_id"));
				staff.setStaffName(resultSet.getString("staff_name"));
				staff.setStaffNo(resultSet.getInt("staff_no"));
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
	/**
	 * 获取总记录数
	 * @return
	 * @throws Exception
	 */
	public int getTotalRecored()throws Exception{
		int count=0;
		String sql="select count(*) crecored from staff;";
		
		Connection  connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				count=resultSet.getInt("crecored");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return count;
	}
	
//-------------------------------------------------------------------分页代码结束
	public void addStaff(Staff staff) throws Exception {
		String sql1=" insert into staff(staff_id, staff_name, staff_sex, staff_password, department_id, role_id, post_id) " +
				"values(?, ?, ?, ?, ?, ?, ? ) ;";
		Connection conn = null;
		PreparedStatement ps = null;
		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, staff.getStaffId());
		ps.setString(2, staff.getStaffName());
		ps.setString(3, staff.getStaffSex());
		ps.setString(4, staff.getPassword());
		ps.setInt(5, staff.getDepartment().getDepartmentId());
		ps.setInt(6, staff.getRole().getRoleId());
		ps.setInt(7, staff.getPost().getPostId());
		ps.executeUpdate();
		DataBaseUtil.close(ps, conn);
	}

	public void deleteStaff(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		String sql1=" delete from staff where staff_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, parseInt);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
		
	}

	public Staff getStaffById(int id) throws Exception {
		Staff staff = null;
		String sql1=" select s.staff_id, s.staff_name, s.staff_sex, s.staff_password, " +
		"s.department_id, s.post_id, s.role_id, " +
		"d.department_name, d.higher_department_id, r.role_name, r.role_description, p.post_name " +
		"from staff s, department d, role r, post p " +
		"where s.department_id=d.department_id and " +
		"s.role_id=r.role_id and s.post_id=p.post_id " +
		"and s.staff_id = ? ";

		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs=preparedStatement.executeQuery();
		
		if(rs.next()){
			staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffSex(rs.getString("staff_sex"));
			staff.setPassword(rs.getString("staff_password"));
			staff.setDepartment(new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs.getInt("higher_department_id")));
			staff.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("role_description")));
			staff.setPost(new Post(rs.getInt("post_id"), rs.getString("post_name")));
		}
		return staff;
	}

	public void modifyStaffById(int id, Staff staffToModify) throws Exception {
		String sql1="update staff set staff_name=?, staff_sex=?, staff_password=?, " +
				"department_id=?, role_id=?, post_id=? " +
				"where staff_id=? ;"; 
		Connection conn = null;
		PreparedStatement ps = null;
		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, staffToModify.getStaffName());
		ps.setString(2, staffToModify.getStaffSex());
		ps.setString(3, staffToModify.getPassword());
		ps.setInt(4, staffToModify.getDepartment().getDepartmentId());
		ps.setInt(5, staffToModify.getRole().getRoleId());
		ps.setInt(6, staffToModify.getPost().getPostId());
		ps.setInt(7, id);
		ps.executeUpdate();
		DataBaseUtil.close(ps, conn);
		
	}

	public List<Float> getSalarysToList(List<Staff> staffsToList) throws Exception {
		if(staffsToList == null)
			return null;
		List<Float> salarysToList = new ArrayList<Float>();
		
		class Salary{
			public Salary(int staffId, float salary) {
				// TODO Auto-generated constructor stub
				this.staffId = staffId;
				this.salary = salary;
			}
			int staffId; 
			float salary;
		};
		List<Salary> salarys = null;
		
		String sql1=" select staff_id, salary from salary; " ;
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()){
			if(salarys == null)
				salarys = new ArrayList<Salary>();
			salarys.add(new Salary(rs.getInt("staff_id"), rs.getFloat("salary")));
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		
		if(salarys == null)
			return null;
		
		for(int i=0; i<staffsToList.size(); i++)
		{
			float salary = 0;
			for(int j=0; j<salarys.size(); j++)
			{
				if(salarys.get(j).staffId == staffsToList.get(i).getStaffId())
				{
					salary = salarys.get(j).salary;
					break;
				}
			}
			salarysToList.add(new Float(salary));
		}
		return salarysToList;
	}
	
	
	public List<Staff> getScoredStaff() throws Exception
	{
		List<Staff> staffs = null;
		
		String sql1=" select s.staff_id, s.staff_name, s.staff_sex, s.staff_password, "+ 
					"s.department_id, s.post_id, s.role_id,  "+
					"d.department_name, d.higher_department_id, d.nature,r.role_name, r.role_description, p.post_name "+ 
					"from staff s, department d, role r, post p, staff_score_record record "+
					"where s.department_id=d.department_id and  "+
					"s.role_id=r.role_id and s.post_id=p.post_id "+
					"and record.scored_to_staff_id = s.staff_id "+
					"group by s.staff_id "+
					"order by s.staff_id asc;";

		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);

		ResultSet rs=preparedStatement.executeQuery();

		while(rs.next()){
			if(staffs == null)
				staffs = new ArrayList<Staff>();
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setStaffName(rs.getString("staff_name"));
			staff.setStaffSex(rs.getString("staff_sex"));
			staff.setPassword(rs.getString("staff_password"));
			Department department=new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs.getInt("higher_department_id"));
			department.setNature(rs.getInt("d.nature"));
			staff.setDepartment(department);
			staff.setRole(new Role(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("role_description")));
			staff.setPost(new Post(rs.getInt("post_id"), rs.getString("post_name")));
			
			staffs.add(staff);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return staffs;
	}
	
	public float getSalaryByStaffId(int staffId)throws Exception
	{	
		float salary = 0;
		String sql1 = "select salary from salary where staff_id = ?; ";

		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, staffId);
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			salary = rs.getFloat("salary");
		}
		return salary;
	}

	
	
}
