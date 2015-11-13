package com.chinanetwork.performance.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.CompleteDepartmentScoreItem;
import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentKeyWork;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.ScoreItemCategory;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.DataBaseUtil;

public class GetScoreDao {
	
//====================================================以下向重点工作打分提供 数据====================================================================================================
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 负责根据从下拉列表中选择的部门名称，找出该部门所对应的重点工作条目
	 */
	public List<DepartmentScoreItem> getDepartmentWorkScoreItem(int departmentId)throws Exception{
		List<DepartmentScoreItem> departmentScoreItemList=null;
		String sql="select dsi.score_item_id,dsi.score_description,dsi.score_value,dsi.score_reference,dsi.department_id,d.department_name,d.higher_department_id," +
				"dsi.score_category_id,sc.score_category_name,dsi.score_creation_date,dsi.score_in_use " +
				"from department_score_item dsi,department d,score_category sc " +
				"where dsi.department_id=d.department_id and dsi.score_category_id=sc.score_category_id and d.department_id=? " +
				"and dsi.score_category_id=(" +
				"select score_category_id " +
				"from score_category " +
				"where score_category_name='重点工作'" +
				");";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, departmentId);
			resultSet=preparedStatement.executeQuery();
			departmentScoreItemList=new ArrayList<DepartmentScoreItem>();
			while(resultSet.next()){
				DepartmentScoreItem departmentScoreItem=new DepartmentScoreItem(resultSet.getInt("dsi.score_item_id"),"",resultSet.getString("dsi.score_description"), 
						resultSet.getFloat("dsi.score_value"),resultSet.getString("dsi.score_reference"),"", 
						new Department(resultSet.getInt("dsi.department_id"),resultSet.getString("d.department_name"),resultSet.getInt("d.higher_department_id")), 
						new ScoreItemCategory(resultSet.getInt("dsi.score_category_id"),resultSet.getString("sc.score_category_name")),
						resultSet.getDate("dsi.score_creation_date"),resultSet.getInt("dsi.score_in_use"));
				departmentScoreItemList.add(departmentScoreItem);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DataBaseUtil.close(resultSet, preparedStatement, connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return departmentScoreItemList;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 将重点工作条目打分记录插入到部门打分记录表中
	 * 1，打分部门
	 * 2，被打分部门
	 * 3，条目Id
	 * 4，分数
	 * 5，打分日期
	 */
	public void submitDepartmentWorkScore(int departmentFromId,int departmentToId,
			int scoreItemId,float score,Date date)throws Exception{
		String sql="insert into department_score_record(department_from_id,department_to_id,score_item_id," +
				"score,score_date, which_year, which_month, modify_times) values (?,?,?,?,?,?,?,?);";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, departmentFromId);
			preparedStatement.setInt(2, departmentToId);
			preparedStatement.setInt(3, scoreItemId);
			preparedStatement.setFloat(4, score);
			preparedStatement.setDate(5, date);
			preparedStatement.setInt(6, ConfigurationDataUtil.getIntegerVlaue("which_year"));
			preparedStatement.setInt(7, ConfigurationDataUtil.getIntegerVlaue("which_month"));
			preparedStatement.setInt(8, ConfigurationDataUtil.getIntegerVlaue("department_modify_times"));
			
			preparedStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	
	/**
	 * 根据重点条目Id，找到它所属的部门信息，而这个部门信息就是被打分部门的信息
	 * @param scoreItemId
	 * @return
	 * @throws Exception
	 */
	public Department getDepartmentFromItemId(int scoreItemId)throws Exception{
		Department department=null;
		String sql="select d.department_id,d.department_name,d.higher_department_id " +
				"from department_score_item dsi,department d " +
				"where dsi.department_id=d.department_id and dsi.score_item_id=?;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, scoreItemId);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				department=new Department(resultSet.getInt("d.department_id"),resultSet.getString("d.department_name"),
						resultSet.getInt("d.higher_department_id"));
			}
			preparedStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
		return department;
	}
	/**
	 * 根据打分的员工信息中的所属部门ID，找打打分部门信息
	 * @param staff
	 * @return
	 * @throws Exception
	 */
	public Department getDepartmentFromStaff(Staff staff)throws Exception{
		Department department=null;
		String sql="select d.department_id,d.department_name,d.higher_department_id " +
				"from department d,staff s " +
				"where s.department_id=d.department_id " +
				"and s.department_id=?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getDepartment().getDepartmentId());
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				department=new Department(resultSet.getInt("d.department_id"),resultSet.getString("d.department_name"),
						resultSet.getInt("d.higher_department_id"));
			}
			preparedStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
		return department;
	}
	
	/*
	public DepartmentScoreItem getDepartmentScoreItemByDepartmentScoreItemId(int itemId)throws Exception{
		DepartmentScoreItem departmentScoreItem=new DepartmentScoreItem();
		String sql="select dsi.score_item_id,dsi.score_item_name,dsi.score_description,dsi.score_value,dsi.score_reference,dsi.score_calculation_method," +
				"dsi.department_id,d.department_name,d.higher_department_id,dsi.score_category_id,sc.score_category_name," +
				"dsi.score_creation_date,dsi.score_in_use " +
				"from department_score_item dsi,department d,score_category sc " +
				"where dsi.department_id=d.department_id " +
				"and dsi.score_category_id=sc.score_category_id " +
				"and dsi.score_in_use=1 "+
				"and dsi.score_item_id=?;";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, itemId);
			
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				departmentScoreItem.setDepartment(new Department(resultSet.getInt("dsi.department_id"),resultSet.getString("d.department_name"),resultSet.getInt("d.higher_department_id")));
				departmentScoreItem.setDepartmentScoredItemId(resultSet.getInt("dsi.score_item_id"));
				departmentScoreItem.setDepartmentScoreName(resultSet.getString("d.department_name"));
				departmentScoreItem.setScoreCalculationMethod(resultSet.getString("dsi.score_calculation_method"));
				departmentScoreItem.setScoreDate(resultSet.getDate("dsi.score_creation_date"));
				departmentScoreItem.setScoreDescription(resultSet.getString("dsi.score_description"));
				departmentScoreItem.setScoreInUse(resultSet.getInt("dsi.score_in_use"));
				departmentScoreItem.setScoreItemCategory(
						new ScoreItemCategory(resultSet.getInt("dsi.score_category_id"),resultSet.getString("sc.score_category_name")));
				departmentScoreItem.setScoreReference(resultSet.getString("dsi.score_reference"));
				departmentScoreItem.setScoreValue(resultSet.getFloat("dsi.score_value"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return departmentScoreItem;
	}
	
	*/
	//填写重点工作修改次数
	public void insertModifyKeyWorkScoreTimes(int times,int fDepId,int tDepId,String category)throws Exception{
		String sql="insert into department_score_Revocation(department_from_id,department_to_id,score_category,remaining_times) values(?,?,?,?);";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,fDepId);
			preparedStatement.setInt(2,tDepId);
			preparedStatement.setString(3, category);
			preparedStatement.setInt(4, times);
			
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	

/**
	 * 提供现阶段 “绩效考评”中 “重点工作”已经被打分的部门
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<Department> getkeyWorkNowBeScoredDepartmentsModify(int year, int month)throws Exception{
		List<Department> departmentList=new ArrayList<Department>();
		String sql="select d.department_id,d.department_name,d.higher_department_id " +
		"from department_score_record dsr,department d " +
		"where dsr.department_to_id=d.department_id " +
		"and dsr.which_year = ? and dsr.which_month=? "+
		"and dsr.score_item_id in(" +
		"select dsi.score_item_id " +
		"from department_score_item dsi,score_category sc " +
		"where dsi.score_category_id=sc.score_category_id " +
		"and sc.score_category_id=3" +
		");";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				Department department=new Department(resultSet.getInt("d.department_id"),
						resultSet.getString("d.department_name"),resultSet.getInt("higher_department_id"));
				departmentList.add(department);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return departmentList;
	}
	
	//--------------------------------------------------------------------------------------------------------
	/**
	 * 根据所选部门名称，查出相应的重点工作条目	的改进方法
	 * 	从sql语句上规避时间问题，
	 * 	从创建CompleteDepartmentScoreItem类，规避分数无法存储问题
	 */
	public List<CompleteDepartmentScoreItem> getDepartmentWorkBeScoredItem(int departmentId,int year, int month)throws Exception{
		List<CompleteDepartmentScoreItem> completeDepartmentScoreItems=null;
		String sql="select dsi.score_item_id,dsi.score_description,dsi.score_value,dsi.score_reference,dsi.department_id,d.department_name," +
		"d.higher_department_id,dsi.score_category_id,sc.score_category_name,dsr.score_date,dsr.score, dsr.modify_times " +
		"from department d,department_score_item dsi,score_category sc,department_score_record dsr " +
		"where d.department_id=dsi.department_id and dsi.score_category_id=sc.score_category_id " +
		"and dsi.score_item_id=dsr.score_item_id and d.department_id=? " +
		"and dsr.which_year = ? and dsr.which_month = ? "+
		"and sc.score_category_id=3;";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, departmentId);
			preparedStatement.setInt(2, year);
			preparedStatement.setInt(3, month);
			
			completeDepartmentScoreItems=new ArrayList<CompleteDepartmentScoreItem>();
			
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				DepartmentScoreItem departmentScoreItem=new DepartmentScoreItem();
				departmentScoreItem.setDepartmentScoredItemId(resultSet.getInt("dsi.score_item_id"));
				departmentScoreItem.setDepartmentScoreName("");
				departmentScoreItem.setScoreDescription(resultSet.getString("dsi.score_description"));
				departmentScoreItem.setScoreValue(resultSet.getFloat("dsi.score_value"));
				departmentScoreItem.setScoreReference(resultSet.getString("dsi.score_reference"));
				departmentScoreItem.setScoreCalculationMethod("");
				departmentScoreItem.setDepartment(new Department(resultSet.getInt("dsi.department_id"),resultSet.getString("d.department_name"),resultSet.getInt("d.higher_department_id")));
				departmentScoreItem.setScoreItemCategory(new ScoreItemCategory(resultSet.getInt("dsi.score_category_id"),resultSet.getString("sc.score_category_name")));
				
				float score=resultSet.getFloat("dsr.score");
				int modifytimes = resultSet.getInt("dsr.modify_times");
				
				CompleteDepartmentScoreItem completeDepartmentScoreItem=new CompleteDepartmentScoreItem();
				completeDepartmentScoreItem.setDepartmentScoreItem(departmentScoreItem);
				completeDepartmentScoreItem.setScore(score);
				completeDepartmentScoreItem.setModifyTimes(modifytimes);
				
				completeDepartmentScoreItems.add(completeDepartmentScoreItem);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return completeDepartmentScoreItems;
	}
/**
	 * 修改评分记录中的分数
	 * 		
	 * 	注意：unix_timestamp(Date date) 是mysql中返回unix时间戳的方法，单位是 秒
	 * 	               而java中date.getTime()方法，的返回值的单位是毫秒
	 * 		    为了能够将两者相互比较，则应该换算一下
	 * @param scoreItemId
	 * @param time		换算之后的时间 long
	 * @param score
	 * @return
	 * @throws Exception
	 */
	public boolean modifyDepartmentWorkScoreRecord(int scoreItemId,long startTime,long endTime,float score)throws Exception{
		boolean isSuccess=false;
		String sql="update department_score_record set score=? ,modify_times = modify_times-1where score_item_id=? and unix_timestamp(score_date) between ? and ?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1, score);
			preparedStatement.setInt(2, scoreItemId);
			preparedStatement.setLong(3, startTime);
			preparedStatement.setLong(4, endTime);
			
			preparedStatement.execute();
			isSuccess=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
		
		return isSuccess;
	}
	//------------------------------------------------------------------------------------------
	/**
	 * 修改评分记录中的分数
	 * 		改进
	 * 		对于重点工作的得分修改，加上了打分部门的条件，使得查找更具唯一性，而不是依赖于条目本身的唯一
	 * @param scoreItemId
	 * @param time		换算之后的时间 long
	 * @param score
	 * @return
	 * @throws Exception
	 */
	public boolean modifyDepartmentWorkScoreRecord(Staff staff,int scoreItemId,int year, int month,float score)throws Exception{
		boolean isSuccess=false;
		String sql="update department_score_record set score=?, department_from_id=?, score_date=?, modify_times = modify_times-1 " +
				" where score_item_id=? and which_year=? and which_month=?;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1, score);
			preparedStatement.setInt(2, staff.getDepartment().getDepartmentId());
			preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
			preparedStatement.setInt(4, scoreItemId);
			preparedStatement.setInt(5, year);
			preparedStatement.setInt(6, month);
			
			preparedStatement.execute();
			isSuccess=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
		
		return isSuccess;
	}
	public boolean insertDepartmentScore(Map<Integer, Float> departmentScoreMap,int year,int month)throws Exception{
		String sql="insert into department_score(department_id,total_score,which_year,which_month,annotation) values(?,?,?,?,?);";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			connection.setAutoCommit(false);
			preparedStatement=connection.prepareStatement(sql);
			
			Iterator iterator=departmentScoreMap.keySet().iterator();
			while(iterator.hasNext()){
				int departmentId=(Integer)iterator.next();
				preparedStatement.setInt(1,departmentId);
				preparedStatement.setFloat(2, departmentScoreMap.get(departmentId));
				preparedStatement.setInt(3, year);
				preparedStatement.setInt(4, month);
				preparedStatement.setString(5,"");
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
			connection.commit();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
	}
	
	

/**
	 * 获取 员工 当前 有评分记录的条目的总数
	 * @param staff
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public int getStaffScoreRecordQuantity(Staff fStaff,Staff tStaff,int year, int month)throws Exception{
		int quantity=0;
		String sql="select count(*) quantity " +
				"from staff_score_record " +
				"where scored_to_staff_id=? and scored_from_staff_id=? " +
				"and which_year=? and which_month=?;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, tStaff.getStaffId());
			preparedStatement.setInt(2, fStaff.getStaffId());
			preparedStatement.setInt(3, year);
			preparedStatement.setInt(4, month);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				quantity=resultSet.getInt("quantity");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return quantity;
	}
	
	/**
	 * 获得 员工的评分细则总数
	 * @return
	 * @throws Exception
	 */
	public int getStaffScoreItemQuantity(Staff staff)throws Exception{
		int quantity=0;
		String sql="select count(*) quantity from staff_score_item ssi,staff_score_relation ssr " +
				"where ssi.score_item_id=ssr.score_item_id " +
				"and ssr.department_id=? " +
				"and ssr.post_id=? " +
				"and ssi.score_in_use=1;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getDepartment().getDepartmentId());
			preparedStatement.setInt(2, staff.getPost().getPostId());
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				quantity=resultSet.getInt("quantity");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return quantity;
	}
	
//=============================================================以下为计算“员工得分”提供数据================================================================================
	
	//---------------------------部门计算分数只能计算一次------------------------------------
	/**
	 * 获取当前部门绩效结果的数量
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public int getNowDepartmentScoreQuantity(int year,int month)throws Exception{
		int count=0;
		String sql="select count(*) c from department_score where which_year=? and which_month=?";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				count=resultSet.getInt("c");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return count;
	}
	//--------------------------------------员工计算只能计算一次----------------------------------------------
	public int getNowStaffScoreQuantity(int year,int month)throws Exception{
		int count=0;
		String sql="select count(*) c from staff_score where which_year=? and which_month=?";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, year);
			preparedStatement.setInt(2, month);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()){
				count=resultSet.getInt("c");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return count;
	}

//===================================================================================================================================
	
	public List<DepartmentKeyWork> getDepartmentKeyWorkList(int departmentId)throws Exception{
		List<DepartmentKeyWork> departmentKeyWorks=new ArrayList<DepartmentKeyWork>();
		
		String sql="select dwk.key_work_id,dwk.department_id,d.department_name,d.higher_department_id," +
				"dwk.key_work_content,dwk.start_year,dwk.start_month,dwk.end_year,dwk.end_month " +
				"from department_key_work dwk,department d where dwk.department_id=d.department_id and d.department_Id=?";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, departmentId);
			
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				DepartmentKeyWork departmentKeyWork=new DepartmentKeyWork(resultSet.getInt("dwk.key_work_id"),
						new Department(resultSet.getInt("dwk.department_id"),resultSet.getString("d.department_name"),resultSet.getInt("d.higher_department_id")),
						resultSet.getString("dwk.key_work_content"),resultSet.getInt("dwk.start_year"),
						resultSet.getInt("dwk.start_month"),resultSet.getInt("dwk.end_year"),resultSet.getInt("dwk.end_month"));
				departmentKeyWorks.add(departmentKeyWork);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return departmentKeyWorks;
	}
	
}
