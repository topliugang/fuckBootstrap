package com.chinanetwork.performance.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * 获取配置数据的工具类
 * @author Administrator
 *
 */
public class ConfigurationDataUtil {
	/**
	 * 根据数据名称Key，获取数据Value，其中value的数据类型为 “字符串”
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getStringValue(String key)throws Exception{
		String value="";
		String sql="select cd.configuration_data_value from configuration_data cd where cd.configuration_data_key=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, key);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				value=resultSet.getString("cd.configuration_data_value");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return value;
	}
	/**
	 * 根据数据名称Key，获取数据Value，其中value的数据类型为 “浮点”
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static float getFloatVlaue(String key)throws Exception{
		String value="";
		String sql="select cd.configuration_data_value from configuration_data cd where cd.configuration_data_key=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, key);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				value=resultSet.getString("cd.configuration_data_value");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return Float.parseFloat(value);
	}
	/**
	 * 根据数据名称Key，获取数据Value，其中value的数据类型为 “整型”
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static int getIntegerVlaue(String key)throws Exception{
		String value="";
		String sql="select cd.configuration_data_value from configuration_data cd where cd.configuration_data_key=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, key);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				value=resultSet.getString("cd.configuration_data_value");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return Integer.parseInt(value);
	}
	/**
	 * 根据数据名称Key，获取数据Value，其中value的数据类型为 “日期”
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Date getDateVlaue(String key)throws Exception{
		String value="";
		String sql="select cd.configuration_data_value from configuration_data cd where cd.configuration_data_key=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, key);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				value=resultSet.getString("cd.configuration_data_value");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date(simpleDateFormat.parse(value).getTime());
		return date;
	}
	
	/**
	 * 根据数据名称Key，获取数据Value，获取日期的秒数
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static long getLongVlaue(String key)throws Exception{
		String value="";
		String sql="select cd.configuration_data_value from configuration_data cd where cd.configuration_data_key=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, key);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				value=resultSet.getString("cd.configuration_data_value");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		if(key.equals("end_time")){
			return simpleDateFormat.parse(value).getTime()/1000+86400;
		}
		return simpleDateFormat.parse(value).getTime()/1000;
	}
	
	public static void updateValue(String key,String value)throws Exception{
		String sql="update configuration_data set configuration_data_value=? where configuration_data_key=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, value);
			preparedStatement.setString(2, key);
			
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
}
