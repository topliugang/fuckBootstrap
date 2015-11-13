package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.util.DataBaseUtil;

public class PostManageDao {

	public List<Post> getAllPost() throws Exception {
		List<Post> posts = null;

		String sql1 = " select post_id, post_name from post ";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			if (posts == null)
				posts = new ArrayList<Post>();
			Post post = new Post(rs.getInt("post_id"), rs
					.getString("post_name"));
			posts.add(post);
		}
		DataBaseUtil.close(rs, preparedStatement, connection);
		return posts;
	}
//----------------------------------------------------------------------------分页代码开始
	/**
	 * 获取总记录数
	 * @return
	 * @throws Exception
	 */
	public int getTotalRecored()throws Exception{
		int count=0;
		String sql="select count(*) crecored from post;";
		
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
	
	public List<Post> getAllPosts(int pageStart,int pageSize)throws Exception{
		List<Post> posts = new ArrayList<Post>();
		String sql=" select post_id, post_name from post limit ?,?";
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
				Post post = new Post(resultSet.getInt("post_id"), resultSet
						.getString("post_name"));
				posts.add(post);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return posts;
	}
//----------------------------------------------------------------------------分页代码结束
	public void addPost(Post post) throws Exception {
		String sql1 = " insert into post(post_name) values(?) ;";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, post.getPostName());
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
	}

	public void deletePost(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		String sql1 = " delete from post where post_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, parseInt);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);

	}

	public Post getPostById(int id) throws Exception {
		Post post = null;
		String sql1 = " select post_id, post_name from post where post_id=? ";
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			post = new Post(rs.getInt("post_id"), rs.getString("post_name"));
		}

		DataBaseUtil.close(rs, preparedStatement, connection);
		return post;

	}

	public void modifyPostById(int id, Post postToModify) throws Exception {
		// TODO Auto-generated method stub
		String sql1 = " update post set post_id = ?, post_name = ? where post_id = ? ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, id);
		ps.setString(2, postToModify.getPostName());
		ps.setInt(3, postToModify.getPostId());
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);

	}


	/***
	方法：
	副总2：副主任4
	主任3：副主任4，员工5
	副主任4：员工5

	区副经理7：副科长9
	科长8：副科长9，员工10
	副科长9：员工10
	***/

	public List<Staff> getScoreStaffsByPostId(Staff staff) throws Exception {

		StaffManageDao staffManageDao = new StaffManageDao();
		List<Staff> staffs = null;
		Connection connection = DataBaseUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		switch (staff.getPost().getPostId()) {
		case 1: //总经理1 ：人力资源副主任
		{
			String sql1="select s.staff_id from staff s,post p,department d " +
					"where s.post_id=p.post_id and s.department_id=d.department_id and d.department_name='人力资源部' and p.post_name='市公司部门副主任';";
			ps=connection.prepareStatement(sql1);
			rs=ps.executeQuery();
		}
		case 2: //副总2：副主任4
		{
			String sql1 = "select staff_id from staff where department_id in "+
						"(select department_id from department where higher_department_id = ?) "+
						"and post_id = 4;";
			//String sql2 = "select a.staff_id from staff a, department b "+
			//			"where a.department_id = b.department_id "+
			//			"and b.higher_department_id = ? "+
			//			"and a.post_id = 4;"
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, staff.getDepartment().getDepartmentId());
			rs = ps.executeQuery();
			break;
		}
		case 3: //主任3：副主任4，员工5
		{
			String sql1 = "select staff_id from staff where department_id=? "+
						"and staff_id <> ? and (post_id = 4 or post_id = 5);";
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, staff.getDepartment().getDepartmentId());
			ps.setInt(2, staff.getStaffId());
			rs = ps.executeQuery();
			break;
		}
		case 4: //副主任4：员工5
		{
			String sql1 = "select staff_id from staff where department_id=? and post_id = 5;";
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, staff.getDepartment().getDepartmentId());
			rs = ps.executeQuery();
			break;
		}
		/*case 7: //区副经理7：副科长9
		{
			String sql1 = "select staff_id from staff where department_id in "+
						"(select department_id from department where higher_department_id = ?) "+
						"and post_id = 9;";
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, staff.getDepartment().getDepartmentId());
			rs = ps.executeQuery();
			break;
		}
		case 8: //科长8：副科长9，员工10
		{
			String sql1 = "select staff_id from staff where department_id=? and staff_id <> ? and (post_id = 9 or post_id = 10)";
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, staff.getDepartment().getDepartmentId());
			ps.setInt(2, staff.getStaffId());
			rs = ps.executeQuery();
			break;
		}
		case 9: //副科长9：员工10
		{
			String sql1 = "select staff_id from staff where department_id=? and post_id = 10;";
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, staff.getDepartment().getDepartmentId());
			rs = ps.executeQuery();
			break;
		}*/

		default:
			break;

		}

		while (rs != null && rs.next()) {
				if (staffs == null)
					staffs = new ArrayList<Staff>();
				Staff sf = staffManageDao.getStaffById(rs.getInt("staff_id"));
				staffs.add(sf);
		}

		return staffs;
	}
	
	
	/**
	 * 找出所有参与部门互评的部门
	 * post_id 1,2,3  6,7
	 * @return
	 */
	public List<Department> getAllDepartmentScoreDepartment()throws Exception{
		List<Department> departmentList=new ArrayList<Department>();
		
		//该sql语句将参与部门互评的部门硬性的规定为区营业部以上的部门，太不灵活
		/*String sql="select d.department_id,d.department_name,d.higher_department_id " +
				"from staff s,department d,post p " +
				"where s.department_id=d.department_id and s.post_id=p.post_id " +
				"and p.post_id in (1,2,3,6,7)";*/
		
		//改进的sql语句主表是部门互评关联表，十分的灵活
		String sql="select distinct dsr.department_id,outd.department_name,outd.higher_department_id " +
		"from department outd,department_score_relation dsr where dsr.department_id=outd.department_id " +
		"and dsr.department_id not in (" +
		"select ind.department_id " +
		"from department ind,staff s,post p " +
		"where ind.department_id=s.department_id and s.post_id=p.post_id " +
		"and p.post_name in ('区营业部科室科长','区营业部副经理'))";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Department deparment=new Department(resultSet.getInt("dsr.department_id"),resultSet.getString("outd.department_name"),resultSet.getInt("outd.higher_department_id"));
				departmentList.add(deparment);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return departmentList;
	}
	
	/**
	 * 返回检测评分过程的结果，没有打分的部门以及对象
	 * @return
	 * @throws Exception
	 */
	public Map<Department,List<DepartmentScoreItem>> getNotScoredDepartmentScoreItem(long startTime,long endTime)throws Exception{
		Map<Department,List<DepartmentScoreRecord>> scoredMap=new HashMap<Department,List<DepartmentScoreRecord>>();
		Map<Department,List<DepartmentScoreItem>> shouldScoredMap=new HashMap<Department,List<DepartmentScoreItem>>();
		Map<Department,List<DepartmentScoreItem>> notScoredMap=new HashMap<Department,List<DepartmentScoreItem>>();
		String sqla="select dsr.department_from_id,foutd.department_name,dsr.department_to_id,toutd.department_name,dsr.score_item_id "+
					"from department_score_record dsr,department foutd,department toutd "+
					"where dsr.department_from_id=foutd.department_id and dsr.department_to_id=toutd.department_id "+ 
					"and unix_timestamp(dsr.score_date) between ? and ? "+
					"and foutd.department_id in ("+
							"select distinct dsre.department_id "+
							"from department outd,department_score_relation dsre "+
							"where dsre.department_id=outd.department_id "+
							"and dsre.department_id not in ("+
								"select ind.department_id "+
								"from department ind,staff s,post p "+
								"where ind.department_id=s.department_id and s.post_id=p.post_id "+ 
								"and p.post_name in ('区营业部科室科长','区营业部副经理','总经理')));";
		
		String sqlb="select odsr.department_id,foutd.department_name,dsi.department_id,toutd.department_name,dsi.score_item_id "+
					"from department_score_relation odsr,department_score_item dsi,department foutd,department toutd "+
					"where dsi.score_item_id=odsr.score_item_id and odsr.department_id=foutd.department_id "+
					"and dsi.department_id=toutd.department_id "+ 
					"and dsi.score_in_use=1 "+
					"and foutd.department_id in ("+
						"select distinct idsr.department_id "+ 
						"from department outd,department_score_relation idsr "+ 
						"where idsr.department_id=outd.department_id "+
						"and idsr.department_id not in ("+
							"select ind.department_id "+
							"from department ind,staff s,post p "+
							"where ind.department_id=s.department_id and s.post_id=p.post_id "+
							"and p.post_name in ('区营业部科室科长','区营业部副经理','总经理')));";;
		
		Connection connection=null;
		PreparedStatement preparedStatementa=null;
		PreparedStatement preparedStatementb=null;
		ResultSet resultSeta=null;
		ResultSet resultSetb=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatementa=connection.prepareStatement(sqla);
			preparedStatementa.setLong(1, startTime);
			preparedStatementa.setLong(2, endTime);
			resultSeta=preparedStatementa.executeQuery();
			while(resultSeta.next()){
				Department fromDepartment=new Department();
				fromDepartment.setDepartmentId(resultSeta.getInt("dsr.department_from_id"));
				fromDepartment.setDepartmentName(resultSeta.getString("foutd.department_name"));
				
				DepartmentScoreItem departmentScoreItem=new DepartmentScoreItem();
				departmentScoreItem.setDepartmentScoredItemId(resultSeta.getInt("dsr.score_item_id"));
				Department toDepartment=new Department();
				toDepartment.setDepartmentId(resultSeta.getInt("dsr.department_to_id"));
				toDepartment.setDepartmentName(resultSeta.getString("toutd.department_name"));
				DepartmentScoreRecord departmentScoreRecord=new DepartmentScoreRecord();
				departmentScoreRecord.setDepartmentScoreItem(departmentScoreItem);
				departmentScoreRecord.setDepartmentTo(toDepartment);
				
				if(scoredMap.get(fromDepartment)==null){
					List<DepartmentScoreRecord> departmentScoreRecords=new ArrayList<DepartmentScoreRecord>();
					departmentScoreRecords.add(departmentScoreRecord);
					scoredMap.put(fromDepartment, departmentScoreRecords);
				}else{
					scoredMap.get(fromDepartment).add(departmentScoreRecord);
				}
			}
			
			preparedStatementb=connection.prepareStatement(sqlb);
			resultSetb=preparedStatementb.executeQuery();
			while(resultSetb.next()){
				Department fromDepartment=new Department();
				fromDepartment.setDepartmentId(resultSetb.getInt("odsr.department_id"));
				fromDepartment.setDepartmentName(resultSetb.getString("foutd.department_name"));
				
				DepartmentScoreItem departmentScoreItem=new DepartmentScoreItem();
				departmentScoreItem.setDepartmentScoredItemId(resultSetb.getInt("dsi.score_item_id"));
				Department toDepartment=new Department();
				toDepartment.setDepartmentId(resultSetb.getInt("dsi.department_id"));
				toDepartment.setDepartmentName(resultSetb.getString("toutd.department_name"));
				departmentScoreItem.setDepartment(toDepartment);
				
				if(shouldScoredMap.get(fromDepartment)==null){
					List<DepartmentScoreItem> departmentScoreItems=new ArrayList<DepartmentScoreItem>();
					departmentScoreItems.add(departmentScoreItem);
					shouldScoredMap.put(fromDepartment, departmentScoreItems);
				}else{
					List<DepartmentScoreItem> departmentScoreItems=new ArrayList<DepartmentScoreItem>();
					departmentScoreItems.add(departmentScoreItem);
				}
			}
			
			Iterator iterator=shouldScoredMap.keySet().iterator();
			while(iterator.hasNext()){
				Department departmentKey=(Department)iterator.next();
				List<DepartmentScoreItem> departmentScoreItems=shouldScoredMap.get(departmentKey);
				List<DepartmentScoreRecord> departmentScoreRecords=scoredMap.get(departmentKey);
				if(departmentScoreRecords!=null&&departmentScoreRecords.size()!=0){
					for(int i=0;i<departmentScoreItems.size();i++){
						DepartmentScoreItem departmentScoreItem=departmentScoreItems.get(i);
						boolean scoredState=false;
						for(int j=0;j<departmentScoreRecords.size();j++){
							if(departmentScoreItem.getDepartment().getDepartmentId()==departmentScoreRecords.get(j).getDepartmentTo().getDepartmentId()
									&&departmentScoreItem.getDepartmentScoredItemId()==departmentScoreRecords.get(j).getDepartmentScoreItem().getDepartmentScoredItemId()){
								scoredState=true;
								break;
							}
						}
						if(!scoredState){
							if(notScoredMap.get(departmentKey)==null){
								List<DepartmentScoreItem> departmentScoreItemList=new ArrayList<DepartmentScoreItem>();
								departmentScoreItemList.add(departmentScoreItem);
								notScoredMap.put(departmentKey, departmentScoreItemList);
							}else{
								notScoredMap.get(departmentKey).add(departmentScoreItem);
							}
						}
					}
				}else{
					notScoredMap.put(departmentKey,null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			preparedStatementb.close();
			resultSetb.close();
			DataBaseUtil.close(resultSeta, preparedStatementa, connection);
		}
		return notScoredMap;
	}
	
	
	/*有个人打分权限的  2,3,4, 7,8,9
	 * 
	 * */
	
	public List<Staff> getOwnScorePermissionsStaffs()throws Exception{
		
		StaffManageDao staffManageDao = new StaffManageDao();
		List<Staff> staffList=new ArrayList<Staff>();
		String s = "select staff_id from staff where post_id in(2,3,4,7,8,9);";
		String sql="select s.staff_id,s.staff_name,s.department_id,s.post_id,p.post_name,s.department_id,d.department_name "+
		 "from staff s,role r,role_funcpage_relation rfr,funcpage f,department d,post p "+ 
		 "where s.department_id=d.department_id and s.post_id=p.post_id "+
		 "and s.role_id=r.role_id and r.role_id=rfr.role_id and rfr.funcpage_id=f.funcpage_id "+ 
		 "and f.funcpage_name='员工打分'";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				Staff staff=new Staff();//staffManageDao.getStaffById(resultSet.getInt("s.staff_id"));
				staff.setStaffId(resultSet.getInt("s.staff_id"));
				staff.setStaffName(resultSet.getString("s.staff_name"));
				Post post=new Post();
				post.setPostId(resultSet.getInt("s.post_id"));
				post.setPostName(resultSet.getString("p.post_name"));
				staff.setPost(post);
				Department department=new Department();
				department.setDepartmentId(resultSet.getInt("s.department_id"));
				department.setDepartmentName(resultSet.getString("d.department_name"));
				staff.setDepartment(department);
				staffList.add(staff);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffList;
	}
	
	
	/*
	 * 返回分管staff
	 * 
	 */
	public List<Staff> getFenguanStaffList(Staff staff) throws Exception
	{
		String sql = null;
		switch(staff.getPost().getPostId())
		{
		case 2://副总
			sql = "select staff_id from staff where department_id in "+
					"(select department_id from department where higher_department_id=?) "+
					"and post_id in(3,4,5) ;";
			break;
		case 3://主任
			sql = "select staff_id from staff where department_id = ? "+
					"and post_id in(4,5); ";
			break;
		case 4://副主任
			sql = "select staff_id from staff where department_id = ? "+
			"and post_id = 5; ";
			break;
		}
		StaffManageDao staffManageDao = new StaffManageDao();
		List<Staff> staffList=new ArrayList<Staff>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getDepartment().getDepartmentId());
			resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()){
				int staffId = resultSet.getInt("staff_id");
				Staff s = staffManageDao.getStaffById(staffId);
				staffList.add(s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffList;
	}
	

}
