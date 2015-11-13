package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffClass;
import com.chinanetwork.performance.util.DataBaseUtil;

public class StaffClassManageDao {
	public StaffClass getStaffClassByClassId(String classId) throws Exception {
		StaffClass staffClass = null;
		String sql = "select class_id, class_name, class_comment from staff_class "
				+ "where class_id = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, classId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			staffClass = new StaffClass(rs.getString("class_id"), rs
					.getString("class_name"), rs.getString("class_comment"));
		}
		DataBaseUtil.close(ps, conn);
		return staffClass;
	}

	public List<StaffClass> getAllStaffClasses() throws Exception {
		List<StaffClass> classes = null;

		String sql = "select class_id, class_name, class_comment from staff_class; ";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (classes == null) {
				classes = new ArrayList<StaffClass>();
			}
			StaffClass staffClass = new StaffClass(rs.getString("class_id"), rs
					.getString("class_name"), rs.getString("class_comment"));
			classes.add(staffClass);
		}
		DataBaseUtil.close(ps, conn);

		return classes;

	}

	public List<Staff> getStaffByClassId(String classId) throws Exception {
		StaffManageDao staffManageDao = new StaffManageDao();
		List<Staff> staffs = null;

		String sql = "select staff_id from staff " +
				"where class_id like ?; ";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%"+classId+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (staffs == null) {
				staffs = new ArrayList<Staff>();
			}
			Staff staff = staffManageDao.getStaffById(rs.getInt("staff_id"));
			staffs.add(staff);
		}
		DataBaseUtil.close(ps, conn);

		return staffs;
	}

}
