package com.chinanetwork.performance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScore;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScore;
import com.chinanetwork.performance.util.DataBaseUtil;
import com.chinanetwork.performance.util.MathUtil;

/**
 * 统计查询DAO，主要负责向统计查询功能提供数据支持
 * @author Administrator
 *
 */
public class StatisticalQueryDao {

	/**
	 获取部门得分的列表 根据 year month
	 */
	public List<DepartmentScore> getDepartmentScoreList(int year,int month)throws Exception{
		List<DepartmentScore> departmentScores=new ArrayList<DepartmentScore>();
		
		String sql="select d.department_id,d.department_name,d.higher_department_id,ds.total_score,ds.which_year,ds.which_month,ds.annotation " +
			"from department_score ds,department d " +
			"where ds.department_id=d.department_id " +
			"and ds.which_year = ? and ds.which_month = ? " +
			"order by d.department_id asc; ";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,year);
		preparedStatement.setInt(2,month);
		
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next()){
			Department department=new Department(resultSet.getInt("d.department_id"),resultSet.getString("d.department_name"),resultSet.getInt("d.higher_department_id"));
			DepartmentScore departmentScore=new DepartmentScore(department,resultSet.getFloat("ds.total_score"));
			departmentScore.setYear(resultSet.getInt("ds.which_year"));
			departmentScore.setMonth(resultSet.getInt("ds.which_month"));
			departmentScore.setInstruction(resultSet.getString("ds.annotation"));
			departmentScores.add(departmentScore);
		}
		return departmentScores;
	}
	
	/**
	 获取部门得分 根据 year month departmentId
	 */
	
	public DepartmentScore getDepartmentScore(int departmentId,int year,int month)throws Exception{
		
		String sql="select d.department_id,d.department_name,d.higher_department_id,ds.total_score,ds.which_year,ds.which_month,ds.annotation " +
			"from department_score ds,department d " +
			"where ds.department_id=d.department_id and ds.department_id = ? " +
			"and ds.which_year = ? and ds.which_month = ? " +
			"order by d.department_id asc; ";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,departmentId);
		preparedStatement.setInt(2,year);
		preparedStatement.setInt(3,month);
		DepartmentScore departmentScore = null;
		
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			Department department=new Department(resultSet.getInt("d.department_id"),resultSet.getString("d.department_name"),resultSet.getInt("d.higher_department_id"));
			departmentScore=new DepartmentScore(department,resultSet.getFloat("ds.total_score"));
			departmentScore.setYear(resultSet.getInt("ds.which_year"));
			departmentScore.setMonth(resultSet.getInt("ds.which_month"));
			departmentScore.setInstruction(resultSet.getString("ds.annotation"));
		}
		return departmentScore;
	}	
	
	/**
	 * 根据所填写的员工ID找到对应的得分
	 * 获取一次考评的所有个人得分
	 */
	public List<StaffScore> getStaffScoreList(int year,int month)throws Exception{
		List<StaffScore> staffScoreList=new ArrayList<StaffScore>();
		
		String sql = "select s.staff_id,s.staff_name,d.department_id,d.department_name,p.post_id,p.post_name,ss.staff_score,ss.which_year,ss.which_month,ss.annotation " +
		"from staff s,staff_score ss,department d,post p " +
		"where ss.staff_id=s.staff_id and s.post_id=p.post_id and s.department_id=d.department_id " +
		"and ss.which_year= ? and ss.which_month = ? " +
		"order by s.department_id asc, s.staff_id asc ;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,year);
		preparedStatement.setInt(2,month);

		resultSet=preparedStatement.executeQuery();
		
		while(resultSet.next()){
			Department department=new Department();
			department.setDepartmentId(resultSet.getInt("d.department_id"));
			department.setDepartmentName(resultSet.getString("d.department_name"));
			Post post=new Post();
			post.setPostId(resultSet.getInt("p.post_id"));
			post.setPostName(resultSet.getString("p.post_name"));
			Staff staff=new Staff();
			staff.setStaffId(resultSet.getInt("s.staff_id"));
			staff.setStaffName(resultSet.getString("s.staff_name"));
			staff.setDepartment(department);
			staff.setPost(post);
			
			StaffScore staffScore=new StaffScore();
			staffScore.setStaff(staff);
			staffScore.setTotalScore(resultSet.getFloat("ss.staff_score"));
			staffScore.setInstruction(resultSet.getString("ss.annotation"));
			staffScore.setYear(resultSet.getInt("ss.which_year"));
			staffScore.setMonth(resultSet.getInt("ss.which_month"));
			
			staffScoreList.add(staffScore);
		}
		
		return staffScoreList;
	}
	
	/**
	 * 根据所填写的员工ID, year,  month找到对应的得分
	 */
	public StaffScore getStaffScore(int staffId, int year,int month)throws Exception{
		StaffScore staffScore=null;
		
		String sql = "select s.staff_id,s.staff_name,d.department_id,d.department_name,p.post_id,p.post_name,ss.staff_score,ss.which_year,ss.which_month,ss.annotation " +
		"from staff s,staff_score ss,department d,post p " +
		"where ss.staff_id=s.staff_id and s.post_id=p.post_id and s.department_id=d.department_id " +
		"and ss.which_year= ? and ss.which_month = ? and ss.staff_id = ? " +
		"order by s.department_id asc, s.staff_id asc ;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1,year);
		preparedStatement.setInt(2,month);
		preparedStatement.setInt(3,staffId);
		resultSet=preparedStatement.executeQuery();
		
		if(resultSet.next()){
			Department department=new Department();
			department.setDepartmentId(resultSet.getInt("d.department_id"));
			department.setDepartmentName(resultSet.getString("d.department_name"));
			Post post=new Post();
			post.setPostId(resultSet.getInt("p.post_id"));
			post.setPostName(resultSet.getString("p.post_name"));
			Staff staff=new Staff();
			staff.setStaffId(resultSet.getInt("s.staff_id"));
			staff.setStaffName(resultSet.getString("s.staff_name"));
			staff.setDepartment(department);
			staff.setPost(post);
			
			staffScore=new StaffScore();
			staffScore.setStaff(staff);
			staffScore.setTotalScore(resultSet.getFloat("ss.staff_score"));
			staffScore.setInstruction(resultSet.getString("ss.annotation"));
			staffScore.setYear(resultSet.getInt("ss.which_year"));
			staffScore.setMonth(resultSet.getInt("ss.which_month"));
		}
		return staffScore;
	}
	


	/*
	 * 修改个人得分
	 */
	public boolean updateStaffScore(int staffScoreId,float staffScore,String instruction)throws Exception{
		String sql="update staff_score set staff_score=?,annotation=?,score_date=? where staff_score_id=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1, staffScore);
			preparedStatement.setString(2, instruction);
			preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
			preparedStatement.setInt(4, staffScoreId);
			
			preparedStatement.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	




//===================================================在Excel表格中添加一列，工资列=====================================================
	/**
	 * 根据员工的员工号，查找到其对应的工资等级
	 */
	public float getSalaryByStaffId(int staffId)throws Exception{
		float salary=0;
		String sql="select salary from salary where staff_id=?";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, staffId);
			
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				salary=resultSet.getFloat("salary");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return salary;
	}
	/*
	 * 已经被打分的年份
	 * 
	 */
	public List<Integer> departmentScoredYears() throws Exception
	{
		List<Integer> ret = null;
		String sql="select which_year from department_score group by which_year;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			if(ret == null)
				ret = new ArrayList<Integer>();
			ret.add(resultSet.getInt("which_year"));
		}
		return ret;
	}
	/*
	 * 已经被打分的月份
	 * 
	 */
	public List<Integer> departmentScoredMonths() throws Exception
	{
		List<Integer> ret = null;
		String sql="select which_month from department_score group by which_month;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			if(ret == null)
				ret = new ArrayList<Integer>();
			ret.add(resultSet.getInt("which_month"));
		}
		return ret;
	}
	
	/*
	 * 已经被打分的年份
	 * 
	 */
	public List<Integer> staffScoredYears() throws Exception
	{
		List<Integer> ret = null;
		String sql="select which_year from staff_score group by which_year;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			if(ret == null)
				ret = new ArrayList<Integer>();
			ret.add(resultSet.getInt("which_year"));
		}
		return ret;
	}
	
	/*
	 * 已经被打分的月份
	 * 
	 */
	public List<Integer> staffScoredMonths() throws Exception
	{
		List<Integer> ret = null;
		String sql="select which_month from staff_score group by which_month;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			if(ret == null)
				ret = new ArrayList<Integer>();
			ret.add(resultSet.getInt("which_month"));
		}
		return ret;
	}

	
	/*
	 * 部门打分描述
	 * 构造待提高条目字符串
	 * 
	 */
	
	public String getDepartmentComment(int departmentId, int year, int month) throws Exception
	{
		String sql = "select r.department_to_id,r.score_item_id, avg(r.score) as score, "+
					"i.score_value, i.score_item_name, i.score_description "+
					"from department_score_record r, department_score_item i "+
					"where r.score < i.score_value "+
					"and r.department_to_id = ? "+
					"and r.score_item_id = i.score_item_id "+
					"and r.which_year = ? "+
					"and r.which_month = ? "+
					"group by r.score_item_id "+
					"order by r.department_to_id; ";
		
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		Connection connection=null;
		connection=DataBaseUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, departmentId);
		preparedStatement.setInt(2, year);
		preparedStatement.setInt(3, month);
		resultSet=preparedStatement.executeQuery();
		String ret = "";
		
		while(resultSet.next())
		{
			ret+=
			"条目内容："+resultSet.getString("score_item_name")+
			" 条目描述："+resultSet.getString("score_description")+
			" 得分："+resultSet.getString("score")+
			" 满分："+resultSet.getString("score_value")+"；";
		}
		return ret;
		
	}
	
	 
	
}
