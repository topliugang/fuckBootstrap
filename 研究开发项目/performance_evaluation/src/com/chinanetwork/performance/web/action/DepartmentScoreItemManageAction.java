package com.chinanetwork.performance.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.ScoreItemCategory;
import com.chinanetwork.performance.service.DepartmentManageService;
import com.chinanetwork.performance.service.DepartmentScoreItemManageService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class DepartmentScoreItemManageAction extends BaseAction {

	

	private DepartmentScoreItemManageService departmentScoreItemManageService = new DepartmentScoreItemManageService();
	private DepartmentManageService departmentManageService = new DepartmentManageService();

	private List<DepartmentScoreItem> departmentScoreItemsToList;

	private String departmentScoreItemIdToDetail; // from list in list.jsp
	private String departmentScoreItemIdToDelete; // from list in list.jsp
	private int departmentScoreItemIdToModify; // from list in list.jsp

	private DepartmentScoreItem departmentScoreItemToDetail; // todetail from id
																// in list.jsp
	private DepartmentScoreItem departmentScoreItemToModify; // tomodify from id
																// in list.jsp

	private DepartmentScoreItem departmentScoreItemWillAdd; // from input in
															// add.jsp
	// private String departmentScoreItemIdWillModify;//modify id
	private DepartmentScoreItem departmentScoreItemWillModify;// from input in
																// modify.jsp

	
	
	//for list and add
	private List<Department> departments;
	
	//for list
	private int departmentIdChosen;

	//for add
	private List<ScoreItemCategory> categories;
	
	private int categoryId;
	private int departmentId;
	private List<Integer> departmentsFromId;
	
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public List<Integer> getDepartmentsFromId() {
		return departmentsFromId;
	}

	public void setDepartmentsFromId(List<Integer> departmentsFromId) {
		this.departmentsFromId = departmentsFromId;
	}
	
	public List<ScoreItemCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ScoreItemCategory> categories) {
		this.categories = categories;
	} 

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

	public List<DepartmentScoreItem> getDepartmentScoreItemsToList() {
		return departmentScoreItemsToList;
	}

	public void setDepartmentScoreItemsToList(
			List<DepartmentScoreItem> departmentScoreItemsToList) {
		this.departmentScoreItemsToList = departmentScoreItemsToList;
	}

	public String getDepartmentScoreItemIdToDetail() {
		return departmentScoreItemIdToDetail;
	}

	public void setDepartmentScoreItemIdToDetail(
			String departmentScoreItemIdToDetail) {
		this.departmentScoreItemIdToDetail = departmentScoreItemIdToDetail;
	}

	public String getDepartmentScoreItemIdToDelete() {
		return departmentScoreItemIdToDelete;
	}

	public void setDepartmentScoreItemIdToDelete(
			String departmentScoreItemIdToDelete) {
		this.departmentScoreItemIdToDelete = departmentScoreItemIdToDelete;
	}

	public int getDepartmentScoreItemIdToModify() {
		return departmentScoreItemIdToModify;
	}

	public void setDepartmentScoreItemIdToModify(
			int departmentScoreItemIdToModify) {
		this.departmentScoreItemIdToModify = departmentScoreItemIdToModify;
	}

	public DepartmentScoreItem getDepartmentScoreItemToDetail() {
		return departmentScoreItemToDetail;
	}

	public void setDepartmentScoreItemToDetail(
			DepartmentScoreItem departmentScoreItemToDetail) {
		this.departmentScoreItemToDetail = departmentScoreItemToDetail;
	}

	public DepartmentScoreItem getDepartmentScoreItemToModify() {
		return departmentScoreItemToModify;
	}

	public void setDepartmentScoreItemToModify(
			DepartmentScoreItem departmentScoreItemToModify) {
		this.departmentScoreItemToModify = departmentScoreItemToModify;
	}

	public DepartmentScoreItem getDepartmentScoreItemWillAdd() {
		return departmentScoreItemWillAdd;
	}

	public void setDepartmentScoreItemWillAdd(
			DepartmentScoreItem departmentScoreItemWillAdd) {
		this.departmentScoreItemWillAdd = departmentScoreItemWillAdd;
	}

	// public String getDepartmentScoreItemIdWillModify() {
	// return departmentScoreItemIdWillModify;
	// }

	// public void setDepartmentScoreItemIdWillModify(String
	// departmentScoreItemIdWillModify) {
	// this.departmentScoreItemIdWillModify = departmentScoreItemIdWillModify;
	// }

	public DepartmentScoreItem getDepartmentScoreItemWillModify() {
		return departmentScoreItemWillModify;
	}

	public void setDepartmentScoreItemWillModify(
			DepartmentScoreItem departmentScoreItemWillModify) {
		this.departmentScoreItemWillModify = departmentScoreItemWillModify;
	}
	
	
//---------------------------------------------------------------------------------分页代码开始
	private String open="false";		//开关 ----- 是否显示分页
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	private int pageNo=1;  //记录当前页数
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	private int totalPage;		//总页数
	private int totalRecorder;	//总记录数
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecorder() {
		return totalRecorder;
	}

	public void setTotalRecorder(int totalRecorder) {
		this.totalRecorder = totalRecorder;
	}
	
	private int pageSize;	//每页显示的记录数
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private String previous="false";
	private String next="false";
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}

	// null--->departmentScoreItems
	public String list() throws Exception {
		departments = departmentManageService.getAllDepartments();// 下拉列表
		if (departments == null) {
			departments = new ArrayList<Department>();
			return "list";
		}
		
		next="false";
		previous="false";
		
		if(open.equals("true")){
			Map<String,Integer> dataMap=departmentScoreItemManageService.getTotalData(departmentIdChosen);
			totalPage=dataMap.get("countPage");
			totalRecorder=dataMap.get("countRecored");
			String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
			pageSize=Integer.parseInt(pageSizeStr);
			
			if(pageNo>1){
				previous="true";
			}
			System.out.println(pageNo+","+totalPage);
			if(pageNo<totalPage){	
				next="true";
			}
			
			departmentScoreItemsToList = departmentScoreItemManageService.getDepartmentScoreItemsByDepartmentId(departmentIdChosen,pageNo);
			if(departmentScoreItemsToList==null){
				open="false";
			}else if(departmentScoreItemsToList.size()==0){
				open="false";
			}
		}
		return "list";
	}
//---------------------------------------------------------------------------------分页代码结束
	// id ---> DepartmentScoreItem
	public String toDetail() throws NumberFormatException, Exception {
		departmentScoreItemToDetail = departmentScoreItemManageService
				.getDepartmentScoreItem(Integer
						.parseInt(departmentScoreItemIdToDetail));
		return "toDetail";
	}

	// null
	public String toAdd() throws Exception {
		departments = departmentManageService.getAllDepartments();// 下拉列表
		categories = departmentScoreItemManageService.getAllCategories();
		
	
		
		return "toAdd";
	}

	// id ---> DepartmentScoreItem
	public String toModify() throws NumberFormatException, Exception {
		departments = departmentManageService.getAllDepartments();// 下拉列表
		categories = departmentScoreItemManageService.getAllCategories();
		departmentScoreItemToModify = departmentScoreItemManageService
				.getDepartmentScoreItem(departmentScoreItemIdToModify);
		departmentsFromId = departmentScoreItemManageService.getFromDepartmentsIdById(departmentScoreItemIdToModify);
		return "toModify";
	}

	// id
	public String delete() throws NumberFormatException, Exception {
		departmentScoreItemManageService.deleteDepartmentScoreItem(Integer
				.parseInt(departmentScoreItemIdToDelete));
		return "delete";
	}

	public String add() throws Exception {
		ScoreItemCategory category = new ScoreItemCategory();
		category.setDepartmentScoreCategoryId(categoryId);
		Department dep = new Department();
		dep.setDepartmentId(departmentId);
	
		departmentScoreItemWillAdd.setScoreItemCategory(category);
		departmentScoreItemWillAdd.setDepartment(dep);
		
		departmentScoreItemManageService
				.addDepartmentScoreItem(departmentScoreItemWillAdd, departmentsFromId);
		return "add";
	}

	public String modify() throws NumberFormatException, Exception {
		ScoreItemCategory category = new ScoreItemCategory();
		category.setDepartmentScoreCategoryId(categoryId);
		Department dep = new Department();
		dep.setDepartmentId(departmentId);
	
		departmentScoreItemWillModify.setScoreItemCategory(category);
		departmentScoreItemWillModify.setDepartment(dep);
		
		departmentScoreItemManageService
				.modifyDepartmentScoreItem(departmentScoreItemWillModify, departmentsFromId);
		return "modify";
	}
}
