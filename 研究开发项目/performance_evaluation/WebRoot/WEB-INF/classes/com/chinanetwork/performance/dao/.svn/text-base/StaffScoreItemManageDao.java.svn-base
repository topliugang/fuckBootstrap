package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.ScoreItemCategory;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.util.DataBaseUtil;

public class StaffScoreItemManageDao {

	public List<StaffScoreItem> getAllStaffScoreItem() throws Exception {
		List<StaffScoreItem> staffScoreItems = null;
		
		String sql1 = "select a.score_item_id, a.score_item_name, a.score_weight, "
				+ "a.score_description, a.score_standard1, a.score_standard2, a.score_standard3, " 
				+ "a.score_standard4, a.score_standard5, a.score_creation_date, a.score_category_id, b.score_category_name, "
				+ "a.score_in_use from staff_score_item as a, score_category as b " +
						"where a.score_category_id = b.score_category_id ;";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);

		ResultSet rs = preparedStatement.executeQuery();

		DepartmentManageDao departmentManageDao = new DepartmentManageDao();
		PostManageDao postManageDao = new PostManageDao();

		while (rs.next()) {
			if (staffScoreItems == null)
				staffScoreItems = new ArrayList<StaffScoreItem>();
			StaffScoreItem staffScoreItem = new StaffScoreItem(
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
					rs.getInt("score_in_use"));

			staffScoreItems.add(staffScoreItem);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return staffScoreItems;
	}

//--------------------------------------------------------------------分页代码开始
	
	public List<StaffScoreItem> getAllStaffScoreItem(int pageStart,int pageSize) throws Exception{
		List<StaffScoreItem> staffScoreItems=new ArrayList<StaffScoreItem>();
		String sql="select a.score_item_id, a.score_item_name, a.score_weight, "
			+ "a.score_description, a.score_standard1, a.score_standard2, a.score_standard3, " 
			+ "a.score_standard4, a.score_standard5, a.score_creation_date, a.score_category_id, b.score_category_name,  "
			+ "a.score_in_use from staff_score_item as a, score_category as b " +
					"where a.score_category_id = b.score_category_id limit ?,?;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, pageStart);
			preparedStatement.setInt(2, pageSize);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				StaffScoreItem staffScoreItem = new StaffScoreItem(
						resultSet.getInt("score_item_id"), 
						resultSet.getString("score_item_name"),
						resultSet.getFloat("score_weight"), 
						resultSet.getString("score_description"), 
						resultSet.getString("score_standard1"),
						resultSet.getString("score_standard2"),
						resultSet.getString("score_standard3"),
						resultSet.getString("score_standard4"),
						resultSet.getString("score_standard5"),
						new ScoreItemCategory(resultSet.getInt("score_category_id"), resultSet.getString("score_category_name")),
						resultSet.getDate("score_creation_date"), 
						resultSet.getInt("score_in_use"));

				staffScoreItems.add(staffScoreItem);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffScoreItems;
	}
	
	public int getTotalRecored()throws Exception{
		int count=0;
		String sql="select count(*) count from staff_score_item;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
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
	
//--------------------------------------------------------------------分页代码结束

	public void deleteStaffScoreItem(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		String sql1 = " delete from staff_score_item where score_item_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, parseInt);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);

	}

	public StaffScoreItem getStaffScoreItemById(int id) throws Exception {
		StaffScoreItem staffScoreItem = null;
		String sql1 = "select a.score_item_id, a.score_item_name, a.score_weight, a.score_description, "
				+ "a.score_standard1, a.score_standard2, a.score_standard3, a.score_standard4, a.score_standard5, " +
						"a.score_category_id, b.score_category_name, " +
						"a.score_creation_date, a.score_in_use from staff_score_item a, score_category b "
				+ "where a.score_item_id = ? and a.score_category_id = b.score_category_id;";

		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			staffScoreItem = new StaffScoreItem(rs.getInt("score_item_id"), rs
					.getString("score_item_name"), rs.getFloat("score_weight"), 
					rs.getString("score_description"), 
					rs.getString("score_standard1"),
					rs.getString("score_standard2"),
					rs.getString("score_standard3"),
					rs.getString("score_standard4"),
					rs.getString("score_standard5"),
					new ScoreItemCategory(rs.getInt("score_category_id"), rs.getString("score_category_name")),
					rs.getDate("score_creation_date"), rs.getInt("score_in_use"));
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return staffScoreItem;

	}



	public List<StaffScoreItem> getStaffScoreItemsByDepartmentId(
			int departmentIdChosen) throws Exception {
		List<StaffScoreItem> items = null;
		String sql1 = "select a.score_item_id, a.score_item_name, a.score_weight, a.score_description, a.score_standard1, a.score_standard2, " +
				"a.score_standard3, a.score_standard4, a.score_standard5, a.score_creation_date, a.score_in_use, a.score_category_id, b.score_category_name " +
				" from staff_score_item a , score_category b where a.score_category_id = b.score_category_id and a.score_item_id in" +
				"(select score_item_id from staff_score_relation where department_id = ?);";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, departmentIdChosen);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next())
		{
			if(items == null)
				items = new ArrayList<StaffScoreItem>();
			StaffScoreItem item = new StaffScoreItem(rs.getInt("score_item_id"), 
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
					rs.getInt("score_in_use"));
			items.add(item);
		}
		return items;
	}

	public void addStaffScoreItem(StaffScoreItem staffScoreItem) throws Exception {
		String sql1 = "insert into staff_score_item(score_item_id, score_item_name, score_weight, " +
				"score_description, score_standard1, score_standard2, score_standard3, score_standard4, score_standard5, score_category_id, score_creation_date, " +
				"score_in_use) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Connection conn = DataBaseUtil.getConnection();
		PreparedStatement ps = null;
		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, staffScoreItem.getStaffScoreItemId());
		ps.setString(2, staffScoreItem.getStaffScoreName());
		ps.setFloat(3, staffScoreItem.getScoreWeight());
		ps.setString(4, staffScoreItem.getScoreDescription());
		ps.setString(5, staffScoreItem.getScoreStandard1());
		ps.setString(6, staffScoreItem.getScoreStandard2());
		ps.setString(7, staffScoreItem.getScoreStandard3());
		ps.setString(8, staffScoreItem.getScoreStandard4());
		ps.setString(9, staffScoreItem.getScoreStandard5());
		ps.setInt(10, staffScoreItem.getScoreItemCategory().getDepartmentScoreCategoryId());
		ps.setDate(11, new java.sql.Date(System.currentTimeMillis()));
		ps.setInt(12, 1);
		ps.executeUpdate();
	}



	public void modifyStaffScoreItemById(StaffScoreItem staffScoreItemToModify) throws Exception {
		String sql1 = "update staff_score_item set score_item_id = ?, score_item_name = ?, score_weight = ?, " +
		"score_description = ?, score_standard1 = ?, score_standard2 = ?, score_standard3 = ?, score_standard4 = ?, " +
		"score_standard5 = ?, score_category = ?, score_creation_date = ?, score_in_use = ? where score_item_id = ?;";
		Connection conn = DataBaseUtil.getConnection();
		PreparedStatement ps = null;
		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, staffScoreItemToModify.getStaffScoreItemId());
		ps.setString(2, staffScoreItemToModify.getStaffScoreName());
		ps.setFloat(3, staffScoreItemToModify.getScoreWeight());
		ps.setString(4, staffScoreItemToModify.getScoreDescription());
		ps.setString(5, staffScoreItemToModify.getScoreStandard1());
		ps.setString(6, staffScoreItemToModify.getScoreStandard2());
		ps.setString(7, staffScoreItemToModify.getScoreStandard3());
		ps.setString(8, staffScoreItemToModify.getScoreStandard4());
		ps.setString(9, staffScoreItemToModify.getScoreStandard5());
		ps.setInt(10, staffScoreItemToModify.getScoreItemCategory().getDepartmentScoreCategoryId());
		ps.setDate(11, new java.sql.Date(System.currentTimeMillis()));
		ps.setInt(12, 1);
		ps.setInt(13, staffScoreItemToModify.getStaffScoreItemId());
		ps.executeUpdate();
	}
}
