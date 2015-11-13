package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentItemResult;
import com.chinanetwork.performance.bean.DepartmentScore;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScore;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.util.DataBaseUtil;

public class CalculateScoreDao {
	// ///fuck department scoreeeeee
	public Map<Integer, Float> fuckDepartmentScore(int year, int month)
			throws Exception {
		Map<Integer, Float> map = new HashMap<Integer, Float>();
		String sql = "select department_to_id, sum(caonima) as score from "
				+ "(select department_to_id, score_item_id, avg(score) as caonima "
				+ "from department_score_record where which_year = ? and which_month = ? group by score_item_id) as shabi "
				+ "group by department_to_id;";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, year);
		preparedStatement.setInt(2, month);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int departmentId = resultSet.getInt("department_to_id");
			float score = resultSet.getFloat("score");
			map.put(departmentId, score);
		}
		return map;
	}

	public void fuckComment(int year, int month) throws Exception {
		class FuckComment {
			int departmentId;
			int scoreitemId;
			float score;
			float scoreValue;
			String scoreitemName;
			String scoreDescription;
		}
		;

		List<FuckComment> fuckComments = new ArrayList<FuckComment>();
		String sql = "select r.department_to_id,r.score_item_id, avg(r.score) as score, "
				+ "i.score_value, i.score_item_name, i.score_description "
				+ "from department_score_record r, department_score_item i "
				+ "where r.score < i.score_value "
				+ "and r.score_item_id = i.score_item_id "
				+ "and r.which_year = ? "
				+ "and r.which_month = ? "
				+ "group by score_item_id " + "order by r.department_to_id;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement.setInt(2, month);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FuckComment fuckComment = new FuckComment();
				fuckComment.departmentId = resultSet.getInt("department_to_id");
				fuckComment.scoreitemId = resultSet.getInt("score_item_id");
				fuckComment.score = resultSet.getFloat("score");
				fuckComment.scoreValue = resultSet.getFloat("score_value");
				fuckComment.scoreitemName = resultSet
						.getString("score_item_name");
				fuckComment.scoreDescription = resultSet
						.getString("score_description");
				fuckComments.add(fuckComment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, year);

		return;
	}

	// ----------------------------------------------------------5月15日改
	// ----------------------------------------------------------5月15日改

	/**
	 * 根据参数staff，找到所有在year month staff_to_id = staff.id被评分的记录
	 * 
	 * @param staff
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<StaffScoreRecord> selectAllStaffScoreRecordByStaff(Staff staff,
			int year, int month) throws Exception {
		List<StaffScoreRecord> staffScoreRecordList = new ArrayList<StaffScoreRecord>();
		String sql = "select ssr.staff_score_record_id,ssr.scored_from_staff_id,s.staff_name,p.post_id,p.post_name,ssr.scored_to_staff_id,ssr.score_item_id,ssi.score_weight,"
				+ "ssr.score,ssr.score_date,ssr.which_year,ssr.which_month,ssr.modify_times "
				+ "from staff_score_record ssr,staff_score_item ssi,staff s,post p where ssr.scored_from_staff_id=s.staff_id and s.post_id=p.post_id and ssr.score_item_id=ssi.score_item_id "
				+ "and ssr.scored_to_staff_id=? and which_year=? and which_month=?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getStaffId());
			preparedStatement.setInt(2, year);
			preparedStatement.setInt(3, month);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Staff fStaff = new Staff();
				fStaff.setStaffId(resultSet.getInt("ssr.scored_from_staff_id"));
				fStaff.setStaffName(resultSet.getString("s.staff_name"));
				fStaff.setPost(new Post(resultSet.getInt("p.post_id"),
						resultSet.getString("p.post_name")));

				Staff tStaff = new Staff();
				tStaff.setStaffId(resultSet.getInt("ssr.scored_to_staff_id"));

				StaffScoreItem staffScoreItem = new StaffScoreItem();
				staffScoreItem.setStaffScoreItemId(resultSet
						.getInt("ssr.score_item_id"));
				staffScoreItem.setScoreWeight(resultSet
						.getFloat("ssi.score_weight"));

				StaffScoreRecord staffScoreRecord = new StaffScoreRecord();

				staffScoreRecord.setId(resultSet
						.getInt("ssr.staff_score_record_id"));
				staffScoreRecord.setScore(resultSet.getFloat("ssr.score"));
				staffScoreRecord.setScoredFromStaff(fStaff);
				staffScoreRecord.setScoredToStaff(tStaff);
				staffScoreRecord.setStaffScoreItem(staffScoreItem);

				staffScoreRecord.setDate(resultSet.getDate("ssr.score_date"));
				staffScoreRecord.setWhichYear(resultSet
						.getInt("ssr.which_year"));
				staffScoreRecord.setWhichMonth(resultSet
						.getInt("ssr.which_month"));
				staffScoreRecord.setModifyTimes(resultSet
						.getInt("ssr.modify_times"));
				staffScoreRecordList.add(staffScoreRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffScoreRecordList;
	}

	/**
	 * 根据参数department_id（部门ID），找到对应的部门得分
	 */
	public DepartmentScore getScoreOfDepartmentBelongStaff(int departmentId,
			int year, int month) throws Exception {
		DepartmentScore departmentScore = new DepartmentScore();
		String sql = "select ds.department_total_score_id,ds.department_id,d.department_name,d.higher_department_id,"
				+ "d.nature,ds.total_score,ds.which_year,ds.which_month,ds.annotation "
				+ "from department_score ds,department d where ds.department_id=d.department_id and "
				+ "ds.department_id=? and ds.which_year=? and ds.which_month=?;";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, departmentId);
			preparedStatement.setInt(2, year);
			preparedStatement.setInt(3, month);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Department department = new Department();
				department
						.setDepartmentId(resultSet.getInt("ds.department_id"));
				department.setDepartmentName(resultSet
						.getString("d.department_name"));
				department.setHigherDepartmentId(resultSet
						.getInt("d.higher_department_id"));
				department.setNature(resultSet.getInt("d.nature"));

				departmentScore.setYear(resultSet.getInt("ds.which_year"));
				departmentScore.setMonth(resultSet.getInt("ds.which_month"));
				departmentScore.setScore(resultSet.getFloat("ds.total_score"));
				departmentScore.setInstruction(resultSet
						.getString("ds.annotation"));
				departmentScore.setDepartment(department);
			}
			return departmentScore;
		} catch (Exception e) {
			return null;
		} finally {
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 将计算出来的员工得分插入到数据库中
	 * 
	 * @param staffScore
	 * @throws Exception
	 */
	public void insertDataIntoStaffScore(List<StaffScore> staffScoreList)
			throws Exception {
		String sql = "insert into staff_score(staff_id,staff_score,which_year,which_month,annotation) values(?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < staffScoreList.size(); i++) {
				preparedStatement.setInt(1, staffScoreList.get(i).getStaff()
						.getStaffId());
				preparedStatement.setFloat(2, staffScoreList.get(i)
						.getTotalScore());
				preparedStatement.setInt(3, staffScoreList.get(i).getYear());
				preparedStatement.setInt(4, staffScoreList.get(i).getMonth());
				preparedStatement.setString(5, staffScoreList.get(i)
						.getInstruction());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	/**
	 * public float selectTaskSchedule(int year,int month)throws Exception{
	 * float taskSchedule=0; String sql=
	 * "select task_progress from task_progress where which_year=? and which_month=?;"
	 * ; Connection connection=null; PreparedStatement preparedStatement=null;
	 * ResultSet resultSet=null; try{ connection=DataBaseUtil.getConnection();
	 * preparedStatement=connection.prepareStatement(sql);
	 * preparedStatement.setInt(1,year); preparedStatement.setInt(2,month);
	 * resultSet=preparedStatement.executeQuery(); if(resultSet.next()){
	 * taskSchedule=resultSet.getFloat("task_progress"); }
	 * 
	 * }catch(Exception e){ e.printStackTrace(); }finally{
	 * DataBaseUtil.close(preparedStatement, connection); } return taskSchedule;
	 * }
	 */

}
