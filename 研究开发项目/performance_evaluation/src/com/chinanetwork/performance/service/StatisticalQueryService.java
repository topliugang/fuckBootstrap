package com.chinanetwork.performance.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScore;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScore;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.dao.CalculateScoreDao;
import com.chinanetwork.performance.dao.PostManageDao;
import com.chinanetwork.performance.dao.StatisticalQueryDao;
import com.chinanetwork.performance.dao.TaskProgressDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.MathUtil;

/**
 * 主要负责统计查询的业务逻辑
 * @author Administrator
 *
 */
public class StatisticalQueryService {

	private StatisticalQueryDao statisticalQueryDao=new StatisticalQueryDao();
	
	//根据个人的postid 返回可查询得分的部门和个人列表
	public List<Department> getQueryDepartmentList(Staff staff) throws Exception
	{
		List<Department> ret = null;
		DepartmentManageService departmentManageService = new DepartmentManageService();
		switch(staff.getPost().getPostId())
		{
		case 1:
		case 14:
			ret = departmentManageService.getAllDepartments();
			break;
		default:
			ret = new ArrayList<Department>();
			ret.add(staff.getDepartment());
			//Department quanbu = new Department();
			//quanbu.setDepartmentId(-1);
			//quanbu.setDepartmentName("所有部门");
			//ret.add(quanbu);
			break;
		}
		return ret;
	}
	
	//根据个人的postid 返回可查询得分的部门和个人列表
	public List<Staff> getQueryStaffList(Staff staff) throws Exception
	{
		List<Staff> staffs = null;
		switch(staff.getPost().getPostId())
		{
		case 1://总经理
		case 14://管理员
			StaffManageService staffManageService = new StaffManageService();
			staffs = staffManageService.getAllStaffs();
			Staff quanbu = new Staff();
			quanbu.setStaffId(-1);
			quanbu.setStaffName("所有员工");
			staffs.add(quanbu);
			break;
		case 2://副总
		case 3://主任
		case 4://副主任
			PostManageDao postManageDao = new PostManageDao();
			staffs = postManageDao.getFenguanStaffList(staff);
			staffs.add(staff);
			break;
		default://员工
			staffs = new ArrayList<Staff>();
			staffs.add(staff);
			break;
		}
		return staffs;
	}
	
	
	/**
	 * 获得部门得分列表
	 * @param departmentName
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentScore> getDepartmentScoreList(int year, int month)throws Exception{
		return statisticalQueryDao.getDepartmentScoreList(year, month);
	}
	
	/*
	 * 部门得分列表
	 * 
	 */
	
	public List<DepartmentScore> getDepartmentScoreList(int departmentId, int year, int month)throws Exception{
		List<DepartmentScore> list = null;
		if(departmentId == -1)//all department
		{
			list = statisticalQueryDao.getDepartmentScoreList(year, month);
		}
		else
		{
			list = new ArrayList<DepartmentScore>(); 
			list.add(statisticalQueryDao.getDepartmentScore(departmentId, year, month));
		}
		for(int i =0; i<list.size(); i++)//添加评论
		{
			DepartmentScore departmentScore = list.get(i);
			departmentScore.setInstruction(statisticalQueryDao.getDepartmentComment(departmentScore.getDepartment().getDepartmentId(), year, month));
		}
		return list; 
	}

	/*
	 * 个人得分列表
	 * 
	 */
	public List<StaffScore> getStaffScoreList(int staffId, int year, int month) throws Exception
	{
		List<StaffScore> list = null;
		if(staffId == -1)
		{
			list = statisticalQueryDao.getStaffScoreList(year, month);
		}
		else
		{
			list = new ArrayList<StaffScore>();
			list.add(statisticalQueryDao.getStaffScore(staffId, year, month));
		}
		return list;
	}
	
	/*
	 * 
	 * 部门得分
	 */
	public float getDepartmentScore(int departmentId, int year,int month) throws Exception {
		DepartmentScore departmentScore = statisticalQueryDao.getDepartmentScore(departmentId, year, month);
		if(departmentScore != null)
			return departmentScore.getScore();
		else
			return 0;
	}
	
	
	/*
	 * 
	 * 返回List<Float>(3)
	 * 0 原始得分
	 * 1 部门加成
	 * 2 任务进度加成
	 */
	public List<Float> getOriginScore(Staff staff, int year, int month) throws Exception
	{
		List<Float> pack = null;
		CalculateScoreDao calculateScoreDao=new CalculateScoreDao();
		float task = new TaskProgressDao().getTaskProgress(year, month).getTaskProgress();
		
		switch(staff.getPost().getPostId())
		{
		case 4:
		case 5:
			System.out.println("员工名称："+staff.getStaffName());
			Map<Staff,List<StaffScoreRecord>> staffScoreRecordMap=new HashMap<Staff,List<StaffScoreRecord>>();
			List<StaffScoreRecord> staffScoreRecordList=calculateScoreDao.selectAllStaffScoreRecordByStaff(staff, year, month);
			for(int i=0;i<staffScoreRecordList.size();i++){
				StaffScoreRecord staffScoreRecord=staffScoreRecordList.get(i);
				Staff fStaff=staffScoreRecord.getScoredFromStaff();
				if(!staffScoreRecordMap.containsKey(fStaff)){
					List<StaffScoreRecord> staffScoredRecordFromList=new ArrayList<StaffScoreRecord>();
					staffScoredRecordFromList.add(staffScoreRecord);
					staffScoreRecordMap.put(fStaff, staffScoredRecordFromList);
				}else{
					staffScoreRecordMap.get(fStaff).add(staffScoreRecord);
				}
			}
			
			Map<Staff,Float> staffScoreMap=new HashMap<Staff,Float>();
			
			Iterator iterator=staffScoreRecordMap.keySet().iterator();
			
			while(iterator.hasNext()){
				float totalScore=0;
				Staff staffKey=(Staff)iterator.next();
				List<StaffScoreRecord> ssRecordList=staffScoreRecordMap.get(staffKey);
				//System.out.println(ssRecordList);
				for(int i=0;i<ssRecordList.size();i++){
					StaffScoreRecord record = ssRecordList.get(i);
					totalScore=totalScore+record.getScore()*record.getStaffScoreItem().getScoreWeight();
					System.out.println("打分记录:"+record.getScoredFromStaff().getStaffName()+": "+record.getScore()+
							" * "+record.getStaffScoreItem().getScoreWeight()+"   totalScore是:" + totalScore);
				}
				staffScoreMap.put(staffKey, totalScore);
			}
			String postName=staff.getPost().getPostName();
			String departmentName=staff.getDepartment().getDepartmentName();
			
			ScoreService getScoreService = new ScoreService();
			
			if(postName.equals("市公司部门副主任")&&departmentName.equals("人力资源部")){
				pack=getScoreService.calculateStaff2(staff,staffScoreMap,year,month,"总经理","市公司部门主任",0.3f,0.7f,0.5f,0.5f);
			}else if(postName.equals("市公司部门副主任")){
				pack=getScoreService.calculateStaff2(staff,staffScoreMap,year,month,"副总经理","市公司部门主任",0.3f,0.7f,0.5f,0.5f);
			}else if(postName.equals("市公司员工")){
				pack=getScoreService.calculateStaff2(staff,staffScoreMap,year,month,"市公司部门主任","市公司部门副主任",0.7f,0.3f,0.7f,0.3f);
			}
			
			float finalScore = task*0.5f+pack.get(1)*0.5f;
			System.out.println(staff.getStaffName()+"的最终得分: "+task+"*0.5f+"+pack.get(1)+"*0.5f = "+finalScore);
			pack.add(2, MathUtil.baoliu(finalScore, 2));
			break;
			
		case 3:
			
			DepartmentScore departmentScore=calculateScoreDao.getScoreOfDepartmentBelongStaff(staff.getDepartment().getDepartmentId(), year, month);
			pack = new ArrayList<Float>(3);
			
			pack.add(0, MathUtil.baoliu(departmentScore.getScore(), 2));
			pack.add(1, MathUtil.baoliu(departmentScore.getScore(), 2));
			float totalScore=task*0.5f+departmentScore.getScore()*0.5f;
			pack.add(2, MathUtil.baoliu(totalScore, 2));
			break;
			
		case 2:
			pack = new ArrayList<Float>(3);
			System.out.println(pack.size());
			pack.add(0, Float.valueOf((float) 0));
			pack.add(1, Float.valueOf((float) 0));
			pack.add(2, statisticalQueryDao.getStaffScore(staff.getStaffId(), year, month).getTotalScore());
			
			break;
			
		default:
			pack = new ArrayList<Float>(3);
			System.out.println(pack.size());
			pack.add(0, Float.valueOf((float) 0));
			pack.add(1, Float.valueOf((float) 0));
			pack.add(2, Float.valueOf((float) 0));
			break;
		}
		return pack;
	}
	

	//===================================================在Excel表格中添加一列，工资列=====================================================
	
	public float getSalaryByStaff(Staff staff)throws Exception{
		float salary=0;
		salary=statisticalQueryDao.getSalaryByStaffId(staff.getStaffId());
		return salary;
	}
	
	public List<Integer> departmentScoredYears() throws Exception
	{
		return statisticalQueryDao.departmentScoredYears();
	}
	public List<Integer> departmentScoredMonths() throws Exception
	{
		return statisticalQueryDao.departmentScoredMonths();
	}
	public List<Integer> staffScoredYears() throws Exception
	{
		return statisticalQueryDao.staffScoredYears();
	}
	public List<Integer> staffScoredMonths() throws Exception
	{
		return statisticalQueryDao.staffScoredMonths();
	}
}
