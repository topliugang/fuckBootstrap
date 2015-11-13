package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.FuncpageCategory;
import com.chinanetwork.performance.util.DataBaseUtil;

public class ToMenuDao {

	public List<Funcpage> getFuncpageList(int roleId)throws Exception{
		List<Funcpage> funcpages = null;
		String sql = "select a.funcpage_id, b.funcpage_name, b.funcpage_action, c.funcpage_category_id, c.funcpage_category_name "
				+ "from role_funcpage_relation as a, funcpage as b, funcpage_category as c "
				+ "where role_id = ? "
				+ "and a.funcpage_id = b.funcpage_id "
				+ "and b.funcpage_category_id = c.funcpage_category_id ";

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection = DataBaseUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roleId);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				if (funcpages == null)
					funcpages = new ArrayList<Funcpage>();
				Funcpage funcpage = new Funcpage(resultSet.getInt("funcpage_id"), resultSet.getString("funcpage_name"), 
						resultSet.getString("funcpage_action"), 
						new FuncpageCategory(resultSet.getInt("funcpage_category_id"), resultSet.getString("funcpage_category_name")));
				funcpages.add(funcpage);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return funcpages;
	}
}
