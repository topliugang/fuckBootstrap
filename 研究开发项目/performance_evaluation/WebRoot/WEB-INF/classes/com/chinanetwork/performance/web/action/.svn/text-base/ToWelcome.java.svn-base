package com.chinanetwork.performance.web.action;

import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Message;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.service.MessageService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.WelcomeDateUtil;

public class ToWelcome extends BaseAction{

	private String performanceEvaluationYear;			//绩效考评年份
	private String performanceEvaluationMonth;			//绩效考评月份
	private String performanceEvaluationStartTime;		//绩效考评开始时间
	private String performanceEvaluationEndTime;		//绩效考评结束时间
	
	
	public String getPerformanceEvaluationYear() {
		return performanceEvaluationYear;
	}


	public void setPerformanceEvaluationYear(String performanceEvaluationYear) {
		this.performanceEvaluationYear = performanceEvaluationYear;
	}


	public String getPerformanceEvaluationMonth() {
		return performanceEvaluationMonth;
	}


	public void setPerformanceEvaluationMonth(String performanceEvaluationMonth) {
		this.performanceEvaluationMonth = performanceEvaluationMonth;
	}


	public String getPerformanceEvaluationStartTime() {
		return performanceEvaluationStartTime;
	}


	public void setPerformanceEvaluationStartTime(
			String performanceEvaluationStartTime) {
		this.performanceEvaluationStartTime = performanceEvaluationStartTime;
	}


	public String getPerformanceEvaluationEndTime() {
		return performanceEvaluationEndTime;
	}


	public void setPerformanceEvaluationEndTime(String performanceEvaluationEndTime) {
		this.performanceEvaluationEndTime = performanceEvaluationEndTime;
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

	private List<Message> unreadList;
	public List<Message> getUnreadList() {
		return unreadList;
	}

	public void setUnreadList(List<Message> unreadList) {
		this.unreadList = unreadList;
	}
	public String execute(){
		try {
			Map<String,String> timeList=WelcomeDateUtil.getTimeInfo();
			performanceEvaluationYear=timeList.get("which_year");
			performanceEvaluationMonth=timeList.get("which_month");
			performanceEvaluationStartTime=timeList.get("start_time");
			performanceEvaluationEndTime=timeList.get("end_time");
			
			//确定pageSize
			pageSize=ConfigurationDataUtil.getIntegerVlaue("page_size");
			Staff staff = (Staff) this.getSession().get("staff");
			//确定总页数和总记录数
			MessageService messageService = new MessageService();
			totalRecord=messageService.getUnreadTotalCount(staff);
			totalPage=messageService.getTotalPage(pageSize, totalRecord);
			
			unreadList = messageService.listUnread(staff,pageNo,pageSize);
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "toWelcome";
	}
}
