package com.chinanetwork.performance.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.service.DepartmentManageService;

public class DepartmentManageAction extends BaseAction {

	private DepartmentManageService departmentManageService = new DepartmentManageService();

	private List<Department> departmentsToList;
	private List<Department> higherDepartments;

	public List<Department> getHigherDepartments() {
		return higherDepartments;
	}

	public void setHigherDepartments(List<Department> higherDepartments) {
		this.higherDepartments = higherDepartments;
	}

	private String departmentIdToDetail; // from list in list.jsp
	private String departmentIdToDelete; // from list in list.jsp
	private String departmentIdToModify; // from list in list.jsp

	private Department departmentToDetail; // todetail from id in list.jsp
	private Department departmentToModify; // tomodify from id in list.jsp

	private Department departmentWillAdd; // from input in add.jsp
	// private String departmentIdWillModify;//modify id
	private Department departmentWillModify;// from input in modify.jsp

	public List<Department> getDepartmentsToList() {
		return departmentsToList;
	}

	public void setDepartmentsToList(List<Department> departmentsToList) {
		this.departmentsToList = departmentsToList;
	}

	public String getDepartmentIdToDetail() {
		return departmentIdToDetail;
	}

	public void setDepartmentIdToDetail(String departmentIdToDetail) {
		this.departmentIdToDetail = departmentIdToDetail;
	}

	public String getDepartmentIdToDelete() {
		return departmentIdToDelete;
	}

	public void setDepartmentIdToDelete(String departmentIdToDelete) {
		this.departmentIdToDelete = departmentIdToDelete;
	}

	public String getDepartmentIdToModify() {
		return departmentIdToModify;
	}

	public void setDepartmentIdToModify(String departmentIdToModify) {
		this.departmentIdToModify = departmentIdToModify;
	}

	public Department getDepartmentToDetail() {
		return departmentToDetail;
	}

	public void setDepartmentToDetail(Department departmentToDetail) {
		this.departmentToDetail = departmentToDetail;
	}

	public Department getDepartmentToModify() {
		return departmentToModify;
	}

	public void setDepartmentToModify(Department departmentToModify) {
		this.departmentToModify = departmentToModify;
	}

	public Department getDepartmentWillAdd() {
		return departmentWillAdd;
	}

	public void setDepartmentWillAdd(Department departmentWillAdd) {
		this.departmentWillAdd = departmentWillAdd;
	}

	public Department getDepartmentWillModify() {
		return departmentWillModify;
	}

	public void setDepartmentWillModify(Department departmentWillModify) {
		this.departmentWillModify = departmentWillModify;
	}

	private int pageNo = 1; // 记录当前页数

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	private int totalPage; // 总页数
	private int totalRecorder; // 总记录数

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

	// null--->departments
	public String list() throws Exception {
		Map<String,Integer> dataMap=departmentManageService.getTotalData();
		totalPage=dataMap.get("countPage");
		totalRecorder=dataMap.get("countRecored");
		//departmentsToList = departmentManageService.getAllDepartments();
		departmentsToList=departmentManageService.getAllPageDepartment(pageNo);
		
		if (higherDepartments == null)
			higherDepartments = new ArrayList<Department>();

		for (Department department : departmentsToList) {
			Department higherDepartment = departmentManageService
					.getDepartment(department.getHigherDepartmentId());
			if (higherDepartment == null)
				higherDepartment = new Department();
			higherDepartments.add(higherDepartment);
		}
		
		
		
		
		
		
		
		

		return "list";
	}

	// id ---> Department
	public String toDetail() throws NumberFormatException, Exception {
		departmentToDetail = departmentManageService.getDepartment(Integer
				.parseInt(departmentIdToDetail));
		return "toDetail";
	}

	// null
	public String toAdd() throws Exception {
		departmentsToList = departmentManageService.getAllDepartments();
		return "toAdd";
	}

	// id ---> Department
	public String toModify() throws NumberFormatException, Exception {
		departmentsToList = departmentManageService.getAllDepartments();
		departmentToModify = departmentManageService.getDepartment(Integer
				.parseInt(departmentIdToModify));
		return "toModify";
	}

	// id
	public String delete() throws NumberFormatException, Exception {
		departmentManageService.deleteDepartment(Integer
				.parseInt(departmentIdToDelete));
		return "delete";
	}

	public String add() throws Exception {
		departmentManageService.addDepartment(departmentWillAdd);
		return "add";
	}

	public String modify() throws NumberFormatException, Exception {
		departmentManageService.modifyDepartment(departmentWillModify);
		return "modify";
	}
}
