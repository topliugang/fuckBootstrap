package com.chinanetwork.performance.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class WelcomeDateUtil {

	public static Map<String,String> getTimeInfo()throws Exception{
		Map<String,String> timeInfoList=new HashMap<String,String>();
		String sql="select configuration_data_key,configuration_data_value " +
				"from configuration_data " +
				"where configuration_data_key in('which_year','which_month','start_time','end_time')";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				timeInfoList.put(resultSet.getString("configuration_data_key"), resultSet.getString("configuration_data_value"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return timeInfoList;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String,String> timeInfoList=getTimeInfo();
		System.out.println(timeInfoList.get("which_year"));
		System.out.println(timeInfoList.get("which_month"));
		System.out.println(timeInfoList.get("start_time"));
		System.out.println(timeInfoList.get("end_time"));
	}
}
