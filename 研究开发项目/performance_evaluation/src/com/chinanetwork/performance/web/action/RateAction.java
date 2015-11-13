package com.chinanetwork.performance.web.action;

import java.util.ArrayList;
import java.util.List;
import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.service.DepartmentScoreItemManageService;
import com.chinanetwork.performance.service.RateService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
/**
 * 打分action
 * 
 * @author 刘刚
 *
 */
public class RateAction extends BaseAction {

	public List<String> getDepartmentScoreComments() {
		return departmentScoreComments;
	}

	public void setDepartmentScoreComments(List<String> departmentScoreComments) {
		this.departmentScoreComments = departmentScoreComments;
	}

	private RateService scoreService = new RateService();

	public List<Department> getDepartmentsNoScored() {
		return departmentsNoScored;
	}

	public void setDepartmentsNoScored(List<Department> departmentsNoScored) {
		this.departmentsNoScored = departmentsNoScored;
	}

	public List<DepartmentScoreItem> getDepartmentNoScoredItems() {
		return departmentNoScoredItems;
	}

	public void setDepartmentNoScoredItems(
			List<DepartmentScoreItem> departmentNoScoredItems) {
		this.departmentNoScoredItems = departmentNoScoredItems;
	}

	public List<String> getDepartmentScores() {
		return departmentScores;
	}

	public void setDepartmentScores(List<String> departmentScores) {
		this.departmentScores = departmentScores;
	}

	public List<Department> getDepartmentsScored() {
		return departmentsScored;
	}

	public void setDepartmentsScored(List<Department> departmentsScored) {
		this.departmentsScored = departmentsScored;
	}

	public Department getDepartmentScored() {
		return departmentScored;
	}

	public void setDepartmentScored(Department departmentScored) {
		this.departmentScored = departmentScored;
	}

	public List<DepartmentScoreRecord> getDepartmentScoredRecords() {
		return departmentScoredRecords;
	}

	public void setDepartmentScoredRecords(
			List<DepartmentScoreRecord> departmentScoredRecords) {
		this.departmentScoredRecords = departmentScoredRecords;
	}

	public List<Staff> getStaffsNoScored() {
		return staffsNoScored;
	}

	public void setStaffsNoScored(List<Staff> staffsNoScored) {
		this.staffsNoScored = staffsNoScored;
	}

	public List<StaffScoreItem> getStaffNoScoredItems() {
		return staffNoScoredItems;
	}

	public void setStaffNoScoredItems(List<StaffScoreItem> staffNoScoredItems) {
		this.staffNoScoredItems = staffNoScoredItems;
	}

	public List<String> getStaffScores() {
		return staffScores;
	}

	public void setStaffScores(List<String> staffScores) {
		this.staffScores = staffScores;
	}

	public List<Staff> getStaffsScored() {
		return staffsScored;
	}

	public void setStaffsScored(List<Staff> staffsScored) {
		this.staffsScored = staffsScored;
	}

	public Staff getStaffScored() {
		return staffScored;
	}

	public void setStaffScored(Staff staffScored) {
		this.staffScored = staffScored;
	}

	public List<StaffScoreRecord> getStaffScoredrecords() {
		return staffScoredrecords;
	}

	public void setStaffScoredrecords(List<StaffScoreRecord> staffScoredrecords) {
		this.staffScoredrecords = staffScoredrecords;
	}

	public int getDepartmentIdChosen() {
		return departmentIdChosen;
	}

	public void setDepartmentIdChosen(int departmentIdChosen) {
		this.departmentIdChosen = departmentIdChosen;
	}

	public int getStaffIdChosen() {
		return staffIdChosen;
	}

	public void setStaffIdChosen(int staffIdChosen) {
		this.staffIdChosen = staffIdChosen;
	}

	// 网页交互变量



	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// listDepartmentNoScore
	private List<Department> departmentsNoScored; // 下拉列表数据 部门
	private int departmentIdChosen; // 选中的部门
	private List<DepartmentScoreItem> departmentNoScoredItems;
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// departmentScore
	private List<String> departmentScores;
	private List<String> departmentScoreComments;
	private List<String> itemsIds;
	private List<Integer> recordId;

	public List<Integer> getRecordId() {
		return recordId;
	}

	public void setRecordId(List<Integer> recordId) {
		this.recordId = recordId;
	}

	// listDepartmentScoreItem
	private List<Department> departmentsScored;
	private Department departmentScored;
	private List<DepartmentScoreRecord> departmentScoredRecords;

	public String listDepartmentNoScoredItem() throws Exception {
		Staff staff = (Staff) this.getSession().get("staff");
		departmentsNoScored = scoreService.getNoScoreDepartment(staff
				.getDepartment());// 下拉列表

		if (departmentsNoScored == null) {
			departmentsNoScored = new ArrayList<Department>();
		} 
		else {
			for (Department department : departmentsNoScored) {
				if (departmentIdChosen == department.getDepartmentId()) {
					departmentNoScoredItems = scoreService
							.getDepartmentScoreItem(department, staff
									.getDepartment());
					return "listDepartmentNoScoredItem";
				}
				departmentNoScoredItems = scoreService.getDepartmentScoreItem(
						departmentsNoScored.get(0), staff.getDepartment());
			}
		}

		return "listDepartmentNoScoredItem";
	}

	public String departmentScore() throws Exception {
		if (itemsIds == null || itemsIds.size() == 0)
			return listDepartmentNoScoredItem();
		
		DepartmentScoreItemManageService departmentScoreItemManageService = new DepartmentScoreItemManageService();
		for (int i = 0; i<departmentScores.size(); i++)
		{
			String score =departmentScores.get(i);
			if (score.equals("*"))
			{
				int itemId = Integer.parseInt(itemsIds.get(i));
				DepartmentScoreItem item = departmentScoreItemManageService.getDepartmentScoreItem(itemId);
				departmentScores.set(i, String.valueOf(item.getScoreValue()));
			}
		}

		Staff staff = (Staff) this.getSession().get("staff");
		scoreService.departmentScoreByItemId(itemsIds, departmentScores, departmentScoreComments, staff
				.getDepartment().getDepartmentId(), departmentIdChosen, staff.getStaffId());
		return "departmentScore";
	}

	public String listDepartmentScoredItem() throws Exception {
		Staff staff = (Staff) this.getSession().get("staff");
		departmentsScored = scoreService.getScoreDepartment(staff
				.getDepartment());// 下拉列表
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");

		if (departmentsScored == null) {
			departmentsScored = new ArrayList<Department>();
		} 
		else {
			for (Department department : departmentsScored) {
				if (departmentIdChosen == department.getDepartmentId()) {
					departmentScoredRecords = scoreService
							.getDepartmentScoreRecord(department, staff
									.getDepartment(), year, month, 1, 2 );
					return "listDepartmentScoredItem";
				}
			}
			departmentScoredRecords = scoreService.getDepartmentScoreRecord(
					departmentsScored.get(0), staff.getDepartment(), year, month, 1, 2 );
		}
		return "listDepartmentScoredItem";
	}

	// listStaffNoScoredItem
	private List<Staff> staffsNoScored;
	private int staffIdChosen;
	private List<StaffScoreItem> staffNoScoredItems;
	private List<String> staffScores;

	public String listStaffNoScoredItem() throws Exception {
		Staff staffFrom = (Staff) this.getSession().get("staff");
		staffsNoScored = scoreService.getNoScoredStaff(staffFrom);// 下拉列表
		if (staffsNoScored == null) {
			staffsNoScored = new ArrayList<Staff>();
			return "listStaffNoScoredItem";
		}
		
		if(staffsNoScored.size() == 0)
		{
			return "listStaffNoScoredItem";
		}

		for (Staff staff : staffsNoScored) {
			if (staffIdChosen == staff.getStaffId()) {
				staffNoScoredItems = scoreService
						.getStaffScoreItemByStaff(staff);
				for (StaffScoreItem item : staffNoScoredItems)
				{
					item.setScoreWeight(Math.round(item.getScoreWeight()*100));
				}
				return "listStaffNoScoredItem";
			}
		}
		if (staffsNoScored.size() > 0)
			staffNoScoredItems = scoreService
					.getStaffScoreItemByStaff(staffsNoScored.get(0));
			for (StaffScoreItem item : staffNoScoredItems)
			{
				item.setScoreWeight(Math.round(item.getScoreWeight()*100));
			}
		return "listStaffNoScoredItem";
	}

	public String staffScore() throws Exception {
		if (itemsIds == null || itemsIds.size() == 0)
			return listStaffNoScoredItem();

		Staff staff = (Staff) this.getSession().get("staff");

		scoreService.staffScoreByItemIdAndStaffId(itemsIds, staffScores,
				staffIdChosen, staff.getStaffId(), staff.getStaffId());
		
		return "staffScore";
	}

	// listStaffScoredItem
	private List<Staff> staffsScored;
	private Staff staffScored;
	private List<StaffScoreRecord> staffScoredrecords;
	private float totalScore;
	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public String listStaffScoredItem() throws Exception {
		//获取评分员工staff
		Staff staff = (Staff) this.getSession().get("staff");
		//从评分记录表中找到staff打分的staff集合
		staffsScored = scoreService.getScoreStaff(staff);// 下拉列表
		int year=ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month=ConfigurationDataUtil.getIntegerVlaue("which_month");
		//如果staffsScored是null，说明staff还没有开始打分，返回
		if (staffsScored == null) {
			staffsScored = new ArrayList<Staff>();
			return "listStaffScoredItem";
		}
		// int = Integer.parseInt(staffNameChosen);
		totalScore=0f;
		//循环staffsScore，找到下拉列表中所选择的staff
		for (Staff sf : staffsScored) {
			if (staffIdChosen == sf.getStaffId()) {
				//找到有关他的评分记录（from sf；to staff）
				staffScoredrecords = scoreService.getStaffScoredRecord(sf,
						staff, year, month );
				
				for (StaffScoreRecord record : staffScoredrecords)
				{
					StaffScoreItem item = record.getStaffScoreItem();
					item.setScoreWeight(Math.round(item.getScoreWeight()*100));
					totalScore=totalScore+record.getScore()*(item.getScoreWeight()/100);
				}
				
				return "listStaffScoredItem";
			}
		}
		//如果第一次进入，显示下拉列表中的第一个相关的评分记录
		staffScoredrecords = scoreService.getStaffScoredRecord(staffsScored
				.get(0), staff, year, month );
		for (StaffScoreRecord record : staffScoredrecords)
		{
			StaffScoreItem item = record.getStaffScoreItem();
			item.setScoreWeight(Math.round(item.getScoreWeight()*100));
			totalScore=totalScore+record.getScore()*(item.getScoreWeight()/100);
		}
		return "listStaffScoredItem";
	}

	public List<String> getItemsIds() {
		return itemsIds;
	}

	public void setItemsIds(List<String> itemsIds) {
		this.itemsIds = itemsIds;
	}

	private String modifyTime;
	
	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String modifyDepartmentScore() throws Exception{
		//System.out.println("部门分数：");
		Staff staff=(Staff)this.getSession().get("staff");
		
		try{
			//修改其分数
			List<Integer> sb = recordId;
			scoreService.modifyDepartmentScore(recordId, departmentScores, departmentScoreComments);
		}catch(Exception e){
			return "error";
		}
		//return "listDepartmentScoredItem";
		return listDepartmentScoredItem();
	}
	
	private List<String> staffScoreIds;
	private List<String> staffScoresList;
	private String staffModifyTime;
	private String myStaffIdChosen;
	
	public List<String> getStaffScoreIds() {
		return staffScoreIds;
	}

	public void setStaffScoreIds(List<String> staffScoreIds) {
		this.staffScoreIds = staffScoreIds;
	}

	public List<String> getStaffScoresList() {
		return staffScoresList;
	}

	public void setStaffScoresList(List<String> staffScoresList) {
		this.staffScoresList = staffScoresList;
	}

	public String getStaffModifyTime() {
		return staffModifyTime;
	}

	public void setStaffModifyTime(String staffModifyTime) {
		this.staffModifyTime = staffModifyTime;
	}

	public String getMyStaffIdChosen() {
		return myStaffIdChosen;
	}

	public void setMyStaffIdChosen(String myStaffIdChosen) {
		this.myStaffIdChosen = myStaffIdChosen;
	}

	public String modifyStaffScore() throws Exception{
		
		Staff staff=(Staff)this.getSession().get("staff");
		boolean isSuccess=scoreService.modifyStaffScore(recordId, staffScores);
		
		return listStaffScoredItem();
	}
}
