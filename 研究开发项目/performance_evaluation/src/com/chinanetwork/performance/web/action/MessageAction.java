package com.chinanetwork.performance.web.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.Message;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffClass;
import com.chinanetwork.performance.bean.UploadFile;
import com.chinanetwork.performance.service.DepartmentManageService;
import com.chinanetwork.performance.service.MessageAccepterChoiceService;
import com.chinanetwork.performance.service.MessageService;
import com.chinanetwork.performance.service.StaffClassManageService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class MessageAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MessageService messageService = new MessageService();
		
	/**
	 * 进入发送页面
	 * 
	 * @return
	 */
	
	private List<Department> departments;
	private List<Staff> staffs;
	private List<StaffClass> classes;
	
	public List<StaffClass> getClasses() {
		return classes;
	}

	public void setClasses(List<StaffClass> classes) {
		this.classes = classes;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
	public String write() throws Exception
	{
		DepartmentManageService departmentManageService = new DepartmentManageService();
		StaffClassManageService staffClassManageService = new StaffClassManageService();
		departments = departmentManageService.getAllDepartments();
		classes = staffClassManageService.getAllStaffClasses();
		departmentList=messageAccepterChoiceService.getAllDepartment();
		staffClassList=messageAccepterChoiceService.getAllStaffClass();
		
		return "write";
	}
	//消息接收者选择
	private List<Department> departmentList;
	private List<Staff> staffList;
	private String departmentIdStr;
	public List<Department> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	public List<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	public String getDepartmentIdStr() {
		return departmentIdStr;
	}
	public void setDepartmentIdStr(String departmentIdStr) {
		this.departmentIdStr = departmentIdStr;
	}
	private List<StaffClass> staffClassList;
	public List<StaffClass> getStaffClassList() {
		return staffClassList;
	}
	public void setStaffClassList(List<StaffClass> staffClassList) {
		this.staffClassList = staffClassList;
	}
	
	private String classIdStr;
	public String getClassIdStr() {
		return classIdStr;
	}
	public void setClassIdStr(String classIdStr) {
		this.classIdStr = classIdStr;
	}
	
	private MessageAccepterChoiceService messageAccepterChoiceService=new MessageAccepterChoiceService();
	public String getStaffsByDep(){
		try {
			List<Staff> staffList=messageAccepterChoiceService.getStaffListByDepartmentId(departmentIdStr);
			String info="";
			for(int i=0;i<staffList.size();i++){
				info=info+staffList.get(i).getStaffId()+","+staffList.get(i).getStaffName()+";";
			}
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return null;
	}

	public String getStaffByCla(){
		System.out.println(classIdStr);
		try {
			List<Staff> staffList=messageAccepterChoiceService.getStaffListByClassId(classIdStr);
			String info="";
			for(int i=0;i<staffList.size();i++){
				info=info+staffList.get(i).getStaffId()+","+staffList.get(i).getStaffName()+";";
			}
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return null;
	}
	
	
	/**
	 * 
	 * send
	 * @return
	 */
	private String titleToSend;
	private String contentToSend;
	private List<Integer> staffidstoreceiveToSend;
	private List<Integer> departmentidstoreceiveToSend;
	private List<String> classidstoreceiveToSend;
	public List<String> getClassidstoreceiveToSend() {
		return classidstoreceiveToSend;
	}

	public void setClassidstoreceiveToSend(List<String> classidstoreceiveToSend) {
		this.classidstoreceiveToSend = classidstoreceiveToSend;
	}

	public String getTitleToSend() {
		return titleToSend;
	}

	public void setTitleToSend(String titleToSend) {
		this.titleToSend = titleToSend;
	}

	public String getContentToSend() {
		return contentToSend;
	}

	public void setContentToSend(String contentToSend) {
		this.contentToSend = contentToSend;
	}

	public List<Integer> getStaffidstoreceiveToSend() {
		return staffidstoreceiveToSend;
	}

	public void setStaffidstoreceiveToSend(List<Integer> staffidstoreceiveToSend) {
		this.staffidstoreceiveToSend = staffidstoreceiveToSend;
	}

	public List<Integer> getDepartmentidstoreceiveToSend() {
		return departmentidstoreceiveToSend;
	}

	public void setDepartmentidstoreceiveToSend(
			List<Integer> departmentidstoreceiveToSend) {
		this.departmentidstoreceiveToSend = departmentidstoreceiveToSend;
	}
	
	//多文件上传
	private List<File> uploads;
	private List<String> uploadsContentType;
	private List<String> uploadsFileName;

	public List<File> getUploads() {
		return uploads;
	}
	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadsContentType() {
		return uploadsContentType;
	}
	public void setUploadsContentType(List<String> uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}

	public List<String> getUploadsFileName() {
		return uploadsFileName;
	}
	public void setUploadsFileName(List<String> uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}
	private String fileuploadInfo="";		//文件上传提示信息
	public String getFileuploadInfo() {
		return fileuploadInfo;
	}
	public void setFileuploadInfo(String fileuploadInfo) {
		this.fileuploadInfo = fileuploadInfo;
	}

	@Override
	public void addActionError(String anErrorMessage){
		System.out.println("错误信息:"+getText("struts.multipart.maxSize.limit"));
		if(anErrorMessage.startsWith("the request was rejected because its size")){
			super.clearActionErrors();
			super.addActionError(getText("struts.multipart.maxSize.limit"));
		}else{
			super.addActionError(anErrorMessage);
		}
	}
	
	public String send() throws Exception{
		System.out.println(titleToSend);
		//获取发送信息用户
		Staff staff = (Staff) this.getSession().get("staff");
		
		//创建消息类对象
		Message messageToSend = new Message();
		
		//如果用户上传了大小合适的文件
		if(uploads!=null){
			//上传文件保存在服务器中的路径
			String savePath=ServletActionContext.getServletContext().getRealPath("/upload");
			File savePathDir=new File(savePath);
			if(!savePathDir.exists()){
				savePathDir.mkdirs();
			}
			
			//生成上传文件保存在服务器中的名称
			List<String> fileNames=messageService.getUploadFileSaveName(uploadsFileName);
			
			//将上传的文件保存到服务器上
			boolean isSuccess=messageService.saveFileOnServer(savePath, uploads, fileNames);
			if(isSuccess){
				fileuploadInfo="文件上传成功";
			}else{
				return "error";
			}
			
			//向数据库中插入上传文件的相关信息
			for(int i=0;i<uploads.size();i++){
				if(!messageService.addUploadFileInfo(staff, uploads.get(i), uploadsFileName.get(i), fileNames.get(i))){
					return "error";
				}
			}
			
			//将保存在服务器中的上传文件名称放入messageToSend对象中
			List<String> attachment=new ArrayList<String>();
			for(int i=0;i<fileNames.size();i++){
				attachment.add(fileNames.get(i).split("\\.")[0]);
			}
			messageToSend.setAttachment(attachment);
		}
		
		
		messageToSend.setTitle(titleToSend);
		messageToSend.setContent(contentToSend);
		messageToSend.setSender(staff);
		messageToSend.setReceiver(messageService.mergeStaff(departmentidstoreceiveToSend, staffidstoreceiveToSend,classidstoreceiveToSend));
		
		messageService.send(messageToSend);
		
		return "send";
	}
	/**
	 * 
	 * 
	 * read
	 * @return
	 */
	
	private int messageId;
	
	
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	Message messageToRead;

	public Message getMessageToRead() {
		return messageToRead;
	}

	public void setMessageToRead(Message messageToRead) {
		this.messageToRead = messageToRead;
	}
	private List<UploadFile> uploadFileList; //储存消息附件信息
	private String goBackMark; //返回页面标记  1 表示来自“我的消息箱”；2 表示来自“我的未读消息”
	private String goBackOrder; //返回命令 哪里来的回到那里去
	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}
	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
	public String getGoBackMark() {
		return goBackMark;
	}

	public void setGoBackMark(String goBackMark) {
		this.goBackMark = goBackMark;
	}

	public String getGoBackOrder() {
		return goBackOrder;
	}

	public void setGoBackOrder(String goBackOrder) {
		this.goBackOrder = goBackOrder;
	}

	public String read() throws Exception
	{
		messageToRead = messageService.read(messageId);
		
		List<String> attachment=messageToRead.getAttachment();
		uploadFileList=messageService.getAttachment(attachment);
		
		if(goBackMark.equals("1")){
			goBackOrder="message_listReceived";
		}else if(goBackMark.equals("2")){
			goBackOrder="message_listUnread";
		}
		
		if(checkUnread(messageId))
			receive(messageId);
		return "read";
	}
	
	public String sendMessageRead(){
		try{
			messageToRead = messageService.read(messageId);
			
			List<String> attachment=messageToRead.getAttachment();
			uploadFileList=messageService.getAttachment(attachment);
			
		}catch(Exception e){
			return "error";
		}
		return "toSendMessageRead";
	}
	/**
	 * 
	 * 
	 */
	public String receive(int messageId) throws Exception
	{
		Staff staff = (Staff) this.getSession().get("staff");
		messageService.receive(staff, messageId);
		boolean hasUnreadMessage = false;
		//更新welcome的未读通知
		if( messageService.listUnread(staff) != null )
			hasUnreadMessage = true;
		this.getSession().put("hasUnreadMessage", hasUnreadMessage);
		return "receive";
	}
	
	public boolean checkUnread(int messageId) throws Exception
	{
		Staff staff = (Staff) this.getSession().get("staff");
		return messageService.checkUnread(staff, messageId);
	}
	
	/**
	 * 
	 * 
	 */
	List<Message> sentList;
	public List<Message> getSentList() {
		return sentList;
	}

	public void setSentList(List<Message> sentList) {
		this.sentList = sentList;
	}

	public String listSent() throws Exception
	{	//确定pageSize
		pageSize=ConfigurationDataUtil.getIntegerVlaue("page_size");
		Staff staff = (Staff) this.getSession().get("staff");
		//确定总页数和总记录数
		totalRecord=messageService.getSendTotalCount(staff);
		totalPage=messageService.getTotalPage(pageSize, totalRecord);
		
		sentList = messageService.listSent(staff,pageNo,pageSize);
		return "listSent";
	}
	
	/**
	 * 
	 * 
	 */
	List<Message> receivedList;
	public List<Message> getReceivedList() {
		return receivedList;
	}

	public void setReceivedList(List<Message> receivedList) {
		this.receivedList = receivedList;
	}

	public String listReceived() throws Exception
	{
		//确定pageSize
		pageSize=ConfigurationDataUtil.getIntegerVlaue("page_size");
		Staff staff = (Staff) this.getSession().get("staff");
		//确定总页数和总记录数
		totalRecord=messageService.getReceivedTotalCount(staff);
		totalPage=messageService.getTotalPage(pageSize, totalRecord);
		
		receivedList = messageService.listReceived(staff,pageNo,pageSize);
		return "listReceived";
	}
	
	/**
	 * 
	 * 
	 */
	List<Message> unreadList;
	public List<Message> getUnreadList() {
		return unreadList;
	}

	public void setUnreadList(List<Message> unreadList) {
		this.unreadList = unreadList;
	}

	public String listUnread() throws Exception
	{
		//确定pageSize
		pageSize=ConfigurationDataUtil.getIntegerVlaue("page_size");
		Staff staff = (Staff) this.getSession().get("staff");
		//确定总页数和总记录数
		totalRecord=messageService.getUnreadTotalCount(staff);
		totalPage=messageService.getTotalPage(pageSize, totalRecord);
		
		unreadList = messageService.listUnread(staff,pageNo,pageSize);
		return "listUnread";
	}
	//下载
	private String fileId;
	private String fileName;
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private InputStream downLoad;
	public InputStream getDownLoad() {
		return downLoad;
	}
	public void setDownLoad(InputStream downLoad) {
		this.downLoad = downLoad;
	}
	public String downLoad(){
		String downLoadFileName=File.separator+"upload"+File.separator+fileId+".dat";
		downLoad=ServletActionContext.getServletContext().getResourceAsStream(downLoadFileName);
		return "success";
	}
	
	
	//为收信箱，发信箱和未读信息添加分页
	private int pageNo=1;			//当前页数
	private int pageSize;			//每一页显示多少条记录
	private int totalPage;			//总页数
	private int totalRecord;		//总记录数

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
}
