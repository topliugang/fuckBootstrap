package com.chinanetwork.performance.web.action;

import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.Role;
import com.chinanetwork.performance.service.RoleManageService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class RoleManageAction extends BaseAction{
	
	private RoleManageService roleManageService = new RoleManageService();
	
	private List<Role> rolesToList;
	
	private String roleIdToDetail;	//from list in list.jsp
	private String roleIdToDelete;	//from list in list.jsp
	private String roleIdToModify;	//from list in list.jsp
	
	private Role roleToDetail;	//todetail from id in list.jsp
	private Role roleToModify;	//tomodify from id in list.jsp
	
	private Role roleWillAdd;	//from input in add.jsp
//	private String roleIdWillModify;//modify id
	private Role roleWillModify;//from input in modify.jsp
	
	private List<Funcpage> funcpages;
	
	
	
	public List<Funcpage> getFuncpages() {
		return funcpages;
	}

	public void setFuncpages(List<Funcpage> funcpages) {
		this.funcpages = funcpages;
	}

	public List<Role> getRolesToList() {
		return rolesToList;
	}

	public void setRolesToList(List<Role> rolesToList) {
		this.rolesToList = rolesToList;
	}

	public String getRoleIdToDetail() {
		return roleIdToDetail;
	}

	public void setRoleIdToDetail(String roleIdToDetail) {
		this.roleIdToDetail = roleIdToDetail;
	}

	public String getRoleIdToDelete() {
		return roleIdToDelete;
	}

	public void setRoleIdToDelete(String roleIdToDelete) {
		this.roleIdToDelete = roleIdToDelete;
	}

	public String getRoleIdToModify() {
		return roleIdToModify;
	}

	public void setRoleIdToModify(String roleIdToModify) {
		this.roleIdToModify = roleIdToModify;
	}

	public Role getRoleToDetail() {
		return roleToDetail;
	}

	public void setRoleToDetail(Role roleToDetail) {
		this.roleToDetail = roleToDetail;
	}

	public Role getRoleToModify() {
		return roleToModify;
	}

	public void setRoleToModify(Role roleToModify) {
		this.roleToModify = roleToModify;
	}

	public Role getRoleWillAdd() {
		return roleWillAdd;
	}

	public void setRoleWillAdd(Role roleWillAdd) {
		this.roleWillAdd = roleWillAdd;
	}



	public Role getRoleWillModify() {
		return roleWillModify;
	}

	public void setRoleWillModify(Role roleWillModify) {
		this.roleWillModify = roleWillModify;
	}
//=========================================================分页开始=======================
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

	//null--->roles
	public String list() throws Exception
	{
		Map<String,Integer> dataMap=roleManageService.getTotalData();
		totalPage=dataMap.get("countPage");
		totalRecorder=dataMap.get("countRecored");
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		pageSize=Integer.parseInt(pageSizeStr);
		rolesToList = roleManageService.getAllRoles(pageNo);
		return "list";
	}
//=========================================================分页结束=======================
	//id ---> Role
	public String toDetail() throws NumberFormatException, Exception 
	{
		roleToDetail = roleManageService.getRole(Integer.parseInt(roleIdToDetail));
		funcpages = roleManageService.getFuncpages(Integer.parseInt(roleIdToDetail));
		return "toDetail";
	}
	
	//null
	public String toAdd() 
	{
		return "toAdd";
	}

	//id ---> Role
	public String toModify() throws NumberFormatException, Exception
	{
		roleToModify = roleManageService.getRole(Integer.parseInt(roleIdToModify));
		funcpages = roleManageService.getAllFuncpages();
		chooseList = roleManageService.getFuncpageIdsByRoleId(roleToModify.getRoleId());
		return "toModify";
	}
	
	//id
	public String delete() throws NumberFormatException, Exception
	{
		roleManageService.deleteRole(Integer.parseInt(roleIdToDelete));
		return "delete";
	}
		
	public String add() throws Exception
	{
		roleManageService.addRole(roleWillAdd);
		return "add";
	}
	
	public String modify() throws NumberFormatException, Exception
	{
		List<Funcpage> sb = funcpages;
		roleManageService.modifyRole(roleWillModify.getRoleId(), roleWillModify);
		roleManageService.modifyFuncage(roleWillModify, funcpageIdsChoosen);
		return "modify";
	}

	
	private List<Integer> funcpageIdsChoosen;
	
	public void setFuncpageIdsChoosen(List<Integer> funcpageIdsChoosen) {
		this.funcpageIdsChoosen = funcpageIdsChoosen;
	}

	public List<Integer> getFuncpageIdsChoosen() {
		return funcpageIdsChoosen;
	}

	private List<Integer> chooseList;

	public void setChooseList(List<Integer> chooseList){
		this.chooseList = chooseList;
	}

	public List<Integer> getChooseList() {
		return chooseList;
	}
	
	
}
