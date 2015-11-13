package com.chinanetwork.performance.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.TrainingPlan;
import com.chinanetwork.performance.service.TrainingPlanService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

/**
 * 功能“周例会”对应的Action
 * @author Administrator
 *
 */
public class TrainingPlanAction extends BaseAction {

	private TrainingPlanService trainingPlanService=new TrainingPlanService();
	
	private String contentToSend;
	public String getContentToSend() {
		return contentToSend;
	}
	public void setContentToSend(String contentToSend) {
		this.contentToSend = contentToSend;
	}
	
	private int week;
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	/**
	 * 到达周例会上传页面
	 * @return
	 */
	public String write(){
		long thisTime=System.currentTimeMillis();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date(thisTime));
		week=calendar.get(Calendar.DAY_OF_WEEK);
		return "write";
	}
	
	private String trainTime;
	private String trainTopic;
	private String trainName;
	private String trainContent;
	
	
	public String getTrainTime() {
		return trainTime;
	}
	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}
	public String getTrainTopic() {
		return trainTopic;
	}
	public void setTrainTopic(String trainTopic) {
		this.trainTopic = trainTopic;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainContent() {
		return trainContent;
	}
	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}
	public String load(){
		long time=System.currentTimeMillis();
		Date date=new Date(time);
		
		contentToSend = "<table width=\"95%\"  border=\"1\">"+
							"<tr><th colspan=\"2\">上周工作总结</th></tr>"+
							"<tr>"+
							"<td width=\"20%\">学习/培训时间</th>"+
						    "<td width=\"80%\"><textarea>"+trainTime+"</textarea></td>"+
							"</tr>"+
							"<tr>"+
							"<td width=\"20%\">学习/培训主题</th>"+
						    "<td width=\"80%\"><textarea>"+trainTopic+"</textarea></td>"+
							"</tr>"+
							"<tr>"+
							"<td width=\"20%\">参会人员姓名</th>"+
						    "<td width=\"80%\"><textarea>"+trainName+"</textarea></td>"+
							"</tr>"+
							"<tr height = \"480\">"+
							"<td width=\"20%\">培训内容：</th>"+
						    "<td width=\"80%\"><textarea rows=\"40\">"+trainContent+"</textarea></td>"+
							"</tr>"+
						    "</table>";
		
		Staff staff=(Staff)this.getSession().get("staff");
		//构造TrainingPlan对象
		TrainingPlan trainingPlan=new TrainingPlan();
		trainingPlan.setUploadDepartment(staff.getDepartment());
		trainingPlan.setUploadStaff(staff);
		trainingPlan.setUploadTime(date);
		trainingPlan.setTrainingPlanContent(contentToSend);
		
		try{
			trainingPlanService.loadTrainingPlan(trainingPlan);
			return list();
		}catch(Exception e){
			return "error";
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	private List<TrainingPlan> trainingPlanList;
	public List<TrainingPlan> getTrainingPlanList() {
		return trainingPlanList;
	}
	public void setTrainingPlanList(
			List<TrainingPlan> trainingPlanList) {
		this.trainingPlanList = trainingPlanList;
	}
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
	/**
	 * 周例会集合
	 * @return
	 */
	public String list(){
		try {
			pageSize=ConfigurationDataUtil.getIntegerVlaue("page_size");
			//totalRecord=trainingPlanService.getTotalRecordSize();
			pageSize=trainingPlanService.getTotalPage(pageSize, totalRecord);
			//Staff staff=(Staff)this.getSession().get("staff");
			//if (staff.getRole().getRoleId() == 11 || staff.getRole().getRoleId() == 12 )
			trainingPlanList = trainingPlanService.getAllTrainingPlan();
			//else
				//trainingPlanList=trainingPlanService.getTrainingPlansByDepartment(staff.getDepartment());
			
			//为trainingPlanList中的各项加上标记
			//trainingPlanList=trainingPlanService.addSignForTrainingPlans(trainingPlanList);
			
			return "list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}	
	}

	/*	read
	 * 
	 * 
	 *
	 */
	 
	
	private int trainingPlanId;
	private TrainingPlan trainingPlanToRead;
	
	public void setTrainingPlanId(int trainingPlanId) {
		this.trainingPlanId = trainingPlanId;
	}
	public int getTrainingPlanId() {
		return trainingPlanId;
	}
	
	public void setTrainingPlanToRead(TrainingPlan trainingPlanToRead) {
		this.trainingPlanToRead = trainingPlanToRead;
	}
	public TrainingPlan getTrainingPlanToRead() {
		return trainingPlanToRead;
	}

		
	

	public String read(){
		try{
			trainingPlanToRead = trainingPlanService.getTrainingPlanById(trainingPlanId);
			return "read"; 
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

}
