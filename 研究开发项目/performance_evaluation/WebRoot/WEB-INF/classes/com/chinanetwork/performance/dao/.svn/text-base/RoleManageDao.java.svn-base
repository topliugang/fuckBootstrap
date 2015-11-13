package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.FuncpageCategory;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.util.DataBaseUtil;

public class RoleManageDao {

	public List<Role> getAllRole() throws Exception
	{
		List<Role> roles = null;
		
		String sql1=" select role_id, role_name, role_description from role ";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);

		ResultSet rs=preparedStatement.executeQuery();

		while(rs.next()){
			if(roles == null)
				roles = new ArrayList<Role>();
			Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("role_description"));
			roles.add(role);
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return roles;
	}
//-----------------------------------------------------------------------分页代码开始
	public List<Role> getAllRole(int pageStart,int pageSize)throws Exception{
		List<Role> roles = new ArrayList<Role>();
		String sql=" select role_id, role_name, role_description from role limit ?,?";
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
				Role role = new Role(resultSet.getInt("role_id"), resultSet.getString("role_name"), resultSet.getString("role_description"));
				roles.add(role);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return roles;
	}
	
	/**
	 * 获取总记录数
	 * @return
	 * @throws Exception
	 */
	public int getTotalRecored()throws Exception{
		int count=0;
		String sql="select count(*) crecored from role;";
		
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
//-----------------------------------------------------------------------分页代码结束

	public void addRole(Role role) throws Exception {
		String sql1=" insert into role (role_name, role_description) values(?, ?);";
		Connection conn = null;
		PreparedStatement ps = null;

		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, role.getRoleName());
		ps.setString(2, role.getRoleDescription());
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
	}

	public void deleteRole(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		String sql1=" delete from role where role_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, parseInt);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
		
	}

	public Role getRoleById(int id) throws Exception {
		Role role = null;
		String sql1=" select role_id, role_name, role_description from role where role_id=? ";
		Connection  connection=DataBaseUtil.getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs=preparedStatement.executeQuery();

		if(rs.next()){
			role = new Role(rs.getInt("role_id"), rs.getString("role_name"), rs.getString("role_description"));
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return role;
		
	}

	public void modifyRoleById(int id, Role roleToModify) throws Exception {
		// TODO Auto-generated method stub
		String sql1=" update role set role_id = ?, role_name = ?, role_description = ? where role_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn=DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.setString(2, roleToModify.getRoleName());
		ps.setString(3, roleToModify.getRoleDescription());
		ps.setInt(4, id);
		ps.executeUpdate();
		DataBaseUtil.close(ps, conn);
	}
	
	public void modifyFuncpage(Role role, List<Integer> funcpageIds) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		conn=DataBaseUtil.getConnection();
		//ps = conn.prepareStatement();
		
		stmt = conn.createStatement();
		conn.setAutoCommit(false);
		String sql1=" delete from role_funcpage_relation where role_id="+role.getRoleId()+";";
		stmt.addBatch(sql1);
		for( int i=0; i<funcpageIds.size(); i++ )
		{
			sql1 = "insert into role_funcpage_relation(role_id, funcpage_id) values("+role.getRoleId()+", "+funcpageIds.get(i) +");";
			stmt.addBatch(sql1);
		}
		//ps.setInt(1, id);
		//ps.setString(2, roleToModify.getRoleName());
		//ps.setString(3, roleToModify.getRoleDescription());
		//ps.setInt(4, id);
		//ps.executeUpdate();
		stmt.executeBatch();
		conn.commit();
  		conn.setAutoCommit(true);

  		if(stmt != null)
  			stmt.close();
		DataBaseUtil.close(ps, conn);

		
	}
	
	public List<Funcpage> getFuncpages(int roleId) throws Exception {
		List<Funcpage> funcpages = null;
		String sql = "select a.funcpage_id, b.funcpage_name, b.funcpage_action, c.funcpage_category_id, c.funcpage_category_name "
				+ "from role_funcpage_relation as a, funcpage as b, funcpage_category as c "
				+ "where role_id = ? "
				+ "and a.funcpage_id = b.funcpage_id "
				+ "and b.funcpage_category_id = c.funcpage_category_id ";

		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, roleId);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (funcpages == null)
				funcpages = new ArrayList<Funcpage>();
			Funcpage funcpage = new Funcpage(rs.getInt("funcpage_id"), rs
					.getString("funcpage_name"), rs
					.getString("funcpage_action"), new FuncpageCategory(rs
					.getInt("funcpage_category_id"), rs
					.getString("funcpage_category_name")));
			funcpages.add(funcpage);
		}
		return funcpages;
	}

	public List<Funcpage> getAllFuncpages() throws Exception {
		List<Funcpage> funcpages = null;
		String sql = "select b.funcpage_id, b.funcpage_name, b.funcpage_action, " +
				"c.funcpage_category_id, c.funcpage_category_name "
				+ "from funcpage as b, funcpage_category as c "
				+ "where b.funcpage_category_id = c.funcpage_category_id ";

		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (funcpages == null)
				funcpages = new ArrayList<Funcpage>();
			Funcpage funcpage = new Funcpage(rs.getInt("funcpage_id"), rs
					.getString("funcpage_name"), rs
					.getString("funcpage_action"), new FuncpageCategory(rs
					.getInt("funcpage_category_id"), rs
					.getString("funcpage_category_name")));
			funcpages.add(funcpage);
		}
		return funcpages;
	}



	public List<Integer> getFuncpageIdsByRoleId(int roleId) throws Exception{
		List<Integer> retIds = null;
		String sql = "select * from role_funcpage_relation where role_id=?; ";

		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, roleId);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (retIds == null)
				retIds = new ArrayList<Integer>();
			retIds.add(rs.getInt("funcpage_id"));
		}
		return retIds;
	}
	
}
