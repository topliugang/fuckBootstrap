package com.chinanetwork.performance.web.action;

import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.service.PostManageService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class PostManageAction extends BaseAction{
	
	private PostManageService postManageService = new PostManageService();
	
	private List<Post> postsToList;
	
	private String postIdToDetail;	//from list in list.jsp
	private String postIdToDelete;	//from list in list.jsp
	private String postIdToModify;	//from list in list.jsp
	
	private Post postToDetail;	//todetail from id in list.jsp
	private Post postToModify;	//tomodify from id in list.jsp
	
	private Post postWillAdd;	//from input in add.jsp
//	private String postIdWillModify;//modify id
	private Post postWillModify;//from input in modify.jsp
	
	
	
	public List<Post> getPostsToList() {
		return postsToList;
	}

	public void setPostsToList(List<Post> postsToList) {
		this.postsToList = postsToList;
	}

	public String getPostIdToDetail() {
		return postIdToDetail;
	}

	public void setPostIdToDetail(String postIdToDetail) {
		this.postIdToDetail = postIdToDetail;
	}

	public String getPostIdToDelete() {
		return postIdToDelete;
	}

	public void setPostIdToDelete(String postIdToDelete) {
		this.postIdToDelete = postIdToDelete;
	}

	public String getPostIdToModify() {
		return postIdToModify;
	}

	public void setPostIdToModify(String postIdToModify) {
		this.postIdToModify = postIdToModify;
	}

	public Post getPostToDetail() {
		return postToDetail;
	}

	public void setPostToDetail(Post postToDetail) {
		this.postToDetail = postToDetail;
	}

	public Post getPostToModify() {
		return postToModify;
	}

	public void setPostToModify(Post postToModify) {
		this.postToModify = postToModify;
	}

	public Post getPostWillAdd() {
		return postWillAdd;
	}

	public void setPostWillAdd(Post postWillAdd) {
		this.postWillAdd = postWillAdd;
	}

//	public String getPostIdWillModify() {
//		return postIdWillModify;
//	}

//	public void setPostIdWillModify(String postIdWillModify) {
//		this.postIdWillModify = postIdWillModify;
//	}

	public Post getPostWillModify() {
		return postWillModify;
	}

	public void setPostWillModify(Post postWillModify) {
		this.postWillModify = postWillModify;
	}

	
	//-----------------------------------------------------------------分页代码开始
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
	
	//null--->posts
	public String list() throws Exception
	{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		pageSize=Integer.parseInt(pageSizeStr);
		
		Map<String,Integer> dataMap=postManageService.getTotalData();
		totalPage=dataMap.get("countPage");
		totalRecorder=dataMap.get("countRecored");
		
		postsToList = postManageService.getAllPosts(pageNo);
		return "list";
	}
	//-----------------------------------------------------------------分页代码结束
	//id ---> Post
	public String toDetail() throws NumberFormatException, Exception 
	{
		postToDetail = postManageService.getPost(Integer.parseInt(postIdToDetail));
		return "toDetail";
	}
	
	//null
	public String toAdd() 
	{
		return "toAdd";
	}

	//id ---> Post
	public String toModify() throws NumberFormatException, Exception
	{
		postToModify = postManageService.getPost(Integer.parseInt(postIdToModify));
		return "toModify";
	}
	
	//id
	public String delete() throws NumberFormatException, Exception
	{
		postManageService.deletePost(Integer.parseInt(postIdToDelete));
		return "delete";
	}
		
	public String add() throws Exception
	{
		postManageService.addPost(postWillAdd);
		return "add";
	}
	
	public String modify() throws NumberFormatException, Exception
	{
		postManageService.modifyPost(postWillModify.getPostId(), postWillModify);
		return "modify";
	}
}
