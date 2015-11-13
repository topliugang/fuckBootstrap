package com.chinanetwork.performance.web.action;

import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.service.DepartmentManageService;
import com.chinanetwork.performance.service.StaffScoreItemManageService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class StaffScoreItemManageAction extends BaseAction{
	
	private StaffScoreItemManageService staffScoreItemManageService = new StaffScoreItemManageService();
	private DepartmentManageService departmentManageService = new DepartmentManageService();
	
	private List<StaffScoreItem> staffScoreItemsToList;
	
	private String staffScoreItemIdToDetail;	//from list in list.jsp
	private int staffScoreItemIdToDelete;	//from list in list.jsp
	private int staffScoreItemIdToModify;	//from list in list.jsp
	
	private StaffScoreItem staffScoreItemToDetail;	//todetail from id in list.jsp
	private StaffScoreItem staffScoreItemToModify;	//tomodify from id in list.jsp
	
	private StaffScoreItem staffScoreItemWillAdd;	//from input in add.jsp
//	private String staffScoreItemIdWillModify;//modify id
	private StaffScoreItem staffScoreItemWillModify;//from input in modify.jsp
	
	
	//for list and add
	private List<Department> departments;
	
	//for list
	private int departmentIdChosen;
	
	private List<Post> postsToList;
	
	private List<Post> posts;
	
	private int departmentId;
	
	private int postId;
	
	
	
	
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
	
	public List<Post> getPostsToList() {
		return postsToList;
	}

	public void setPostsToList(List<Post> postsToList) {
		this.postsToList = postsToList;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public int getDepartmentId()
	{
		return departmentId;
	}
	
	public void setDepartmentId(int departmentId)
	{
		this.departmentId = departmentId;
	}
	
	public int getPostId()
	{
		return postId;
	}
	
	public void setPostId(int postId)
	{
		this.postId = postId;
	}
	
	
	public List<StaffScoreItem> getStaffScoreItemsToList() {
		return staffScoreItemsToList;
	}

	public void setStaffScoreItemsToList(List<StaffScoreItem> staffScoreItemsToList) {
		this.staffScoreItemsToList = staffScoreItemsToList;
	}

	public String getStaffScoreItemIdToDetail() {
		return staffScoreItemIdToDetail;
	}

	public void setStaffScoreItemIdToDetail(String staffScoreItemIdToDetail) {
		this.staffScoreItemIdToDetail = staffScoreItemIdToDetail;
	}

	public int getStaffScoreItemIdToDelete() {
		return staffScoreItemIdToDelete;
	}

	public void setStaffScoreItemIdToDelete(int staffScoreItemIdToDelete) {
		this.staffScoreItemIdToDelete = staffScoreItemIdToDelete;
	}

	public int getStaffScoreItemIdToModify() {
		return staffScoreItemIdToModify;
	}

	public void setStaffScoreItemIdToModify(int staffScoreItemIdToModify) {
		this.staffScoreItemIdToModify = staffScoreItemIdToModify;
	}

	public StaffScoreItem getStaffScoreItemToDetail() {
		return staffScoreItemToDetail;
	}

	public void setStaffScoreItemToDetail(StaffScoreItem staffScoreItemToDetail) {
		this.staffScoreItemToDetail = staffScoreItemToDetail;
	}

	public StaffScoreItem getStaffScoreItemToModify() {
		return staffScoreItemToModify;
	}

	public void setStaffScoreItemToModify(StaffScoreItem staffScoreItemToModify) {
		this.staffScoreItemToModify = staffScoreItemToModify;
	}

	public StaffScoreItem getStaffScoreItemWillAdd() {
		return staffScoreItemWillAdd;
	}

	public void setStaffScoreItemWillAdd(StaffScoreItem staffScoreItemWillAdd) {
		this.staffScoreItemWillAdd = staffScoreItemWillAdd;
	}

//	public String getStaffScoreItemIdWillModify() {
//		return staffScoreItemIdWillModify;
//	}

//	public void setStaffScoreItemIdWillModify(String staffScoreItemIdWillModify) {
//		this.staffScoreItemIdWillModify = staffScoreItemIdWillModify;
//	}

	public StaffScoreItem getStaffScoreItemWillModify() {
		return staffScoreItemWillModify;
	}

	public void setStaffScoreItemWillModify(StaffScoreItem staffScoreItemWillModify) {
		this.staffScoreItemWillModify = staffScoreItemWillModify;
	}
//-----------------------------------------------------------------------------分页代码开始
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
	
	//null--->staffScoreItems
	public String list() throws Exception
	{
		
		Map<String,Integer> dataMap=staffScoreItemManageService.getTotalData();
		totalPage=dataMap.get("countPage");
		totalRecorder=dataMap.get("countRecored");
		
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		pageSize=Integer.parseInt(pageSizeStr);
		
		if(pageNo>1){
			previous="true";
		}
		
		if(pageNo<totalPage){	
			next="true";
		}
		staffScoreItemsToList = staffScoreItemManageService.getAllStaffScoreItems(pageNo);
		return "list";
	}
//----------------------------------------------------------------------------分页代码结束
	//id ---> StaffScoreItem
	public String toDetail() throws NumberFormatException, Exception 
	{
		staffScoreItemToDetail = staffScoreItemManageService.getStaffScoreItem(Integer.parseInt(staffScoreItemIdToDetail));
		return "toDetail";
	}
	
	//null
	public String toAdd() 
	{
		//posts = postManageService.getAllPosts();
		//departments = departmentManageService.getAllDepartments();// 下拉列表
		return "toAdd";
	}

	//id ---> StaffScoreItem
	public String toModify() throws NumberFormatException, Exception
	{
		
		
		//posts = postManageService.getAllPosts();
		//departments = departmentManageService.getAllDepartments();// 下拉列表
		staffScoreItemToModify = staffScoreItemManageService.getStaffScoreItem(staffScoreItemIdToModify);
		return "toModify";
	}
	
	//id
	public String delete() throws NumberFormatException, Exception
	{
		staffScoreItemManageService.deleteStaffScoreItem(staffScoreItemIdToDelete);
		return "delete";
	}
		
	public String add() throws Exception
	{
		//staffScoreItemManageService.addStaffScoreItem(staffScoreItemToModify, departmentId, postId);
		staffScoreItemManageService.addStaffScoreItem(staffScoreItemWillAdd);
		return "add";
	}
	
	public String modify() throws NumberFormatException, Exception
	{
		//staffScoreItemManageService.modifyStaffScoreItem(staffScoreItemToModify, departmentId, postId);
		staffScoreItemManageService.modifyStaffScoreItem(staffScoreItemWillModify);
		return "modify";
	}
}
