package com.chinanetwork.performance.web.action;

import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.service.DepartmentManageService;
import com.chinanetwork.performance.service.PostManageService;
import com.chinanetwork.performance.service.StaffManageService;
import com.chinanetwork.performance.service.RoleManageService;

public class StaffManageAction extends BaseAction{
	
	private StaffManageService staffManageService = new StaffManageService();
	private DepartmentManageService departmentManageService = new DepartmentManageService();
	private PostManageService postManageService = new PostManageService();
	private RoleManageService roleManageService = new RoleManageService();
	
	private List<Staff> staffsToList;
	private List<Float> salarysToList;
	
	public List<Float> getSalarysToList() {
		return salarysToList;
	}

	public void setSalarysToList(List<Float> salarysToList) {
		this.salarysToList = salarysToList;
	}

	private String staffIdToDetail;	//from list in list.jsp
	private String staffIdToDelete;	//from list in list.jsp
	private String staffIdToModify;	//from list in list.jsp
	
	private Staff staffToDetail;	//todetail from id in list.jsp
	private Staff staffToModify;	//tomodify from id in list.jsp
	
	private Staff staffWillAdd;	//from input in add.jsp
//	private String staffIdWillModify;//modify id
	private Staff staffWillModify;//from input in modify.jsp
	
	private List<Department> departments;
	private List<Post> posts;
	private List<Role> roles;
	
	private int departmentIdChosen;
	
	private int postIdChosen;
	
	private int roleIdChosen;

	
	
	public int getDepartmentIdChosen() {
		return departmentIdChosen;
	}

	public void setDepartmentIdChosen(int departmentIdChosen) {
		this.departmentIdChosen = departmentIdChosen;
	}

	public int getPostIdChosen() {
		return postIdChosen;
	}

	public void setPostIdChosen(int postIdChosen) {
		this.postIdChosen = postIdChosen;
	}

	public int getRoleIdChosen() {
		return roleIdChosen;
	}

	public void setRoleIdChosen(int roleIdChosen) {
		this.roleIdChosen = roleIdChosen;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Staff> getStaffsToList() {
		return staffsToList;
	}

	public void setStaffsToList(List<Staff> staffsToList) {
		this.staffsToList = staffsToList;
	}

	public String getStaffIdToDetail() {
		return staffIdToDetail;
	}

	public void setStaffIdToDetail(String staffIdToDetail) {
		this.staffIdToDetail = staffIdToDetail;
	}

	public String getStaffIdToDelete() {
		return staffIdToDelete;
	}

	public void setStaffIdToDelete(String staffIdToDelete) {
		this.staffIdToDelete = staffIdToDelete;
	}

	public String getStaffIdToModify() {
		return staffIdToModify;
	}

	public void setStaffIdToModify(String staffIdToModify) {
		this.staffIdToModify = staffIdToModify;
	}

	public Staff getStaffToDetail() {
		return staffToDetail;
	}

	public void setStaffToDetail(Staff staffToDetail) {
		this.staffToDetail = staffToDetail;
	}

	public Staff getStaffToModify() {
		return staffToModify;
	}

	public void setStaffToModify(Staff staffToModify) {
		this.staffToModify = staffToModify;
	}

	public Staff getStaffWillAdd() {
		return staffWillAdd;
	}

	public void setStaffWillAdd(Staff staffWillAdd) {
		this.staffWillAdd = staffWillAdd;
	}


	public Staff getStaffWillModify() {
		return staffWillModify;
	}

	public void setStaffWillModify(Staff staffWillModify) {
		this.staffWillModify = staffWillModify;
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

	//null--->staffs
	public String list() throws Exception
	{
		//staffsToList = staffManageService.getAllStaffs();
		Map<String,Integer> dataMap=staffManageService.getTotalData();
		totalPage=dataMap.get("countPage");
		totalRecorder=dataMap.get("countRecored");
		
		staffsToList=staffManageService.getAllPageStaff(pageNo);
		salarysToList = staffManageService.getSalarysToList( staffsToList );
		return "list";
	}
	
	//id ---> Staff
	public String toDetail() throws NumberFormatException, Exception 
	{
		staffToDetail = staffManageService.getStaff(Integer.parseInt(staffIdToDetail));
		return "toDetail";
	}
	
	//null
	public String toAdd() throws Exception 
	{
		departments = departmentManageService.getAllDepartments();
		posts = postManageService.getAllPosts();
		roles = roleManageService.getAllRoles();
		return "toAdd";
	}

	//id ---> Staff
	public String toModify() throws NumberFormatException, Exception
	{
		staffToModify = staffManageService.getStaff(Integer.parseInt(staffIdToModify));
		departments = departmentManageService.getAllDepartments();
		posts = postManageService.getAllPosts();
		roles = roleManageService.getAllRoles();
		return "toModify";
	}
	
	//id
	public String delete() throws NumberFormatException, Exception
	{
		staffManageService.deleteStaff(Integer.parseInt(staffIdToDelete));
		return "delete";
	}
		
	public String add() throws Exception
	{
		staffWillAdd.setDepartment(departmentManageService.getDepartment(departmentIdChosen));
		staffWillAdd.setPost(postManageService.getPost(postIdChosen));
		staffWillAdd.setRole(roleManageService.getRole(roleIdChosen));
		
		staffManageService.addStaff(staffWillAdd);
		return "add";
	}
	
	public String modify() throws NumberFormatException, Exception
	{
		staffWillModify.setDepartment(departmentManageService.getDepartment(departmentIdChosen));
		staffWillModify.setPost(postManageService.getPost(postIdChosen));
		staffWillModify.setRole(roleManageService.getRole(roleIdChosen));
		staffManageService.modifyStaff(staffWillModify.getStaffId(), staffWillModify);
		return "modify";
	}
	
	public String topasswordmodify()
	{
		return "topasswordmodify";
	}
	
	private String oldpassword;
	private String newpassword;
	
	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String passwordmodify() throws Exception
	{
		Staff staff = (Staff) this.getSession().get("staff");
		staffManageService.passwordmodify(staff, newpassword);
		return "passwordmodify";
	}
	
}
