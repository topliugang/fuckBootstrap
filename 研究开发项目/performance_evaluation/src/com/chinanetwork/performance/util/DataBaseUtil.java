package com.chinanetwork.performance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataBaseUtil {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/pfe?useUnicode=true&characterEncoding=utf8";
	private static String username = "root";
	private static String password = "liugang";

	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("getConnection error");
		}
		return connection;
	}

	public static void close(ResultSet resultSet,
			PreparedStatement preparedStatement, Connection connection)
			throws Exception {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("close error");
		}
	}

	public static void close(PreparedStatement preparedStatement,
			Connection connection) throws Exception {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("close error");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DataBaseUtil.getConnection());
	}

	public static void executeSQL(String sql) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
	}

	public static String integerListToSQLString(List<Integer> list) {
		String sql = "";
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				sql.concat(String.valueOf(list.get(i)) + ";");
			}
		}
		return sql;
	}

}
