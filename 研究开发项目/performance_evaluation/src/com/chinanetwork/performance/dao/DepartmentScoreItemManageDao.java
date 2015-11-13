package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.ScoreItemCategory;
import com.chinanetwork.performance.util.DataBaseUtil;

public class DepartmentScoreItemManageDao {

//	public List<DepartmentScoreItem> getAllDepartmentScoreItem() throws Exception
//	{
//		List<DepartmentScoreItem> departmentScoreItems = null;
//		String sql1="select a.score_item_id, a.score_item_name, a.score_description, a.score_value, " +
//				"a.score_reference, a.score_calculation_method, a.department_id, b.score_category_name, " +
//				"a.score_category_id, a.score_creation_date, a.score_in_use " +
//				"from department_score_item as a, score_category as b " +
//				"where a.score_category_id = b.score_category_id;";
//		Connection  connection=DataBaseUtil.getConnection();
//		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
//		ResultSet rs=preparedStatement.executeQuery();
//		DepartmentManageDao departmentManageDao = new DepartmentManageDao();
//
//		while(rs.next()){
//			if(departmentScoreItems == null)
//				departmentScoreItems = new ArrayList<DepartmentScoreItem>();
//			
//			Department department = departmentManageDao.getDepartmentById(rs.getInt("a.department_id"));
//			ScoreItemCategory category = new ScoreItemCategory(rs.getInt("score_category_id"),
//					rs.getString("score_category_name"));
//			DepartmentScoreItem departmentScoreItem = new DepartmentScoreItem(rs.getInt("score_item_id"),
//					rs.getString("score_item_name"), rs.getString("score_description"),
//					rs.getFloat("score_value"), rs.getString("score_reference"),
//					rs.getString("score_calculation_method"), department, category,
//					rs.getDate("score_creation_date"), rs.getInt("score_in_use"));
//			departmentScoreItems.add(departmentScoreItem);
//		}
//		DataBaseUtil.close(rs, preparedStatement, connection);
//		return departmentScoreItems;
//	}
//	
	
	public List<DepartmentScoreItem> getDepartmentScoreItemsByDepartmentId(
			int id) throws Exception {
		List<DepartmentScoreItem> departmentScoreItems = null;
		String sql1="select a.score_item_id, a.score_item_name, a.score_description, a.score_value, " +
				"a.score_reference, a.score_calculation_method, a.department_id, b.score_category_name, " +
				"a.score_category_id, a.score_creation_date, a.score_in_use " +
				"from department_score_item as a, score_category as b " +
				"where a.score_category_id = b.score_category_id " +
				"and department_id = ?;";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs=preparedStatement.executeQuery();
		DepartmentManageDao departmentManageDao = new DepartmentManageDao();

		while(rs.next()){
			if(departmentScoreItems == null)
				departmentScoreItems = new ArrayList<DepartmentScoreItem>();
			
			Department department = departmentManageDao.getDepartmentById(rs.getInt("a.department_id"));
			ScoreItemCategory category = new ScoreItemCategory(rs.getInt("score_category_id"),
					rs.getString("score_category_name"));
			DepartmentScoreItem departmentScoreItem = new DepartmentScoreItem(rs.getInt("score_item_id"),
					rs.getString("score_item_name"), rs.getString("score_description"),
					rs.getFloat("score_value"), rs.getString("score_reference"),
					rs.getString("score_calculation_method"), department, category,
					rs.getDate("score_creation_date"), rs.getInt("score_in_use"));
			departmentScoreItems.add(departmentScoreItem);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return departmentScoreItems;
	}
	
//--------------------------------------------------------------分页代码开始
	public List<DepartmentScoreItem> getDepartmentScoreItemsByDepartmentId(int id,int pageStart,int pageSize) throws Exception{
		List<DepartmentScoreItem> departmentScoreItems = new ArrayList<DepartmentScoreItem>();
		String sql="select a.score_item_id, a.score_item_name, a.score_description, a.score_value, " +
				"a.score_reference, a.score_calculation_method, a.department_id, b.score_category_name, " +
				"a.score_category_id, a.score_creation_date, a.score_in_use " +
				"from department_score_item as a, score_category as b " +
				"where a.score_category_id = b.score_category_id " +
				"and department_id = ? limit ?,?;";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, pageStart);
			preparedStatement.setInt(3, pageSize);
			resultSet=preparedStatement.executeQuery();
			
			DepartmentManageDao departmentManageDao = new DepartmentManageDao();

			while(resultSet.next()){
				if(departmentScoreItems == null)
					departmentScoreItems = new ArrayList<DepartmentScoreItem>();
				
				Department department = departmentManageDao.getDepartmentById(resultSet.getInt("a.department_id"));
				ScoreItemCategory category = new ScoreItemCategory(resultSet.getInt("score_category_id"),
						resultSet.getString("score_category_name"));
				DepartmentScoreItem departmentScoreItem = new DepartmentScoreItem(resultSet.getInt("score_item_id"),
						resultSet.getString("score_item_name"), resultSet.getString("score_description"),
						resultSet.getFloat("score_value"), resultSet.getString("score_reference"),
						resultSet.getString("score_calculation_method"), department, category,
						resultSet.getDate("score_creation_date"), resultSet.getInt("score_in_use"));
				departmentScoreItems.add(departmentScoreItem);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return departmentScoreItems;
	} 
	
	public int getTotalRecored(int departmentId)throws Exception{
		int count=0;
		String sql="select count(*) count " +
		"from department_score_item as a, score_category as b " +
		"where a.score_category_id = b.score_category_id " +
		"and department_id = ?;";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, departmentId);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				count=resultSet.getInt("count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return count;
	}
//--------------------------------------------------------------分页代码结束
	
	public void addDepartmentScoreItem(DepartmentScoreItem departmentScoreItem, List<Integer> departmentsFromId) throws Exception {
		Connection conn = DataBaseUtil.getConnection();
		PreparedStatement ps = null;
		int id = 0;
		
		String sql = "select max(score_item_id) as id from department_score_item;";
		ps = conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			id = rs.getInt("id")+1;
		}
		
		String sql1=" insert into department_score_item(score_item_id, score_item_name, score_description, score_value, "+
					"score_reference, score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use) "+
					"values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ;";
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.setString(2, departmentScoreItem.getDepartmentScoreName());
		ps.setString(3, departmentScoreItem.getScoreDescription());
		ps.setFloat(4, departmentScoreItem.getScoreValue());
		ps.setString(5, departmentScoreItem.getScoreReference());
		ps.setString(6, departmentScoreItem.getScoreCalculationMethod());
		ps.setInt(7, departmentScoreItem.getDepartment().getDepartmentId());
		ps.setInt(8, departmentScoreItem.getScoreItemCategory().getDepartmentScoreCategoryId());
		ps.setDate(9, new Date(System.currentTimeMillis()));
		ps.setInt(10, 1);
		ps.executeUpdate();
		
		if( departmentsFromId != null )
		{
			for(int i=0; i<departmentsFromId.size(); i++)
			{
				String sql2 = "insert into department_score_relation" +
						"(score_item_id, department_id) " +
						"values(?, ?);";
				conn=DataBaseUtil.getConnection();
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, id);
				ps.setInt(2, departmentsFromId.get(i));
				ps.executeUpdate();
			}
		}

		DataBaseUtil.close(ps, conn);
	}

	public void deleteDepartmentScoreItem(int id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		conn=DataBaseUtil.getConnection();
		
		String sql1=" delete from department_score_item where score_item_id = ? ;";
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		String sql2 = "delete from department_score_relation where score_item_id = ?;";
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
	}

	public DepartmentScoreItem getDepartmentScoreItemById(int id) throws Exception {
		DepartmentScoreItem departmentScoreItem = null;
		String sql1=" select score_item_id, score_item_name, score_description, score_value, score_reference, " +
				"score_calculation_method, department_id, score_category_id, score_creation_date, score_in_use " +
				"from department_score_item where score_item_id = ?;";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs=preparedStatement.executeQuery();

		if(rs.next()){
			Department dep = new Department();
			dep.setDepartmentId(rs.getInt("department_id"));
			ScoreItemCategory cat = new ScoreItemCategory();
			cat.setDepartmentScoreCategoryId(rs.getInt("score_category_id"));
			departmentScoreItem = new DepartmentScoreItem(rs.getInt("score_item_id"), 
					rs.getString("score_item_name"), 
					rs.getString("score_description"), 
					rs.getFloat("score_value"), 
					rs.getString("score_reference"), 
					rs.getString("score_calculation_method"), 
					dep, 
					cat, 
					rs.getDate("score_creation_date"), 
					rs.getInt("score_in_use"));
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return departmentScoreItem;
	}
	
	
	public List<Integer> getFromDepartmentsIdById(int id) throws Exception
	{
		List<Integer> departmentIds = null;
		String sql1 = "select department_id from department_score_relation where score_item_id = ?;";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs=preparedStatement.executeQuery();

		while(rs.next()){
			if(departmentIds == null)
				departmentIds = new ArrayList<Integer>();
			departmentIds.add(rs.getInt("department_id"));
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return departmentIds;
		
	}

	public ScoreItemCategory getCategoryById( int id ) throws Exception
	{
		ScoreItemCategory category = null;
		String sql1=" select score_category_id, score_category_name from score_category where score_category_id = ? ";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.next()){
			category = new ScoreItemCategory(rs.getInt("score_category_id"), rs.getString("score_category_name"));
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return category;
	}
	
	public List<ScoreItemCategory> getAllCategories( ) throws Exception
	{
		List<ScoreItemCategory> categories = null;
		String sql1=" select score_category_id, score_category_name from score_category ";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()){
			if(categories == null)
				categories = new ArrayList<ScoreItemCategory>();
			ScoreItemCategory category = new ScoreItemCategory(rs.getInt("score_category_id"), rs.getString("score_category_name"));
			categories.add(category);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return categories;
	}


	public void modifyDepartmentScoreItem(
			DepartmentScoreItem departmentScoreItemWillModify,
			List<Integer> departmentsFromId) throws Exception {
		Connection conn = DataBaseUtil.getConnection();
		PreparedStatement ps = null;
		
		
		String sql1=" update department_score_item set " +
				"score_item_id = ?, score_item_name = ?, score_description = ?, score_value = ?, "+
					"score_reference = ?, score_calculation_method = ?, department_id = ?, score_category_id = ?, " +
					"score_creation_date = ?, score_in_use = ? "+
					"where score_item_id = ?";
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, departmentScoreItemWillModify.getDepartmentScoredItemId());
		ps.setString(2, departmentScoreItemWillModify.getDepartmentScoreName());
		ps.setString(3, departmentScoreItemWillModify.getScoreDescription());
		ps.setFloat(4, departmentScoreItemWillModify.getScoreValue());
		ps.setString(5, departmentScoreItemWillModify.getScoreReference());
		ps.setString(6, departmentScoreItemWillModify.getScoreCalculationMethod());
		ps.setInt(7, departmentScoreItemWillModify.getDepartment().getDepartmentId());
		ps.setInt(8, departmentScoreItemWillModify.getScoreItemCategory().getDepartmentScoreCategoryId());
		ps.setDate(9, new Date(System.currentTimeMillis()));
		ps.setInt(10, 1);
		ps.setInt(11, departmentScoreItemWillModify.getDepartmentScoredItemId());
		ps.executeUpdate();
		
		if( departmentsFromId != null )
		{
			//全删掉
			String sql3 = "delete from department_score_relation " +
						"where score_item_id = ? ;"; 
			conn=DataBaseUtil.getConnection();
			ps = conn.prepareStatement(sql3);
			ps.setInt(1, departmentScoreItemWillModify.getDepartmentScoredItemId());
			ps.executeUpdate();
			//再添加
			for(int i=0; i<departmentsFromId.size(); i++)
			{
				String sql2 = "insert into department_score_relation" +
						"(score_item_id, department_id) " +
						"values(?, ?);";
				conn=DataBaseUtil.getConnection();
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, departmentScoreItemWillModify.getDepartmentScoredItemId());
				ps.setInt(2, departmentsFromId.get(i));
				ps.executeUpdate();
			}
		}
		DataBaseUtil.close(ps, conn);
	}


	
}
