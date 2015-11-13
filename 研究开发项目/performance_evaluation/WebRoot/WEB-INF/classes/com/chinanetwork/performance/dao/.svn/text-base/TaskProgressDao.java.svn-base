package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.TaskProgress;
import com.chinanetwork.performance.util.DataBaseUtil;

public class TaskProgressDao {

	/**
	 * 确定“任务进度”是否已经被录入
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public boolean check(int year, int month) throws Exception {
		// TODO Auto-generated method stubList<Role> roles = null;
		boolean res;
		String sql1 = " select task_progress from task_progress "
				+ "where which_year=? and which_month=? ;";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				res = true;
			} else
				res = false;
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		} finally {
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return res;
	}

	public void input(TaskProgress taskProgress) throws Exception {
		if (!check(taskProgress.getWhichYear(), taskProgress.getWhichMonth())) {
			String sql1 = " insert into task_progress (task_progress, creation_date, "
					+ "which_year, which_month) " + "values(?, ?, ?, ?);";
			Connection conn = null;
			PreparedStatement ps = null;

			conn = DataBaseUtil.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setFloat(1, taskProgress.getTaskProgress());
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.setInt(3, taskProgress.getWhichYear());
			ps.setInt(4, taskProgress.getWhichMonth());
			ps.executeUpdate();
			DataBaseUtil.close(ps, conn);
		}
	}

	public TaskProgress getTaskProgress(int year, int month) throws Exception {
		TaskProgress taskProgress = null;
		String sql1 = " select task_progress_id, task_progress, creation_date, "
			+ "which_year, which_month from task_progress "
				+ "where which_year=? and which_month=? ;";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, year);
		preparedStatement.setInt(2, month);

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			taskProgress = new TaskProgress(rs
					.getInt("task_progress_id"), rs.getFloat("task_progress"),
					rs.getDate("creation_date"), rs.getInt("which_year"), rs
							.getInt("which_month"));
		} 
		DataBaseUtil.close(rs, preparedStatement, connection);
		return taskProgress;

	}

	public List<TaskProgress> getAllTaskProgress() throws Exception {
		List<TaskProgress> ret = null;

		String sql1 = " select task_progress_id, task_progress, creation_date, "
				+ "which_year, which_month from task_progress ;";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (ret == null)
				ret = new ArrayList<TaskProgress>();
			TaskProgress taskProgress = new TaskProgress(rs
					.getInt("task_progress_id"), rs.getFloat("task_progress"),
					rs.getDate("creation_date"), rs.getInt("which_year"), rs
							.getInt("which_month"));
			ret.add(taskProgress);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return ret;
	}

}
