package com.chinanetwork.performance.service;
/**
 * 负责计算得分的业务逻辑层
 */

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.CompleteDepartmentScoreItem;
import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentKeyWork;
import com.chinanetwork.performance.bean.DepartmentScore;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScore;
import com.chinanetwork.performance.bean.StaffScoreExcel;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.dao.CalculateScoreDao;
import com.chinanetwork.performance.dao.GetScoreDao;
import com.chinanetwork.performance.dao.PostManageDao;
import com.chinanetwork.performance.dao.ScoreDao;
import com.chinanetwork.performance.dao.StaffManageDao;
import com.chinanetwork.performance.dao.TaskProgressDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.MathUtil;


/*
 * 算分
 * 
 */

public class ScoreService {
		
	private GetScoreDao getScoreDao=new GetScoreDao();
	private PostManageDao postManageDao = new PostManageDao();
	private ScoreDao scoreDao = new ScoreDao();
	
//=============================================================以下向部门重点工作打分提供服务================================================================
//==========================添加功能，在重点工作打分页面，显示重点工作列表===========================
	

	
	/**
	 * 重点工作条目打分
	 * @param staff
	 * @param itemsIds
	 * @param departmentScores
	 * @return
	 */
	public boolean submitDepartmentWorkScore(Staff staff,List<String> itemsIds,List<String> departmentScores){
		try{
			Department departmentFrom=getScoreDao.getDepartmentFromStaff(staff);
			List<Department> departmentTos=new ArrayList<Department>();
			for(String s:itemsIds){
				int itemsId=Integer.parseInt(s);
				departmentTos.add(getScoreDao.getDepartmentFromItemId(itemsId));
			}
			Date date=new Date(System.currentTimeMillis());
			for(int i=0;i<itemsIds.size();i++){
				int itemsId=Integer.parseInt(itemsIds.get(i));
				float departmentScore=Float.parseFloat(departmentScores.get(i));
				getScoreDao.submitDepartmentWorkScore(departmentFrom.getDepartmentId(), departmentTos.get(i).getDepartmentId(), itemsId, departmentScore, date);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
		} 
		return false;
	}
	//填写修改次数
	public void setModifyKeyWorkScoreTimes(String times,int fDepId,int tDepId,String category)throws Exception{
		int time=Integer.parseInt(times);
		try{
			getScoreDao.insertModifyKeyWorkScoreTimes(time, fDepId, tDepId, category);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

//=============================================================以下向重点得分修改提供服务=====================================================================
	
	/**
	 * 当用户进入“重点工作打分修改界面”时，需要“已打分部门”下拉列表
	 * 该方法提供这样的功能
	 */
	public List<Department> getBeScoredDepartmentList()throws Exception{
		//获取绩效考评的开始和结束时间
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		//获取“重点工作”被打分完毕的部门列表
		List<Department> departmentsScored=new ArrayList<Department>();
		departmentsScored=getScoreDao.getkeyWorkNowBeScoredDepartmentsModify(year, month);
		return departmentsScored;
	}
	

//-----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 根据在下拉列表中选择的“部门名称”，返回其在打分就表中的对应的记录，包括分数
	 * 改进方法
	 */
	public List<CompleteDepartmentScoreItem>getDepartmentWorkBeScoredItem(String departmentIdStr)throws Exception{
		int departmentId=Integer.parseInt(departmentIdStr);
		//获取绩效考评的开始和结束时间
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		
		List<CompleteDepartmentScoreItem> completeDepartmentScoreItems=getScoreDao.getDepartmentWorkBeScoredItem(departmentId, year, month);
		
		return completeDepartmentScoreItems;
	}
//-----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 为修改部门重点工作得分提供服务
	 * @param itemsIds
	 * @param departmentScores
	 * @return
	 * @throws Exception
	 */
	public boolean modifyDepartmentWorkScoreRecord(List<String>itemsIds ,List<String> departmentScores)throws Exception{
		boolean isSuccess=false;
		
		//获取绩效考评的开始和结束时间
		long startTime=ConfigurationDataUtil.getLongVlaue("start_time");
		long endTime=ConfigurationDataUtil.getLongVlaue("end_time");
		//修改相应评分记录中的分数
		for(int i=0;i<itemsIds.size();i++){
			isSuccess=getScoreDao.modifyDepartmentWorkScoreRecord(Integer.parseInt(itemsIds.get(i)), startTime, endTime, Float.parseFloat(departmentScores.get(i)));
		}
		return isSuccess;
	}
	
	//------------------------------------------------------------------------------------
	/**
	 * 为修改部门重点工作得分提供服务
	 * @param itemsIds
	 * @param departmentScores
	 * @return
	 * @throws Exception
	 */
	public boolean modifyDepartmentWorkScoreRecord(Staff staff,List<String>itemsIds ,List<String> departmentScores)throws Exception{
		boolean isSuccess=false;
		//获取绩效考评的开始和结束时间
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		//修改相应评分记录中的分数
		for(int i=0;i<itemsIds.size();i++){
			isSuccess=getScoreDao.modifyDepartmentWorkScoreRecord(staff,Integer.parseInt(itemsIds.get(i)), year, month, Float.parseFloat(departmentScores.get(i)));
		}
		return isSuccess;
	}
	//------------------------------------------------------------------------------------
//==================================================================以下给部门互评得分提供服务======================================================================
	
	/**
	 * 判断部门互评的评分过程是否结束
	 * @return
	 * @throws Exception
	 */
	public List<String> confirmAccomplishDepartmentScore()throws Exception{
		List<String> messageList=new ArrayList<String>();
		//获取绩效考评的开始和结束时间
		long startTime=ConfigurationDataUtil.getLongVlaue("start_time");
		long endTime=ConfigurationDataUtil.getLongVlaue("end_time");
		
		Map<Department,List<DepartmentScoreItem>> notScoredMap=new HashMap<Department,List<DepartmentScoreItem>>();
		notScoredMap=postManageDao.getNotScoredDepartmentScoreItem(startTime, endTime);
		System.out.println(notScoredMap.size());
		if(notScoredMap.size()>0){
			Iterator iterator=notScoredMap.keySet().iterator();
			while(iterator.hasNext()){
				Department departmentKey=(Department)iterator.next();
				List<DepartmentScoreItem> departmentScoreItems=notScoredMap.get(departmentKey);
				if(departmentScoreItems==null){
					String message=departmentKey.getDepartmentName()+"还没有开始评分";
					messageList.add(message);
				}else{
					for(int i=0;i<departmentScoreItems.size();i++){
						String message=departmentKey.getDepartmentName()+"对"+departmentScoreItems.get(i).getDepartment().getDepartmentName()+"的评分还没有结束";
						messageList.add(message);
					}
				}
			}
		}
		return messageList;
	}
	
//====================================================================以下给员工得分提供服务=================================================================
	/**
	 * 确定员工打分过程是否结束，如果没有结束，提供具体的细节信息
	 */
	public List<String> confirmAccomplishStaffScore() throws Exception{
		List<String> messageList=new ArrayList<String>();
		//获取绩效考评的开始和结束时间
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		//获取拥有“员工打分”权限的员工，除管理员之外
		List<Staff> ownScorePermissionsStaffList=postManageDao.getOwnScorePermissionsStaffs();
		//循环员工列表
		List<String> diff = new ArrayList<String>();
		for(int i=0;i<ownScorePermissionsStaffList.size();i++){
			Staff ownScorePermissionsStaff=ownScorePermissionsStaffList.get(i);
			//该员工打分对象列表
			List<Staff> beScoredStaffList=null;
			
			beScoredStaffList = postManageDao.getScoreStaffsByPostId(ownScorePermissionsStaff);
			
			if(beScoredStaffList == null )
			{
				continue;
			}
			
			//循环打分对象列表
			for(int j=0;j<beScoredStaffList.size();j++){
				Staff beScoreStaff=beScoredStaffList.get(j);
				//获得该打分对象的评分细则的打分记录的总条数
				int beScoredQuantity=getScoreDao.getStaffScoreRecordQuantity(ownScorePermissionsStaff,beScoreStaff, year,month);
				//获得该打分对象的评分细则的总条数
				int scoreItemQuantity=getScoreDao.getStaffScoreItemQuantity(beScoreStaff);
				System.out.println(ownScorePermissionsStaff.getStaffName());
				System.out.println("打分记录："+beScoredQuantity);
				System.out.println("应打分记录："+scoreItemQuantity);
				//判断该打分对象的评分过程是否结束
				if(beScoredQuantity==scoreItemQuantity){
					//结束
					continue;
				}else{
					//没有结束
					String message=ownScorePermissionsStaff.getDepartment().getDepartmentName()+ownScorePermissionsStaff.getStaffName()+"对"+beScoreStaff.getStaffName()+"的评分还没有结束";
					messageList.add(message);
				}
			}
		}
		return messageList;
	}
	
	//----------------------------(2)部门计算分数，应该只能计算一次
	/**
	 * 确定“部门算分”是否已经执行过
	 */
	public boolean confirmOnlyCalculateDepartmentScore()throws Exception{
		//获取绩效年份和月份
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		int quantity=getScoreDao.getNowDepartmentScoreQuantity(year, month);
		
		if(quantity>=1){
			return false;
		}else{
			return true;
		}
	}
	//---------------------------(3)员工计算分数，应该只能计算一次
	/**
	 * 确定“员工算分”是否已经执行过
	 */
	public boolean confirmOnlyCalculateStaffScore()throws Exception{
		//获取绩效年份和月份
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		int quantity=getScoreDao.getNowStaffScoreQuantity(year, month);
		if(quantity>=1){
			return false;
		}else{
			return true;
		}
	}
	//----------------------------(4)员工算分之前，应该录入“任务进度”
	/**
	 * 确定“任务进度”是否已经被录入
	 */
	public boolean confirmTaskProgressInput()throws Exception{
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		boolean flag=false;
		TaskProgressService taskProgressService = new TaskProgressService();
		flag=taskProgressService.check(year, month);
		
		return flag;
	}


	public CalculateScoreDao calculateScoreDao = new CalculateScoreDao();
	public WeekMeetingService weekMeetingService = new WeekMeetingService();

	// ----------------------------------------------------------------------------------------------------------------------部门绩效计算开始
	public boolean calculateDepartmentScore() throws Exception {
		try {
			// 计算市公司各部门，各区营业部的部门的得分
			calculateFirstDepartmentScore();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * First：市公司各部门，各区营业部的部门得分
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void calculateFirstDepartmentScore() throws Exception {
		try {
			// 修改
			int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
			int month = ConfigurationDataUtil.getIntegerVlaue("which_month");

			// map<department_to_id, score>
			Map<Integer, Float> scoreList = calculateScoreDao
					.fuckDepartmentScore(year, month);
			// 周例会检测，加分
			scoreList = weekMeetingService
					.updateDepartmentScoreByWeekAndMonthMeeting(scoreList,
							year, month);

			getScoreDao.insertDepartmentScore(scoreList, year, month);

			//calculateScoreDao.fuckComment(year, month);

			System.out.println("caonima");
			System.out.println("caonima");
			System.out.println("caonima");
			System.out.println("caonima");
			System.out.println("caonima");
			System.out.println("caonima");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 市公司各部门副主任和员工，以及各区营业部的各个科室副科长和科员的绩效考评的计算公式
	 * 
	 * @param staff
	 *            被打分的员工
	 * @param staffScoreMap
	 *            员工各项评分细则的得分Map，以打分者的职位不同分类
	 * @param year
	 *            绩效考评年份
	 * @param month
	 *            绩效考评月份
	 * @param fStaffA
	 *            公式参数，A打分员工的职位名称
	 * @param fStaffB
	 *            公式参数，B打分员工的职位名称
	 * @param coefficientA
	 *            公式参数，A打分员工对应的权重
	 * @param coefficientB
	 *            公式参数，B打分员工对应的权重
	 * @param staffScoreCoefficient
	 *            公式参数，员工评分得分所占的权重
	 * @param depScoreCoefficient
	 *            公式参数，员工所属的部门得分所占的权重
	 * @return totalScore 返回根据公式计算出的分数
	 * @throws Exception
	 */
	public float calculateStaff(Staff staff, Map<Staff, Float> staffScoreMap,
			int year, int month, String fStaffA, String fStaffB,
			float coefficientA, float coefficientB,
			float staffScoreCoefficient, float depScoreCoefficient)
			throws Exception {
		float totalScore = 0;
		Iterator iterator = staffScoreMap.keySet().iterator();

		List<Float> scoreListA = new ArrayList<Float>();
		List<Float> scoreListB = new ArrayList<Float>();
		while (iterator.hasNext()) {
			Staff staffKey = (Staff) iterator.next();
			System.out.println("打分员工名称：" + staffKey.getStaffName());
			System.out.println("所打分数：" + staffScoreMap.get(staffKey));
			if (staffKey.getPost().getPostName().equals(fStaffA)) {
				scoreListA.add(staffScoreMap.get(staffKey));
				// totalScore=totalScore+(float)(staffScoreMap.get(staffKey)*coefficientA);
			} else if (staffKey.getPost().getPostName().equals(fStaffB)) {
				// totalScore=totalScore+(float)(staffScoreMap.get(staffKey)*coefficientB);
				scoreListB.add(staffScoreMap.get(staffKey));
			}
		}
		float totalA = 0;
		float totalB = 0;
		for (int i = 0; i < scoreListA.size(); i++)
			totalA += scoreListA.get(i);
		for (int i = 0; i < scoreListB.size(); i++)
			totalB += scoreListB.get(i);
		totalA = totalA / scoreListA.size();
		totalB = totalB / scoreListB.size();
		totalScore = coefficientA * totalA + coefficientB * totalB;
		System.out.println(fStaffA + "打分为：" + totalA);
		System.out.println(fStaffB + "打分为：" + totalB);
		System.out.println("员工 " + staff.getStaffName() + " 的个人得分为: "
				+ coefficientA + "*" + totalA + " + " + coefficientB + "*"
				+ totalB + " = " + totalScore);

		DepartmentScore departmentScore = calculateScoreDao
				.getScoreOfDepartmentBelongStaff(staff.getDepartment()
						.getDepartmentId(), year, month);
		System.out.println("所属部门："
				+ departmentScore.getDepartment().getDepartmentName());
		System.out.println("所属部门分数：" + departmentScore.getScore());
		float fuck = totalScore;
		totalScore = totalScore * staffScoreCoefficient
				+ departmentScore.getScore() * depScoreCoefficient;
		System.out.println("员工 " + staff.getStaffName() + " 部门加权得分为: " + fuck
				+ "*" + staffScoreCoefficient + " + "
				+ departmentScore.getScore() + "*" + depScoreCoefficient
				+ " = " + totalScore);
		return totalScore;
	}

	// --------------------------------------------------------------------------------------------------------------------------------部门绩效计算结束

	public List<Float> calculateStaff2(Staff staff,
			Map<Staff, Float> staffScoreMap, int year, int month,
			String fStaffA, String fStaffB, float coefficientA,
			float coefficientB, float staffScoreCoefficient,
			float depScoreCoefficient) throws Exception {
		float totalScore = 0;
		Iterator iterator = staffScoreMap.keySet().iterator();

		List<Float> scoreListA = new ArrayList<Float>();
		List<Float> scoreListB = new ArrayList<Float>();
		while (iterator.hasNext()) {
			Staff staffKey = (Staff) iterator.next();
			System.out.println("打分员工名称：" + staffKey.getStaffName());
			System.out.println("所打分数：" + staffScoreMap.get(staffKey));
			if (staffKey.getPost().getPostName().equals(fStaffA)) {
				scoreListA.add(staffScoreMap.get(staffKey));
				// totalScore=totalScore+(float)(staffScoreMap.get(staffKey)*coefficientA);
			} else if (staffKey.getPost().getPostName().equals(fStaffB)) {
				// totalScore=totalScore+(float)(staffScoreMap.get(staffKey)*coefficientB);
				scoreListB.add(staffScoreMap.get(staffKey));
			}
		}
		float totalA = 0;
		float totalB = 0;
		for (int i = 0; i < scoreListA.size(); i++)
			totalA += scoreListA.get(i);
		for (int i = 0; i < scoreListB.size(); i++)
			totalB += scoreListB.get(i);
		totalA = totalA / scoreListA.size();
		totalB = totalB / scoreListB.size();
		totalScore = coefficientA * totalA + coefficientB * totalB;
		System.out.println(fStaffA + "打分为：" + totalA);
		System.out.println(fStaffB + "打分为：" + totalB);
		System.out.println("员工 " + staff.getStaffName() + " 的个人得分为: "
				+ coefficientA + "*" + totalA + " + " + coefficientB + "*"
				+ totalB + " = " + totalScore);

		DepartmentScore departmentScore = calculateScoreDao
				.getScoreOfDepartmentBelongStaff(staff.getDepartment()
						.getDepartmentId(), year, month);
		System.out.println("所属部门："
				+ departmentScore.getDepartment().getDepartmentName());
		System.out.println("所属部门分数：" + departmentScore.getScore());
		float fuck = totalScore;
		totalScore = totalScore * staffScoreCoefficient
				+ departmentScore.getScore() * depScoreCoefficient;
		System.out.println("员工 " + staff.getStaffName() + " 部门加权得分为: " + fuck
				+ "*" + staffScoreCoefficient + " + "
				+ departmentScore.getScore() + "*" + depScoreCoefficient
				+ " = " + totalScore);
		List<Float> ret = new ArrayList<Float>(3);

		ret.add(0, MathUtil.baoliu(fuck, 2));
		ret.add(1, MathUtil.baoliu(totalScore, 2));
		return ret;
	}

	public boolean calculateStaffScore() {
		try {
			List<Staff> staffList = null;
			StaffManageDao staffManageDao = new StaffManageDao();
			staffList = staffManageDao.getAllStaff();

			/*
			 * //找到市场开发部 Department
			 * departmentMarket=staffManageDao.getMarketDepartment();
			 * //从员工列表中去掉市场开发部的员工（主任，副主任，员工） for(int
			 * i=staffList.size()-1;i>=0;i--){ Staff staff=staffList.get(i);
			 * if(staff
			 * .getDepartment().getDepartmentId()==departmentMarket.getDepartmentId
			 * ()){ staffList.remove(i); } }
			 */

			List<StaffScore> staffScoreList = new ArrayList<StaffScore>();

			List<Staff> postAStaffList = new ArrayList<Staff>(); // 副总经理List
			List<Staff> postBStaffList = new ArrayList<Staff>(); // 市公司部门主任List
			List<Staff> postCStaffList = new ArrayList<Staff>(); // 市公司部门副主任,市公司员工,区营业部科室副科长,区营业部科员工List
			// List<Staff> postDStaffList=new ArrayList<Staff>(); //区营业部经理List
			// List<Staff> postEStaffList=new ArrayList<Staff>(); //区营业部副经理List
			// List<Staff> postFStaffList=new ArrayList<Staff>(); //区营业部科室科长List
			// List<Staff> postGStaffList=new ArrayList<Staff>(); //总经理List

			for (int i = 0; i < staffList.size(); i++) {
				Staff staff = staffList.get(i);
				String postName = staff.getPost().getPostName();
				if (postName.equals("副总经理")) {
					postAStaffList.add(staff);
				} else if (postName.equals("市公司部门主任")) {
					postBStaffList.add(staff);
				} else if (postName.equals("市公司部门副主任")
						|| postName.equals("市公司员工")) {
					postCStaffList.add(staff);
				}
				staffList.remove(staff);
				i--;
			}

			List<StaffScore> postAStaffScoreList = new ArrayList<StaffScore>(); // 副总经理List
			List<StaffScore> postBStaffScoreList = new ArrayList<StaffScore>(); // 市公司部门主任List
			List<StaffScore> postCStaffScoreList = new ArrayList<StaffScore>(); // 市公司部门副主任,市公司员工,区营业部科室副科长,区营业部科员工List
			// List<StaffScore> postDStaffScoreList=new ArrayList<StaffScore>();
			// //区营业部经理List
			// List<StaffScore> postEStaffScoreList=new ArrayList<StaffScore>();
			// //区营业部副经理List
			// List<StaffScore> postFStaffScoreList=new ArrayList<StaffScore>();
			// //区营业部科室科长List
			// List<StaffScore> postGStaffScoreList=new ArrayList<StaffScore>();
			// //总经理List

			postBStaffScoreList = calculateTheScoreOfChief(postBStaffList);
			postAStaffScoreList = calculateTheScoreOfDeputyManager(
					postAStaffList, postBStaffScoreList);
			postCStaffScoreList = calculateTheScoreOfDeputyDirectorOrDepEmpOrDeputyChiefOrClerk(postCStaffList);
			// postDStaffScoreList=calculateTheScoreOfSalesManager(postDStaffList);
			// postEStaffScoreList=calculateTheScoreOfSalesDeputyManager(postEStaffList);
			// postFStaffScoreList=calculateTheScoreOfDirector(postFStaffList);
			// /postGStaffScoreList=calculateLastStaffScore(postGStaffList,postAStaffScoreList);

			staffScoreList.addAll(postAStaffScoreList);
			staffScoreList.addAll(postBStaffScoreList);
			staffScoreList.addAll(postCStaffScoreList);
			// staffScoreList.addAll(postDStaffScoreList);
			// staffScoreList.addAll(postEStaffScoreList);
			// staffScoreList.addAll(postFStaffScoreList);
			// staffScoreList.addAll(postGStaffScoreList);

			calculateScoreDao.insertDataIntoStaffScore(staffScoreList);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------部室主任算分开始
	/**
	 * 计算市公司各部门主任的绩效考评得分
	 * 
	 * @param staffList
	 *            市公司各部门主任列表
	 * @return staffScoreList 市公司各部门主任的得分列表
	 * @throws Exception
	 */
	private List<StaffScore> calculateTheScoreOfChief(List<Staff> staffList)
			throws Exception {
		System.out
				.println("-------------------------------------------------------------------------------------------------");
		System.out.println("部室主任计算分数开始");
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		List<StaffScore> staffScoreList = new ArrayList<StaffScore>();
		float task = new TaskProgressDao().getTaskProgress(year, month)
				.getTaskProgress();
		System.out.println("任务进度：" + task);

		for (int i = 0; i < staffList.size(); i++) {
			Staff staff = staffList.get(i);
			System.out.println("主任名称：" + staff.getStaffName());
			float totalScore = 0;
			// float task=calculateScoreDao.selectTaskSchedule(year, month);
			// task=task*100;

			String explain = "";// 打分说明
			DepartmentScore departmentScore = calculateScoreDao
					.getScoreOfDepartmentBelongStaff(staff.getDepartment()
							.getDepartmentId(), year, month);
			System.out.println("部室名称："
					+ staff.getDepartment().getDepartmentName());
			System.out.println("部室得分：" + departmentScore.getScore());
			if (departmentScore.getInstruction() != null
					&& (!departmentScore.getInstruction().equals(""))) {
				explain = explain + departmentScore.getInstruction();
			}
			System.out.println("部室性质："
					+ departmentScore.getDepartment().getNature());
			if (departmentScore.getDepartment().getNature() == 0) {
				totalScore = task * 0.7f + departmentScore.getScore() * 0.3f;
			} else if (departmentScore.getDepartment().getNature() == 1) {
				totalScore = task * 0.5f + departmentScore.getScore() * 0.5f;
			}
			System.out.println("最后得分：" + totalScore);
			DecimalFormat df = new DecimalFormat("#.00");
			totalScore = Float.parseFloat(df.format(totalScore));
			StaffScore staffScore = new StaffScore();
			staffScore.setStaff(staff);
			staffScore.setTotalScore(totalScore);
			staffScore.setYear(year);
			staffScore.setMonth(month);
			staffScore.setInstruction(explain);
			staffScoreList.add(staffScore);
		}
		System.out.println("部室主任计算分数结束");
		return staffScoreList;
	}

	// --------------------------------------------------------------------------------------------------------------------------------员工算分结束

	// --------------------------------------------------------------------------------------------------------------------------------员工算分开始
	/**
	 * 计算市公司各部门副主任和员工，以及各区营业部的各个科室副科长和科员的绩效考评分数
	 * 
	 * @param staffList
	 *            市公司各部门副主任和员工，以及各区营业部的各个科室副科长和科员的集合列表
	 * @return staffScoreList 员工绩效考评分数集合列表
	 * @throws Exception
	 */
	private List<StaffScore> calculateTheScoreOfDeputyDirectorOrDepEmpOrDeputyChiefOrClerk(
			List<Staff> staffList) throws Exception {
		System.out
				.println("-----------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("员工算分开始");
		try {
			int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
			int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
			// 获取任务进度
			float task = new TaskProgressDao().getTaskProgress(year, month)
					.getTaskProgress();

			List<StaffScore> staffScoreList = new ArrayList<StaffScore>();
			for (int z = 0; z < staffList.size(); z++) {
				Staff staff = staffList.get(z);
				System.out.println("员工名称：" + staff.getStaffName());
				Map<Staff, List<StaffScoreRecord>> staffScoreRecordMap = new HashMap<Staff, List<StaffScoreRecord>>();
				List<StaffScoreRecord> staffScoreRecordList = calculateScoreDao
						.selectAllStaffScoreRecordByStaff(staff, year, month);
				for (int i = 0; i < staffScoreRecordList.size(); i++) {
					StaffScoreRecord staffScoreRecord = staffScoreRecordList
							.get(i);
					Staff fStaff = staffScoreRecord.getScoredFromStaff();
					if (!staffScoreRecordMap.containsKey(fStaff)) {
						List<StaffScoreRecord> staffScoredRecordFromList = new ArrayList<StaffScoreRecord>();
						staffScoredRecordFromList.add(staffScoreRecord);
						staffScoreRecordMap.put(fStaff,
								staffScoredRecordFromList);
					} else {
						staffScoreRecordMap.get(fStaff).add(staffScoreRecord);
					}
				}

				Map<Staff, Float> staffScoreMap = new HashMap<Staff, Float>();

				Iterator iterator = staffScoreRecordMap.keySet().iterator();
				String explain = "";// 打分说明
				while (iterator.hasNext()) {
					Staff staffKey = (Staff) iterator.next();
					float totalScore = 0;
					List<StaffScoreRecord> ssRecordList = staffScoreRecordMap
							.get(staffKey);
					// System.out.println(ssRecordList);
					for (int i = 0; i < ssRecordList.size(); i++) {
						StaffScoreRecord record = ssRecordList.get(i);
						totalScore = totalScore + record.getScore()
								* record.getStaffScoreItem().getScoreWeight();
						System.out.println("打分记录:"
								+ record.getScoredFromStaff().getStaffName()
								+ ": " + record.getScore() + " * "
								+ record.getStaffScoreItem().getScoreWeight()
								+ "   totalScore是:" + totalScore);
					}
					staffScoreMap.put(staffKey, totalScore);
				}

				String postName = staff.getPost().getPostName();
				String departmentName = staff.getDepartment()
						.getDepartmentName();

				float totalScore = 0;
				if (postName.equals("市公司部门副主任")
						&& departmentName.equals("人力资源部")) {
					totalScore = calculateStaff(staff, staffScoreMap, year,
							month, "总经理", "市公司部门主任", 0.3f, 0.7f, 0.5f, 0.5f);
				} else if (postName.equals("市公司部门副主任")) {
					totalScore = calculateStaff(staff, staffScoreMap, year,
							month, "副总经理", "市公司部门主任", 0.3f, 0.7f, 0.5f, 0.5f);
				} else if (postName.equals("市公司员工")) {
					totalScore = calculateStaff(staff, staffScoreMap, year,
							month, "市公司部门主任", "市公司部门副主任", 0.7f, 0.3f, 0.7f,
							0.3f);
				}
				System.out.println("所属部门性质："
						+ staff.getDepartment().getNature());
				if (staff.getDepartment().getNature() == 0) {
					float fuck = totalScore;
					totalScore = task * 0.7f + totalScore * 0.3f;
					System.out.println(staff.getStaffName() + "的最终得分: " + task
							+ "*0.7f+" + fuck + "*0.3f = " + totalScore);
				}

				if (staff.getDepartment().getNature() == 1) {
					float fuck = totalScore;
					totalScore = task * 0.5f + totalScore * 0.5f;
					System.out.println(staff.getStaffName() + "的最终得分: " + task
							+ "*0.5f+" + fuck + "*0.5f = " + totalScore);
				}

				DecimalFormat df = new DecimalFormat("#.00");
				totalScore = Float.parseFloat(df.format(totalScore));
				StaffScore staffScore = new StaffScore();
				staffScore.setStaff(staff);
				staffScore.setTotalScore(totalScore);
				staffScore.setYear(year);
				staffScore.setMonth(month);
				staffScore.setInstruction("");
				staffScoreList.add(staffScore);
				System.out.println("处理后为" + totalScore);
				System.out.println();
			}
			System.out.println("员工算分结束");
			return staffScoreList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<StaffScore> calculateTheScoreOfDeputyManager(
			List<Staff> staffAList, List<StaffScore> staffScoreBList)
			throws Exception {
		System.out
				.println("--------------------------------------------------------------");
		System.out.println("分管副总算分开始：");
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		Map<Staff, List<StaffScore>> staffScoreMap = new HashMap<Staff, List<StaffScore>>();
		for (int i = 0; i < staffAList.size(); i++) {
			Staff staff = staffAList.get(i);
			List<StaffScore> staffScoreBs = new ArrayList<StaffScore>();
			for (int j = staffScoreBList.size() - 1; j >= 0; j--) {
				if (staff.getDepartment().getDepartmentId() == staffScoreBList
						.get(j).getStaff().getDepartment()
						.getHigherDepartmentId()) {
					staffScoreBs.add(staffScoreBList.get(j));
				}
			}
			staffScoreMap.put(staff, staffScoreBs);
		}

		List<StaffScore> staffAScoreList = new ArrayList<StaffScore>();
		Iterator iterator = staffScoreMap.keySet().iterator();
		while (iterator.hasNext()) {
			Staff staffKey = (Staff) iterator.next();

			System.out.println(staffKey.getStaffName());

			System.out.println("     " + "分管部门数量：");

			List<StaffScore> staffScoreBListByStaff = staffScoreMap
					.get(staffKey);

			System.out.println(staffScoreBListByStaff.size());
			System.out.println("     " + "分管部门及得分：");
			float score = 0f;
			String explain = "";
			for (int i = 0; i < staffScoreBListByStaff.size(); i++) {
				System.out.println(staffScoreBListByStaff.get(i).getStaff()
						.getDepartment().getDepartmentName()
						+ "," + staffScoreBListByStaff.get(i).getTotalScore());

				score = score + staffScoreBListByStaff.get(i).getTotalScore();
				explain = explain
						+ staffScoreBListByStaff.get(i).getInstruction() + ";";
			}
			score = score / staffScoreBListByStaff.size();
			DecimalFormat df = new DecimalFormat("#.00");
			score = Float.parseFloat(df.format(score));

			System.out.println("最后得分：" + score);

			StaffScore staffScore = new StaffScore();
			staffScore.setStaff(staffKey);
			staffScore.setTotalScore(score);
			staffScore.setYear(year);
			staffScore.setMonth(month);
			staffScore.setInstruction(explain);
			staffAScoreList.add(staffScore);
		}
		return staffAScoreList;
	}

	

}
