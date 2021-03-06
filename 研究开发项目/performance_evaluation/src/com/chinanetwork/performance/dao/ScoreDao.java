package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.ScoreItemCategory;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.DataBaseUtil;

public class ScoreDao {

	//返回需要打分的部门
	public List<Department> getNoScoreDepartment(Department departmentFrom, int year, int month, int... categorys)
			throws Exception {

		List<Department> departments = null;
		
		String categorySql = null;
		if(categorys.length != 0)
		{
			//and b.score_category_id in(1, 2, 3..)
			categorySql = "and b.score_category_id in(";
			for(int i=0; i<categorys.length; i++)
			{
				categorySql += categorys[i];
				if(i < categorys.length-1)
					categorySql += ",";
			}
			categorySql += ")";
		}
		
		String sql1 = "select c.department_id, c.department_name, c.higher_department_id "
				+ "from department_score_relation as a, department_score_item as b, department as c, score_category as d "
				+ "where a.department_id = ? "
				+ "and a.score_item_id not in (select score_item_id from department_score_record where department_from_id = ? and which_year = ? and which_month = ? ) "
				+ "and a.score_item_id = b.score_item_id "
				+ "and b.department_id = c.department_id "
				+ "and b.score_category_id = d.score_category_id "
				+ categorySql
				+ "group by c.department_id; ";
		

		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, departmentFrom.getDepartmentId());
		preparedStatement.setInt(2, departmentFrom.getDepartmentId());
		preparedStatement.setInt(3, year);
		preparedStatement.setInt(4, month);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (departments == null)
				departments = new ArrayList<Department>();
			Department department = new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs
							.getInt("higher_department_id"));
			if (!departments.contains(department))
				departments.add(department);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return departments;
	}
	
	


	//返回部门条目
	public List<DepartmentScoreItem> getDepartmentScoreItem(
			Department departmentTo, Department departmentFrom, int year, int month, int... categorys)
			throws Exception {
		// TODO Auto-generated method stub
		List<DepartmentScoreItem> items = null;
		String categorySql = null;
		if(categorys.length != 0)
		{
			//and b.score_category_id in(1, 2, 3..)
			categorySql = "and b.score_category_id in(";
			for(int i=0; i<categorys.length; i++)
			{
				categorySql += categorys[i];
				if(i < categorys.length-1)
					categorySql += ",";
			}
			categorySql += ")";
		}
		String sql1 = "select b.score_item_id, b.score_item_name, b.score_description, b.score_value, b.score_reference, "
				+ "b.score_calculation_method, b.department_id, c.department_name, c.higher_department_id, "
				+ "b.score_category_id, d.score_category_name, "
				+ "b.score_creation_date, b.score_in_use "
				+ "from department_score_relation as a, department_score_item as b, department as c, score_category as d "
				+ "where a.department_id = ? "
				+ "and a.score_item_id not in (select score_item_id from department_score_record where department_from_id = ? and which_year = ? and which_month = ? )  "
				+ "and a.score_item_id = b.score_item_id "
				+ "and b.department_id = c.department_id "
				+ "and b.score_category_id = d.score_category_id "
				+ "and c.department_id = ? " 
				+ categorySql + "; ";
		


		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, departmentFrom.getDepartmentId());
		preparedStatement.setInt(2, departmentFrom.getDepartmentId());
		preparedStatement.setInt(3, year);
		preparedStatement.setInt(4, month);
		preparedStatement.setInt(5, departmentTo.getDepartmentId());
		
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (items == null)
				items = new ArrayList<DepartmentScoreItem>();

			DepartmentScoreItem departmentScoreItem = new DepartmentScoreItem(
					rs.getInt("score_item_id"),
					rs.getString("score_item_name"), rs
							.getString("score_description"), rs
							.getFloat("score_value"), rs
							.getString("score_reference"), rs
							.getString("score_calculation_method"),
					new Department(rs.getInt("department_id"), rs
							.getString("department_name"), rs
							.getInt("higher_department_id")),
					new ScoreItemCategory(rs.getInt("score_category_id"), rs
							.getString("score_category_name")), rs
							.getDate("score_creation_date"), rs
							.getInt("score_in_use"));
			items.add(departmentScoreItem);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return items;

	}


	//返回已经打完分的部门
	public List<Department> getScoreDepartment(Department departmentFrom, int year, int month, int... categorys)
			throws Exception {
		List<Department> departments = null;
		String categorySql = null;
		if(categorys.length != 0)
		{
			//and b.score_category_id in(1, 2, 3..)
			categorySql = "and b.score_category_id in(";
			for(int i=0; i<categorys.length; i++)
			{
				categorySql += categorys[i];
				if(i < categorys.length-1)
					categorySql += ",";
			}
			categorySql += ")";
		}
		String sql1 = "select c.department_id, c.department_name, c.higher_department_id "
				+ "from department_score_record as a, department_score_item as b, department as c, score_category as d "
				+ "where a.department_from_id = ? "
				+ "and a.score_item_id = b.score_item_id "
				+ "and b.department_id = c.department_id "
				+ "and b.score_category_id = d.score_category_id "
				+ "and a.which_year = ? and a.which_month = ?  "
				+ categorySql
				+ "group by c.department_id;";
		


		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, departmentFrom.getDepartmentId());
		preparedStatement.setInt(2, year);
		preparedStatement.setInt(3, month);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (departments == null)
				departments = new ArrayList<Department>();
			Department department = new Department(rs.getInt("department_id"),
					rs.getString("department_name"), rs
							.getInt("higher_department_id"));
			if (!departments.contains(department))
				departments.add(department);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return departments;
	}

	//返回打分记录
	/*
	 * departmentTo departmentFrom 为null时候 不限定 查出所有的 但是不可以同时为null
	 * category 不填为不限定
	 */
	public List<DepartmentScoreRecord> getDepartmentScoreRecord(
			Department departmentTo, Department departmentFrom, int year, int month, int... categorys)
			throws Exception {

		List<DepartmentScoreRecord> records = null;
		//categorys
		//departmentFrom
		//departmentTo
		String categorySql = "";
		String depFrom = "";
		String depTo = "";
		if(categorys.length != 0)
		{
			//and b.score_category_id in(1, 2, 3..)
			categorySql = "and b.score_category_id in(";
			for(int i=0; i<categorys.length; i++)
			{
				categorySql += categorys[i];
				if(i < categorys.length-1)
					categorySql += ",";
			}
			categorySql += ")";
		}
		if(departmentFrom != null)
		{
			depFrom = "and a.department_from_id = "+departmentFrom.getDepartmentId()+" ";
		}
		if(departmentTo != null)
		{
			depTo = "and a.department_to_id = "+departmentTo.getDepartmentId()+" ";
		}
		
		String sql1 = "select a.department_score_record_id, a.score, a.score_comment, a.score_date, a.department_from_id as deprtment_from_id, " +
				" a.which_year as which_year, a.which_month as which_month, a.modify_times as modify_times, "
				+ "b.score_item_id, b.score_item_name, b.score_description, b.score_value, b.score_reference, b.score_calculation_method,  "
				+ "b.department_id as department_to_id,b.score_creation_date, b.score_in_use, b.score_category_id  "
				+ "from department_score_record as a, department_score_item as b "
				+ "where a.which_year = ? and a.which_month = ? " 
				+ "and a.score_item_id = b.score_item_id "
				+depFrom+depTo
				+ categorySql 
				+"order by b.score_item_id asc; ";
		
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, year);
		preparedStatement.setInt(2, month);
		
		ResultSet rs = preparedStatement.executeQuery();
		DepartmentManageDao departmentManageDao = new DepartmentManageDao();
		DepartmentScoreItemManageDao departmentScoreItemManageDao = new DepartmentScoreItemManageDao();

		while (rs.next()) {
			if (records == null)
				records = new ArrayList<DepartmentScoreRecord>();

			Department from = departmentManageDao.getDepartmentById(rs
					.getInt("deprtment_from_id"));
			Department to = departmentManageDao.getDepartmentById(rs
					.getInt("department_to_id"));
			ScoreItemCategory category = departmentScoreItemManageDao
					.getCategoryById(rs.getInt("score_category_id"));
			DepartmentScoreItem departmentScoreItem = new DepartmentScoreItem(
					rs.getInt("score_item_id"),
					rs.getString("score_item_name"), rs
							.getString("score_description"), rs
							.getFloat("score_value"), rs
							.getString("score_reference"), rs
							.getString("score_calculation_method"), to,
					category, rs.getDate("score_creation_date"), rs
							.getInt("score_in_use"));

			DepartmentScoreRecord departmentScoreRecord = new DepartmentScoreRecord(
					rs.getInt("department_score_record_id"), from, to,
					departmentScoreItem, rs.getFloat("score"), rs
							.getString("score_comment"), rs
							.getDate("score_date"), rs.getInt("which_year"), rs.getInt("which_month"), rs.getInt("modify_times"));

			records.add(departmentScoreRecord);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return records;

	}

	//返回需要打分的人
	public List<Staff> getNoScoredStaff(Staff staffFrom, int year, int month) throws Exception {
		// TODO Auto-generated method stub
		PostManageDao postManageDao = new PostManageDao();
		List<Staff> staffs = postManageDao.getScoreStaffsByPostId(staffFrom);

		if (staffs == null)
			return null;

		List<Staff> scoredStaffs = getScoreStaff(staffFrom, year, month);

		if (scoredStaffs == null)
			return staffs;

		// for (Staff sf : staffs) {
		for (int i = staffs.size() - 1; i >= 0; i--) {
			Staff sf = staffs.get(i);
			boolean contains = false;
			for (Staff ssf : scoredStaffs) {
				if (sf.equals(ssf)) {
					contains = true;
					break;
				}
			}
			if (contains)
				staffs.remove(i);
		}
		return staffs;
	}


	//返回个人条目
	public List<StaffScoreItem> getStaffScoreItemByStaff(Staff staff, int year, int month)
			throws Exception {
		List<StaffScoreItem> items = new ArrayList<StaffScoreItem>();
		String sql = "select a.score_item_id, a.score_item_name, a.score_weight, a.score_description, a.score_standard1, a.score_standard2, " +
				"a.score_standard3, a.score_standard4, a.score_standard5, a.score_category_id, a.score_creation_date, a.score_in_use, c.score_category_name " +
				"from staff_score_item as a, staff_score_relation as b, score_category as c "
				+ "where a.score_item_id=b.score_item_id and a.score_category_id = c.score_category_id and "
				+ "b.department_id=? and b.post_id=?";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, staff.getDepartment().getDepartmentId());
		preparedStatement.setInt(2, staff.getPost().getPostId());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			if (items == null)
				items = new ArrayList<StaffScoreItem>();
			items.add(new StaffScoreItem(
					rs.getInt("score_item_id"), 
					rs.getString("score_item_name"), 
					rs.getFloat("score_weight"),
					rs.getString("score_description"), 
					rs.getString("score_standard1"), 
					rs.getString("score_standard2"), 
					rs.getString("score_standard3"), 
					rs.getString("score_standard4"), 
					rs.getString("score_standard5"),
					new ScoreItemCategory(rs.getInt("score_category_id"), rs.getString("score_category_name")),
					rs.getDate("score_creation_date"), 
					rs.getInt("score_in_use")));
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return items;
	}


	//返回打完分的人
	public List<Staff> getScoreStaff(Staff staffFrom, int year, int month) throws Exception {
		List<Staff> staffs = null;
		String sql = "select scored_to_staff_id from "
				+ "staff_score_record where scored_from_staff_id = ? "
				+ " and which_year = ? and which_month = ?   "
				+ "group by scored_to_staff_id; ";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setInt(1, staffFrom.getStaffId());
		preparedStatement.setInt(2, year);
		preparedStatement.setInt(3, month);

		ResultSet rs = preparedStatement.executeQuery();
		StaffManageDao manageDao = new StaffManageDao();
		while (rs.next()) {
			if (staffs == null)
				staffs = new ArrayList<Staff>();
			staffs.add(manageDao.getStaffById(rs.getInt("scored_to_staff_id")));
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return staffs;
	}

	//返回打分记录
	/*
	 * staffTo staffFrom 为空时不限定
	 * 
	 */
	public List<StaffScoreRecord> getStaffScoredRecord(Staff staffTo,
			Staff staffFrom, int year, int month) throws SQLException, Exception {
		List<StaffScoreRecord> records = null;
		String stfTo = "";
		String stfFrom = "";
		
		if(staffTo!=null)
		{
			stfTo = "and scored_to_staff_id = "+staffTo.getStaffId()+" ";
		}
		if(staffFrom!=null)
		{
			stfFrom = "and scored_from_staff_id = "+staffFrom.getStaffId()+" ";
		}
		
		String sql = "select staff_score_record_id, scored_from_staff_id, "
				+ "scored_to_staff_id, score_item_id, score, score_date, " +
						"which_year, which_month, modify_times "
				+ "from staff_score_record "
				+ "where which_year = ? and which_month = ? "
				+stfFrom+stfTo
				+"order by scored_from_staff_id asc, score_item_id asc;";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, year);
		preparedStatement.setInt(2, month);
		ResultSet rs = preparedStatement.executeQuery();

		StaffScoreItemManageDao staffScoreItemManageDao = new StaffScoreItemManageDao();
		StaffManageDao staffManageDao = new StaffManageDao();
		while (rs.next()) {
			if (records == null)
				records = new ArrayList<StaffScoreRecord>();
				staffTo = staffManageDao.getStaffById(rs.getInt("scored_to_staff_id"));
				staffFrom = staffManageDao.getStaffById(rs.getInt("scored_from_staff_id"));
			
			
			StaffScoreRecord staffScoreRecord = new StaffScoreRecord(rs
					.getInt("staff_score_record_id"), staffFrom, staffTo,
					staffScoreItemManageDao.getStaffScoreItemById(rs
							.getInt("score_item_id")), rs.getFloat("score"), rs
							.getDate("score_date"), rs.getInt("which_year"), 
							rs.getInt("which_month"), rs.getInt("modify_times"));
			records.add(staffScoreRecord);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return records;
	}

	//部门打分
	public void departmentScoreByItemId(List<String> itemIds,
			List<String> departmentScores,
			List<String> departmentScoreComments, int departmentFromId,
			int departmentToId, int ownerId) throws Exception {
		// TODO Auto-generated method stub
		if (itemIds.size() != departmentScores.size())
			return;
		for (int i = 0; i < itemIds.size(); i++) {
			String sql1 = " insert into department_score_record(department_from_id, department_to_id, "
					+ "score_item_id, score, score_comment, score_date, which_year, which_month, modify_times, owner) "
					+ "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
			Connection conn = null;
			PreparedStatement ps = null;
			conn = DataBaseUtil.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, departmentFromId);
			ps.setInt(2, departmentToId);
			ps.setInt(3, Integer.parseInt(itemIds.get(i)));
			ps.setFloat(4, Float.parseFloat(departmentScores.get(i)));
			ps.setString(5, departmentScoreComments.get(i));
			ps.setDate(6, new Date(System.currentTimeMillis()));
			ps.setInt(7, ConfigurationDataUtil.getIntegerVlaue("which_year"));
			ps.setInt(8, ConfigurationDataUtil.getIntegerVlaue("which_month"));
			ps.setInt(9, ConfigurationDataUtil.getIntegerVlaue("department_modify_times"));
			ps.setInt(10, ownerId);

			ps.executeUpdate();

			DataBaseUtil.close(ps, conn);
		}
	}

	//个人打分
	public void staffScoreByItemIdAndStaffId(List<String> itemIds,
			List<String> scores, int staffIdTo, int staffFromId, int ownerId)
			throws Exception {
		{

			if (itemIds.size() != scores.size())
				return;
			for (int i = 0; i < itemIds.size(); i++) {
				String sql1 = " insert into staff_score_record(scored_from_staff_id, scored_to_staff_id, "
						+ "score_item_id, score, score_date, which_year, which_month, modify_times, owner) "
						+ "values( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

				Connection conn = null;
				PreparedStatement ps = null;
				conn = DataBaseUtil.getConnection();
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, staffFromId);
				ps.setInt(2, staffIdTo);
				ps.setInt(3, Integer.parseInt(itemIds.get(i)));
				ps.setFloat(4, Float.parseFloat(scores.get(i)));
				ps.setDate(5, new Date(System.currentTimeMillis()));
				ps.setInt(6, ConfigurationDataUtil.getIntegerVlaue("which_year"));
				ps.setInt(7, ConfigurationDataUtil.getIntegerVlaue("which_month"));
				ps.setInt(8, ConfigurationDataUtil.getIntegerVlaue("staff_modify_times"));
				ps.setInt(9, ownerId);
				ps.executeUpdate();

				DataBaseUtil.close(ps, conn);
			}

		}
	}


	public boolean updateDepartmentScoreRecord(int recordId,
			float departmentScore, String content, Date date) throws Exception {
		boolean isSuccess = false;
		String sql = "update department_score_record set score=?,score_comment=?,score_date=?, modify_times = modify_times-1 "
				+ "where department_score_record_id=? ;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat(1, departmentScore);
			preparedStatement.setString(2, content);
			preparedStatement.setDate(3, date);
			preparedStatement.setInt(4, recordId);

			preparedStatement.executeUpdate();
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			DataBaseUtil.close(preparedStatement, connection);
		}
		return isSuccess;
	}



	//个人打分修改
	public boolean updateStaffScoreRecord(int recordId, float staffScore,
			Date date) throws Exception {
		boolean isSuccess = false;
		String sql = "update staff_score_record set score=?,score_date=?, modify_times = modify_times-1 "
				+ "where staff_score_record_id = ?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat(1, staffScore);
			preparedStatement.setDate(2, date);
			preparedStatement.setInt(3, recordId);

			preparedStatement.executeUpdate();
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			DataBaseUtil.close(preparedStatement, connection);
		}
		return isSuccess;
	}
}
