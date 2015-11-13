package com.chinanetwork.performance.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScore;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScore;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.service.DepartmentManageService;
import com.chinanetwork.performance.service.ScoreService;
import com.chinanetwork.performance.service.RateService;
import com.chinanetwork.performance.service.StaffManageService;
import com.chinanetwork.performance.service.StatisticalQueryService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.MathUtil;

/**
 * "统计查询"Action
 * @author Administrator
 *
 */
public class StatisticalQueryAction extends BaseAction {
	//“统计查询”业务逻辑层
	private StatisticalQueryService statisticalQueryService=new StatisticalQueryService();
	private ScoreService getScoreService=new ScoreService();
	private DepartmentManageService departmentManageService = new DepartmentManageService();
	private StaffManageService staffManageService = new StaffManageService();
	private RateService scoreService = new RateService();
//====================================================================以下为“修改部门得分”提供服务==============================================================================
//	//用于获取用户填写的“部门名称”
//	private String departmentName;
//	
//	public String getDepartmentName() {
//		return departmentName;
//	}
//
//	public void setDepartmentName(String departmentName) {
//		this.departmentName = departmentName;
//	}
//
//	//用于在页面显示部门得分列表
//	private List<DepartmentScore> departmentScores;
//	
//	public List<DepartmentScore> getDepartmentScores() {
//		return departmentScores;
//	}
//
//	public void setDepartmentScores(List<DepartmentScore> departmentScores) {
//		this.departmentScores = departmentScores;
//	}
//	private List<StaffScore> staffScores;
//	
//	public List<StaffScore> getStaffScores() {
//		return staffScores;
//	}
//
//	public void setStaffScores(List<StaffScore> staffScores) {
//		this.staffScores = staffScores;
//	}
//
//	//标记变量
//	boolean marked;
//	
//	public boolean isMarked() {
//		return marked;
//	}
//
//	public void setMarked(boolean marked) {
//		this.marked = marked;
//	}
//
//	//向查询页提供查询的部门名称是什么
//	private String chooseDepartmentName;
//	
//	public String getChooseDepartmentName() {
//		return chooseDepartmentName;
//	}
//
//	public void setChooseDepartmentName(String chooseDepartmentName) {
//		this.chooseDepartmentName = chooseDepartmentName;
//	}
//
//	public String errorInfo="";
//	
//	public String getErrorInfo() {
//		return errorInfo;
//	}
//
//	public void setErrorInfo(String errorInfo) {
//		this.errorInfo = errorInfo;
//	}
//
//	/**
//	 * 到达“部门修改页面”
//	 * @return
//	 */
//	public String toListDepartmentScore(){
//		try{
//			int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
//			int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
//			departmentScores=statisticalQueryService.getDepartmentScoreList(year, month);
//			return "toListDepartmentScore";
//		}catch(Exception e){
//			e.printStackTrace();
//			return "error";
//		}	
//	}
//	
//	private boolean scoreMarker=false;	 	//用来标记部门绩效是否已经有结果		fale :没有计算结果		true：有计算结果
//	public boolean isScoreMarker() {
//		return scoreMarker;
//	}
//	public void setScoreMarker(boolean scoreMarker) {
//		this.scoreMarker = scoreMarker;
//	}
//	private List<String> messageList;			//用来显示检测信息
//	public List<String> getMessageList() {
//		return messageList;
//	}
//	public void setMessageList(List<String> messageList) {
//		this.messageList = messageList;
//	}
///*
//	public String toTestDepartmentScorePage(){
//		messageList=new ArrayList<String>();
//		messageList.add("操作步骤：                                                                                                 ");
//		messageList.add("1、消息框上两个按钮，可点击的部门得分按钮，不可点击的修改部门绩效按钮；");
//		messageList.add("2、点击检测部门得分按钮，以检测部门绩效是否已经计算出来；");
//		messageList.add("3、如果部门得分已经计算出来了，会在消息框中有提示，并且修改部门得分按钮变为可以点击");
//		messageList.add("4、点击修改部门得分按钮，以进入部门得分修改页面，修改部门得分");
//		messageList.add("5、如果部门部门得分还没有计算出来，请先计算部门得分。");
//		return "toTestDepartmentScorePage";
//	}
//	*/
//	
//	/*
//	public String confirmFinishCalculateDepartmentScore(){
//		messageList=new ArrayList<String>();
//		try {
//			boolean finish=statisticalQueryService.confirmOnlyCalculateDepartmentScore();
//			if(finish){
//				scoreMarker=false;
//				messageList.add("部门得分还没有被计算出来，还不能修改部门绩效... ...");
//			}else{
//				scoreMarker=true;
//				messageList.add("部门得分已经被计算出来了，可以进行部门绩效修改... ...");
//			}
//			return "toTestDepartmentScorePage";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//	}
//	
//	*/
//	/**
//	 * 根据用户填写的部门名称，获取相应的部门得分列表，并显示在页面上
//	 */
//	/*public String showDepartmentScoreList(){
//		try {
//			departmentScores=statisticalQueryService.getDepartmentScoreList(departmentName);
//			chooseDepartmentName=departmentName;
//			if(departmentScores!=null&&departmentScores.size()!=0){
//				marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//			}else{
//				marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//			}
//			return "toListDepartmentScore";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//	}*/
//	
//	/*
//	public String toModifyDepartmentScore(){
//		try {
//			departmentScores=statisticalQueryService.getDepartmentScoreList(departmentName);
//			chooseDepartmentName=departmentName;
//			if(departmentScores!=null&&departmentScores.size()!=0){
//				marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//			}else{
//				marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//			}
//			return "toModifyDepartmentScore";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//	}
//	
//	*/
//	
//	private String departmentIdStr;
//	private String scoreStr;
//	private String instruction;
//	
//	public String getDepartmentIdStr() {
//		return departmentIdStr;
//	}
//
//	public void setDepartmentIdStr(String departmentIdStr) {
//		this.departmentIdStr = departmentIdStr;
//	}
//
//	public String getScoreStr() {
//		return scoreStr;
//	}
//
//	public void setScoreStr(String scoreStr) {
//		this.scoreStr = scoreStr;
//	}
//
//	public String getInstruction() {
//		return instruction;
//	}
//
//	public void setInstruction(String instruction) {
//		this.instruction = instruction;
//	}
//
//	private List<Integer> depIdList;
//	private List<Float> depScoreList;
//	private List<String> infoList;
//	
//	public List<Integer> getDepIdList() {
//		return depIdList;
//	}
//
//	public void setDepIdList(List<Integer> depIdList) {
//		this.depIdList = depIdList;
//	}
//
//	public List<Float> getDepScoreList() {
//		return depScoreList;
//	}
//
//	public void setDepScoreList(List<Float> depScoreList) {
//		this.depScoreList = depScoreList;
//	}
//
//	public List<String> getInfoList() {
//		return infoList;
//	}
//
//	public void setInfoList(List<String> infoList) {
//		this.infoList = infoList;
//	}
//
//	
//
////=================================================================以下为“员工分数修改“提供服务=========================================================
//	
//	/*
//	public String toTestStaffScorePage(){
//		messageList=new ArrayList<String>();
//		messageList.add("操作步骤：                                                                                                 ");
//		messageList.add("1、消息框上两个按钮，可点击的员工得分按钮，不可点击的修改员工绩效按钮；");
//		messageList.add("2、点击检测员工得分按钮，以检测员工绩效是否已经计算出来；");
//		messageList.add("3、如果员工得分已经计算出来了，会在消息框中有提示，并且修改员工得分按钮变为可以点击");
//		messageList.add("4、点击修改员工得分按钮，以进入员工得分修改页面，修改员工得分");
//		messageList.add("5、如果员工得分还没有计算出来，请先计算员工得分。");
//		return "toTestStaffScorePage";
//	}
//	
//	*/
//	
//	/*
//	public String confirmFinishCalculateStaffScore(){
//		messageList=new ArrayList<String>();
//		try {
//			boolean finish=statisticalQueryService.confirmOnlyCalculateStaffScore();
//			if(finish){
//				scoreMarker=false;
//				messageList.add("员工得分还没有被计算出来，还不能修改员工绩效... ...");
//			}else{
//				scoreMarker=true;
//				messageList.add("员工得分已经被计算出来，可以进行员工绩效修改... ...");
//			}
//			return "toTestStaffScorePage";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//	}
//	
//	*/
//	public String toListStaffScore(){
//		try{
//			//return showStaffList();
//			
//			int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
//			int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
//			staffScoreList=statisticalQueryService.getStaffScoreList(year, month);
//			return "toListStaffScore";
//		}catch(Exception e){
//			e.printStackTrace();
//			return "error";
//		}
//	}
//	
//
//	
//	private int staffId;
//	
//	public int getStaffId() {
//		return staffId;
//	}
//
//	public void setStaffId(int staffId) {
//		this.staffId = staffId;
//	}
//	
//	private int chooseStaffId;
//
//	public int getChooseStaffId() {
//		return chooseStaffId;
//	}
//
//	public void setChooseStaffId(int chooseStaffId) {
//		this.chooseStaffId = chooseStaffId;
//	}
//
//	private List<StaffScore> staffScoreList;
//
//	public List<StaffScore> getStaffScoreList() {
//		return staffScoreList;
//	}
//
//	public void setStaffScoreList(List<StaffScore> staffScoreList) {
//		this.staffScoreList = staffScoreList;
//	}
//
//
//	private String staffScoreStr;
//
//	public String getStaffScoreStr() {
//		return staffScoreStr;
//	}
//
//	public void setStaffScoreStr(String staffScoreStr) {
//		this.staffScoreStr = staffScoreStr;
//	}
//
//	public String showStaffList(){
//		try {
//			staffScoreList=statisticalQueryService.getStaffList(staffId);
//			chooseStaffId=staffId;
//			if(staffScoreList!=null&&staffScoreList.size()!=0){
//				marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//			}else{
//				marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//			}
//			return  "toListStaffScore";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//	}
//	
//	public String toModifyStaffScore(){
//		try {
//			staffScoreList=statisticalQueryService.getStaffList(staffId);
//			chooseStaffId=staffId;
//			if(staffScoreList!=null&&staffScoreList.size()!=0){
//				marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//			}else{
//				marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//			}
//			return "toModifyStaffScore";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//	}
//	
//	public String modifyStaffScore(){
//		try {
//			System.out.println(staffId);
//			String regex="^[0-9]+\\.{0,1}[0-9]*$";
//			if(staffScoreStr.equals("")||staffScoreStr==null){
//				errorInfo="分数不能为空";
//				staffScoreList=statisticalQueryService.getStaffList(staffId);
//				chooseStaffId=staffId;
//				if(staffScoreList!=null&&staffScoreList.size()!=0){
//					marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//				}else{
//					marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//				}
//				return "toModifyStaffScore";
//			}else if(!staffScoreStr.matches(regex)){
//				errorInfo="分数应该是数字";
//				staffScoreList=statisticalQueryService.getStaffList(staffId);
//				chooseStaffId=staffId;
//				if(staffScoreList!=null&&staffScoreList.size()!=0){
//					marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//				}else{
//					marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//				}
//				return "toModifyStaffScore";
//			}else if(Float.parseFloat(staffScoreStr)>100){
//				errorInfo="分数超出范围";
//				staffScoreList=statisticalQueryService.getStaffList(staffId);
//				chooseStaffId=staffId;
//				if(staffScoreList!=null&&staffScoreList.size()!=0){
//					marked=true;	//说明根据用户填写的部门名称找到了相应的记录
//				}else{
//					marked=false;	//说明根据用户填写的部门名称没有找到相应的记录
//				}
//				return "toModifyStaffScore";
//			}
//			//statisticalQueryService.modifyStaffScore(staffId, staffScoreStr, instruction);
//			return "toListStaffScore";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "error";
//		}
//		
//	}


//	public String exportStaffScoreExcel(){
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		String format=simpleDateFormat.format(new Date(System.currentTimeMillis()));
//		System.out.println(format);
//		WritableWorkbook workbook;
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		
//		try{
//			int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
//			int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
//			List<StaffScore> staffScoreList=statisticalQueryService.getStaffScoreList(year, month);
//			
//			workbook = Workbook.createWorkbook(os);
//			WritableSheet sheet = workbook.createSheet("员工绩效考评", 0);
//			
//			sheet.addCell(new jxl.write.Label(0, 0, "工号"));
//			sheet.addCell(new jxl.write.Label(1, 0, "名字"));
//			sheet.addCell(new jxl.write.Label(2, 0, "性别"));
//			sheet.addCell(new jxl.write.Label(3, 0, "部门"));
//			sheet.addCell(new jxl.write.Label(4, 0, "绩效"));
//			sheet.addCell(new jxl.write.Label(5, 0, "工资"));
//			sheet.addCell(new jxl.write.Label(6, 0, "日期"));
//			sheet.addCell(new jxl.write.Label(7, 0, "描述"));
//			
//			for (StaffScore staffScore : staffScoreList) {
//				int i = staffScoreList.indexOf(staffScore);
//				i=i+1;
//				sheet.addCell(new jxl.write.Label(0, i, String.valueOf(staffScore.getStaff().getStaffId())));
//				sheet.addCell(new jxl.write.Label(1, i, staffScore.getStaff().getStaffName()));
//				sheet.addCell(new jxl.write.Label(2, i, staffScore.getStaff().getStaffSex()));
//				sheet.addCell(new jxl.write.Label(3, i, staffScore.getStaff().getDepartment().getDepartmentName()));
//				sheet.addCell(new jxl.write.Label(4, i, String.valueOf(staffScore.getTotalScore())));
//				sheet.addCell(new jxl.write.Label(5, i, format));
//				sheet.addCell(new jxl.write.Label(6,i,staffScore.getInstruction()));
//			}
//			workbook.write();
//			workbook.close();
//			is = new ByteArrayInputStream(os.toByteArray());
//			return "excel";
//		}catch(Exception e){
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
	public String goBack(){
		return "goBackWelcome";
	}
	
//=======================================“导出员工绩效”对应的Action============================================================


	
//	
//	public String exportNewStaffScoreExcel() throws Exception{
//			//判断“员工绩效”是否已经计算过
//			//if(!statisticalQueryService.confirmOnlyCalculateStaffScore()){
//				//判断“部门绩效”是否已经被修改
//				//String value=ConfigurationDataUtil.getStringValue("department_modify");
//				//fuck
//				/*
//				if(value.equals("true")){
//					getScoreService.calculateStaffScoreUpdate();
//					//将部门修改状态变为“false”====>即改变为“没有被修改”状态
//					ConfigurationDataUtil.updateValue("department_modify", "false");
//				}
//				*/
//				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//				String format=simpleDateFormat.format(new Date(System.currentTimeMillis()));
//				System.out.println(format);
//				WritableWorkbook workbook;
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				
//				int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
//				int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
//				List<StaffScore> staffScoreList=statisticalQueryService.getStaffScoreList(year, month);
//				
//				workbook = Workbook.createWorkbook(os);
//				WritableSheet sheet = workbook.createSheet("员工绩效考评", 0);
//				
//				sheet.addCell(new jxl.write.Label(0, 0, "工号"));
//				sheet.addCell(new jxl.write.Label(1, 0, "名字"));
//				sheet.addCell(new jxl.write.Label(2, 0, "性别"));
//				sheet.addCell(new jxl.write.Label(3, 0, "部门"));
//				sheet.addCell(new jxl.write.Label(4, 0, "得分"));
//				sheet.addCell(new jxl.write.Label(5, 0, "绩效"));
//				sheet.addCell(new jxl.write.Label(6, 0, "日期"));
//				sheet.addCell(new jxl.write.Label(7, 0, "描述"));
//				
//				for (StaffScore staffScore : staffScoreList) {
//					int i = staffScoreList.indexOf(staffScore);
//					i=i+1;
//					sheet.addCell(new jxl.write.Label(0, i, String.valueOf(staffScore.getStaff().getStaffId())));
//					sheet.addCell(new jxl.write.Label(1, i, staffScore.getStaff().getStaffName()));
//					sheet.addCell(new jxl.write.Label(2, i, staffScore.getStaff().getStaffSex()));
//					sheet.addCell(new jxl.write.Label(3, i, staffScore.getStaff().getDepartment().getDepartmentName()));
//					sheet.addCell(new jxl.write.Label(4, i, String.valueOf(staffScore.getTotalScore())));
//					float salary=statisticalQueryService.getSalaryByStaff(staffScore.getStaff());
//					float score=staffScore.getTotalScore();
//					sheet.addCell(new jxl.write.Label(5,i,String.valueOf(salary*(score/100))));
//					sheet.addCell(new jxl.write.Label(6, i, format));
//					sheet.addCell(new jxl.write.Label(7,i,staffScore.getInstruction()));
//				}
//				workbook.write();
//				workbook.close();
//				is = new ByteArrayInputStream(os.toByteArray());
//				return "excel";
//			//}else{
//			//	return "toStaffInfo";
//	
//	}
//	
	
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * 查询统计
 * 	
 */
	private List<Department> departments;
	private int departmentIdChosen;
	private List<Integer> years;
	private List<Integer> months;
	private int startyearChosen;
	private int startmonthChosen;
	//private int endyearChosen;
	//private int endmonthChosen;
	
	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public int getDepartmentIdChosen() {
		return departmentIdChosen;
	}

	public void setDepartmentIdChosen(int departmentIdChosen) {
		this.departmentIdChosen = departmentIdChosen;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<Integer> getMonths() {
		return months;
	}

	public void setMonths(List<Integer> months) {
		this.months = months;
	}

	public int getStartyearChosen() {
		return startyearChosen;
	}

	public void setStartyearChosen(int startyearChosen) {
		this.startyearChosen = startyearChosen;
	}

	public int getStartmonthChosen() {
		return startmonthChosen;
	}

	public void setStartmonthChosen(int startmonthChosen) {
		this.startmonthChosen = startmonthChosen;
	}

	private List<DepartmentScore> departmentScores;
	
	public List<DepartmentScore> getDepartmentScores() {
		return departmentScores;
	}

	public void setDepartmentScores(List<DepartmentScore> departmentScores) {
		this.departmentScores = departmentScores;
	}
	

	/*
	 * departments
	 * departmentIdChosen
	 * years
	 * months
	 * startyearChosen
	 * startmonthChosen
	 * endyearChosen
	 * endmonthChosen
	 * departmentScores
	 * 
	 */
//	public void setEndmonthChosen(int endmonthChosen) {
//		this.endmonthChosen = endmonthChosen;
//	}

	public String toDepartmentScoreQuery() throws Exception
	{
		Staff staff = (Staff) this.getSession().get("staff");
		departments = statisticalQueryService.getQueryDepartmentList(staff);
		
		years = statisticalQueryService.departmentScoredYears();
		months = statisticalQueryService.departmentScoredMonths();
		if(years == null)
			years = new ArrayList<Integer>();
		if(months == null)
			months = new ArrayList<Integer>();
		
		return "toDepartmentScoreQuery";
	}
	public String departmentScoreQuery() throws Exception
	{
		departmentScores = statisticalQueryService.getDepartmentScoreList(departmentIdChosen, 
				startyearChosen, startmonthChosen);
		return toDepartmentScoreQuery();
	}
	//------------------------------------------------------------------------------------------------
	
	private List<Staff> staffs;
	private int staffIdChosen;
	
	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	public int getStaffIdChosen() {
		return staffIdChosen;
	}

	/*
	 * staffs
	 * staffIdChosen
	 * years
	 * months
	 * startyearChosen
	 * startmonthChosen
	 * endyearChosen
	 * endmonthChosen
	 * staffScores
	 * 
	 */
	public void setStaffIdChosen(int staffIdChosen) {
		this.staffIdChosen = staffIdChosen;
	}

	public String toStaffScoreQuery() throws Exception
	{
		Staff staff = (Staff) this.getSession().get("staff");
		staffs = statisticalQueryService.getQueryStaffList(staff);
		//
		years = statisticalQueryService.staffScoredYears();
		months = statisticalQueryService.staffScoredMonths();
		if(years == null)
			years = new ArrayList<Integer>();
		if(months == null)
			months = new ArrayList<Integer>();
		return "toStaffScoreQuery";
	}
	
	
	private List<Float> staffScoresDepartmentScores;
	private List<Float> staffScoresOriginScores;
	private List<Float> staffScoresWithDepartmentScores;
	private List<Float> staffScoresWithTask;
	
	
	public List<Float> getStaffScoresWithTask() {
		return staffScoresWithTask;
	}

	public void setStaffScoresWithTask(List<Float> staffScoresWithTask) {
		this.staffScoresWithTask = staffScoresWithTask;
	}

	public List<Float> getStaffScoresDepartmentScores() {
		return staffScoresDepartmentScores;
	}

	public void setStaffScoresDepartmentScores(
			List<Float> staffScoresDepartmentScores) {
		this.staffScoresDepartmentScores = staffScoresDepartmentScores;
	}

	public List<Float> getStaffScoresOriginScores() {
		return staffScoresOriginScores;
	}

	public void setStaffScoresOriginScores(List<Float> staffScoresOriginScores) {
		this.staffScoresOriginScores = staffScoresOriginScores;
	}

	public List<Float> getStaffScoresWithDepartmentScores() {
		return staffScoresWithDepartmentScores;
	}

	public void setStaffScoresWithDepartmentScores(
			List<Float> staffScoresWithDepartmentScores) {
		this.staffScoresWithDepartmentScores = staffScoresWithDepartmentScores;
	}
	
	private List<StaffScore> staffScores;
	
	public List<StaffScore> getStaffScores() {
		return staffScores;
	}

	public void setStaffScores(List<StaffScore> staffScores) {
		this.staffScores = staffScores;
	}
	

	public String staffScoreQuery() throws Exception
	{
		staffScores = statisticalQueryService.getStaffScoreList(staffIdChosen,startyearChosen, startmonthChosen);
		staffScoresDepartmentScores = new ArrayList<Float>();
		staffScoresOriginScores = new ArrayList<Float>();
		staffScoresWithDepartmentScores = new ArrayList<Float>();
		staffScoresWithTask = new ArrayList<Float>();
		//int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		//int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		for(int i=0; i<staffScores.size(); i++)
		{
			StaffScore staffScore = staffScores.get(i);
			float depScore = statisticalQueryService.getDepartmentScore(
					staffScore.getStaff().getDepartment().getDepartmentId(), staffScore.getYear(), staffScore.getMonth());
			
			List<Float> scores = statisticalQueryService.getOriginScore(staffScore.getStaff(), staffScore.getYear(), staffScore.getMonth());
			
			staffScoresDepartmentScores.add(depScore);
			staffScoresOriginScores.add(scores.get(0));
			staffScoresWithDepartmentScores.add(scores.get(1));
			staffScoresWithTask.add(scores.get(2));
			//staffScoresWithDepartmentScores.add(0);
			
		}
		System.out.println("hehe");
		return toStaffScoreQuery();
	}
//------------------------------------------------------------------------------------------------
	
	private List<DepartmentScoreRecord> departmentScoreRecords;
	public List<DepartmentScoreRecord> getDepartmentScoreRecords() {
		return departmentScoreRecords;
	}
	public void setDepartmentScoreRecords(
			List<DepartmentScoreRecord> departmentScoreRecords) {
		this.departmentScoreRecords = departmentScoreRecords;
	}
	
	/*
	 * departments
	 * years
	 * months
	 * startyearChosen
	 * startmonthChosen
	 * departmentIdChosen
	 * departmentScoreRecords
	 */

	public String toDepartmentRecordQuery() throws Exception
	{
		departments = departmentManageService.getAllDepartments();
		years = statisticalQueryService.departmentScoredYears();
		months = statisticalQueryService.departmentScoredMonths();
		if(years == null)
			years = new ArrayList<Integer>();
		if(months == null)
			months = new ArrayList<Integer>();
		return "toDepartmentRecordQuery";
	}
	public String departmentRecordQuery() throws Exception
	{
		Department departmentTo = departmentManageService.getDepartment(departmentIdChosen);
		departmentScoreRecords = scoreService.getDepartmentScoreRecord(departmentTo, null, startyearChosen, startmonthChosen);
		return toDepartmentRecordQuery();
	}
	//------------------------------------------------------------------------------------------------
	
	private List<StaffScoreRecord> staffScoreRecords;
	public List<StaffScoreRecord> getStaffScoreRecords() {
		return staffScoreRecords;
	}
	public void setStaffScoreRecords(List<StaffScoreRecord> staffScoreRecords) {
		this.staffScoreRecords = staffScoreRecords;
	}
	/*
	 * staffs
	 * years
	 * months
	 * startyearChosen
	 * startmonthChosen
	 * staffIdChosen
	 * staffScoreRecords
	 */
	public String toStaffRecordQuery() throws Exception
	{
		staffs = staffManageService.getScoredStaffs();
		years = statisticalQueryService.staffScoredYears();
		months = statisticalQueryService.staffScoredMonths();
		if(years == null)
			years = new ArrayList<Integer>();
		if(months == null)
			months = new ArrayList<Integer>();
		return "toStaffRecordQuery";
	}
	public String staffRecordQuery() throws Exception
	{
		Staff staffTo = staffManageService.getStaff(staffIdChosen);
		staffScoreRecords = scoreService.getStaffScoredRecord(staffTo, null, startyearChosen, startmonthChosen);
		for (StaffScoreRecord record : staffScoreRecords)
		{
			StaffScoreItem item = record.getStaffScoreItem();
			item.setScoreWeight(Math.round(item.getScoreWeight()*100));
		}
		return toStaffRecordQuery();
	}
	//------------------------------------------------------------------------------------------------
	
	
	//导出到excel
	private List<String> messageList;			//用来显示检测信息
	public List<String> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}
	private boolean scoreMarker=false;	 	//用来标记部门绩效是否已经有结果		fale :没有计算结果		true：有计算结果
	public boolean isScoreMarker() {
		return scoreMarker;
	}
	public void setScoreMarker(boolean scoreMarker) {
		this.scoreMarker = scoreMarker;
	}
	
	
	private InputStream is;
	
	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}
	
	
	public String toTestExportExcel(){
		messageList=new ArrayList<String>();
		messageList.add("操作步骤：");
		messageList.add("1、消息框上两个按钮，可点击的检测员工绩效按钮，不可点击的导出员工绩效按钮；");
		messageList.add("2、点击检测员工绩效按钮，以检测员工绩效是否已经计算出来；");
		messageList.add("3、如果员工得分已经计算出来了，会在消息框中有提示，并且导出员工绩效按钮变为可以点击");
		messageList.add("4、点击导出员工绩效按钮，导出员工绩效的Excel表格");
		messageList.add("5、如果员工得分还没有计算出来，请先计算员工得分。");
		return "toTestExportExcel";
	}
	
	public String confirmAccomplishStaffScore() throws Exception{
		try {
			messageList=new ArrayList<String>();
			if(getScoreService.confirmOnlyCalculateStaffScore()){
				messageList=new ArrayList<String>();
				messageList.add("员工绩效还没有被计算出来");
			}
			//如果评分过程没有结束
			if(messageList.size()!=0){
				//将评分标记变成false；
				scoreMarker=false;
			}
			//评分过程结束
			else{
				//将评分标记变成true
				ConfigurationDataUtil.updateValue("start_score", "false");
			//	ConfigurationDataUtil.updateValue("which_month", "00");
			//	ConfigurationDataUtil.updateValue("which_year", "0000");
				scoreMarker=true;
			}
			return "toTestExportExcel";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
	/**
	 * 员工计算分数结束后，导出Excel表格
	 * @return
	 */
	public String exportScoreExcel(){
		//获取当前时间
		//SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		//String format=simpleDateFormat.format(new Date(System.currentTimeMillis()));
		//System.out.println(format);
		StaffManageService staffManageService = new StaffManageService();
		WritableWorkbook workbook;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try{
			//List<StaffScoreExcel> staffScoreExcelList=getScoreService.getStaffScoreList();
			
			int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
			int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
			
			StatisticalQueryService statisticalQueryService=new StatisticalQueryService();
			List<StaffScore> staffScores = statisticalQueryService.getStaffScoreList(-1,year, month);
			List<Float> staffScoresDepartmentScores = new ArrayList<Float>();
			List<Float> staffScoresOriginScores = new ArrayList<Float>();
			List<Float> staffScoresWithDepartmentScores = new ArrayList<Float>();
			List<Float> staffScoresWithTask = new ArrayList<Float>();
	
			for(int i=0; i<staffScores.size(); i++)
			{
				StaffScore staffScore = staffScores.get(i);
				float depScore = statisticalQueryService.getDepartmentScore(
						staffScore.getStaff().getDepartment().getDepartmentId(), staffScore.getYear(), staffScore.getMonth());
				List<Float> scores = statisticalQueryService.getOriginScore(staffScore.getStaff(), staffScore.getYear(), staffScore.getMonth());
				staffScoresDepartmentScores.add(depScore);
				staffScoresOriginScores.add(scores.get(0));
				staffScoresWithDepartmentScores.add(scores.get(1));
				staffScoresWithTask.add(scores.get(2));
			}
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("员工绩效考评", 0);
			
			sheet.addCell(new jxl.write.Label(0, 0, "序号"));
			sheet.addCell(new jxl.write.Label(1, 0, "姓名"));
			sheet.addCell(new jxl.write.Label(2, 0, "部室"));
			sheet.addCell(new jxl.write.Label(3, 0, "职务"));
			sheet.addCell(new jxl.write.Label(4, 0, "部室得分"));
			sheet.addCell(new jxl.write.Label(5, 0, "个人得分"));
			sheet.addCell(new jxl.write.Label(6, 0, "部室加成"));
			sheet.addCell(new jxl.write.Label(7, 0, "运营加成"));
			sheet.addCell(new jxl.write.Label(8, 0, "绩效档次"));
			sheet.addCell(new jxl.write.Label(9, 0, "比例"));
			sheet.addCell(new jxl.write.Label(10, 0, "绩效工资"));
			//sheet.addCell(new jxl.write.Label(10, 0, "日期"));
			
			for(int i=0;i<staffScores.size();i++){
				//StaffScoreExcel staffScoreExcel=staffScoreExcelList.get(i);
				//System.out.println(staffScoreExcel.getStaff().getStaffId()+","+staffScoreExcel.getStaff().getStaffName()+","+staffScoreExcel.getStaff().getStaffSex()+","+
				//		staffScoreExcel.getStaff().getDepartment().getDepartmentName()+","+staffScoreExcel.getStaffScore()+","+staffScoreExcel.getInstruction());
				StaffScore staffScore = staffScores.get(i);
				sheet.addCell(new jxl.write.Label(0, i+1, String.valueOf(i+1)));
				sheet.addCell(new jxl.write.Label(1, i+1, staffScore.getStaff().getStaffName()));
				sheet.addCell(new jxl.write.Label(2, i+1, staffScore.getStaff().getDepartment().getDepartmentName()));
				sheet.addCell(new jxl.write.Label(3, i+1, staffScore.getStaff().getPost().getPostName()));
				sheet.addCell(new jxl.write.Label(4, i+1, String.valueOf(staffScoresDepartmentScores.get(i))));
				sheet.addCell(new jxl.write.Label(5, i+1, String.valueOf(staffScoresOriginScores.get(i))));
				sheet.addCell(new jxl.write.Label(6, i+1, String.valueOf(staffScoresWithDepartmentScores.get(i))));
				sheet.addCell(new jxl.write.Label(7, i+1, String.valueOf(staffScoresWithTask.get(i))));
				float salary = staffManageService.getSalaryByStaffId(staffScore.getStaff().getStaffId());
				sheet.addCell(new jxl.write.Label(8, i+1, String.valueOf(salary)));
				sheet.addCell(new jxl.write.Label(9,i+1,String.valueOf(staffScoresWithTask.get(i))+"%"));
				float realsalary = salary*staffScoresWithTask.get(i)/100;
				
				sheet.addCell(new jxl.write.Label(10,i+1,String.valueOf(MathUtil.baoliu(realsalary, 2))));
				//sheet.addCell(new jxl.write.Label(10, i+1, format));
				
			}
			
			
			workbook.write();
			workbook.close();
			is = new ByteArrayInputStream(os.toByteArray());
			return "excel";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
}
