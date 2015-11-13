package com.chinanetwork.performance.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.WeekMeetingRecord;
import com.chinanetwork.performance.service.WeekMeetingService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

/**
 * 功能“周例会”对应的Action
 * @author Administrator
 *
 */
public class WeekMeetingAction extends BaseAction {

	private WeekMeetingService weekMeetingService=new WeekMeetingService();
	
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
	private String[] lastNeirong;
	private String[] lastBuzhou;
	private String[] lastWancheng;
	private String[] lastYuanyin;
	private String[] thisNeirong;
	private String[] thisBuzhou;
	private String[] thisWancheng;

	
	public String[] getLastNeirong() {
		return lastNeirong;
	}
	public void setLastNeirong(String[] lastNeirong) {
		this.lastNeirong = lastNeirong;
	}
	public String[] getLastBuzhou() {
		return lastBuzhou;
	}
	public void setLastBuzhou(String[] lastBuzhou) {
		this.lastBuzhou = lastBuzhou;
	}
	public String[] getLastWancheng() {
		return lastWancheng;
	}
	public void setLastWancheng(String[] lastWancheng) {
		this.lastWancheng = lastWancheng;
	}
	public String[] getLastYuanyin() {
		return lastYuanyin;
	}
	public void setLastYuanyin(String[] lastYuanyin) {
		this.lastYuanyin = lastYuanyin;
	}
	public String[] getThisNeirong() {
		return thisNeirong;
	}
	public void setThisNeirong(String[] thisNeirong) {
		this.thisNeirong = thisNeirong;
	}
	public String[] getThisBuzhou() {
		return thisBuzhou;
	}
	public void setThisBuzhou(String[] thisBuzhou) {
		this.thisBuzhou = thisBuzhou;
	}
	public String[] getThisWancheng() {
		return thisWancheng;
	}
	public void setThisWancheng(String[] thisWancheng) {
		this.thisWancheng = thisWancheng;
	}
	
	
	public String load(){
		long time=System.currentTimeMillis();
		Date date=new Date(time);
		
		Staff staff=(Staff)this.getSession().get("staff");
		contentToSend = 
			"<table width=\"100%\"  border=\"1\">"+
			"<tr>"+
			"<th colspan=\"4\">上周工作总结</th>"+
			"</tr>"+
			"<tr>"+
		    "<th width=\"20%\">工作内容</th>"+
		    "<th width=\"40%\">实施步骤</th>"+
		    "<th width=\"10%\">是否完成</th>"+
		    "<th width=\"30%\">原因</th>"+
		    "</tr>";
		
		for (int i =0; i<10; i++)
		{
			contentToSend += "<tr height=\"48\">"+
			 		"<td><textarea>"+lastNeirong[i]+"</textarea></td>"+
			 		"<td><textarea>"+lastBuzhou[i]+"</textarea></td>"+
				 	"<td><textarea>"+lastWancheng[i]+"</textarea></td>"+
				 	"<td><textarea>"+lastYuanyin[i]+"</textarea></td>"+
				 	"</tr>";
		}
		
		contentToSend += "<tr>"+
			"<th colspan=\"4\">本周工作计划</th>"+
			"</tr>"+
			"<tr>"+
		    "<th width=\"20%\">工作内容</th>"+
		    "<th width=\"40%\">实施步骤</th>"+
		    "<th colspan=\"4\" width=\"30%\">完成时间</th>"+
		    "</tr>";
		
		for (int i =0; i<10; i++)
		{
			contentToSend += "<tr height=\"48\">"+
						    "<td><textarea>"+thisNeirong[i]+"</textarea></td>"+
						    "<td><textarea>"+thisBuzhou[i]+"</textarea></td>"+
						    "<td><textarea>"+thisWancheng[i]+"</textarea></td>"+
						    "</tr>";
		}
		contentToSend += "</table>";
		
		
		
		//构造WeekMeeting对象
		WeekMeetingRecord weekMeetingRecord=new WeekMeetingRecord();
		weekMeetingRecord.setUploadDepartment(staff.getDepartment());
		weekMeetingRecord.setUploadStaff(staff);
		weekMeetingRecord.setUploadTime(date);
		weekMeetingRecord.setWeekMeetingRecordContent(contentToSend);
		
		try{
			weekMeetingService.loadWeekMeeting(weekMeetingRecord);
			return list();
		}catch(Exception e){
			return "error";
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	private List<WeekMeetingRecord> weekMeetingRecordList;
	public List<WeekMeetingRecord> getWeekMeetingRecordList() {
		return weekMeetingRecordList;
	}
	public void setWeekMeetingRecordList(
			List<WeekMeetingRecord> weekMeetingRecordList) {
		this.weekMeetingRecordList = weekMeetingRecordList;
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
			totalRecord=weekMeetingService.getTotalRecordSize();
			pageSize=weekMeetingService.getTotalPage(pageSize, totalRecord);
			Staff staff=(Staff)this.getSession().get("staff");
			//if (staff.getRole().getRoleId() == 11 || staff.getRole().getRoleId() == 12 )
			weekMeetingRecordList = weekMeetingService.getAllWeekMeetingRecord();
			//else
			//	weekMeetingRecordList=weekMeetingService.getWeekMeetingRecordsByDepartment(staff.getDepartment());
			
			//为weekMeetingRecordList中的各项加上标记
			//weekMeetingRecordList=weekMeetingService.addSignForWeekMeetingRecords(weekMeetingRecordList);
			
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
	 
	
	private int weekMeetingId;
	private WeekMeetingRecord weekMeetingRecordToRead;
	
	public void setWeekMeetingId(int weekMeetingId) {
		this.weekMeetingId = weekMeetingId;
	}
	public int getWeekMeetingId() {
		return weekMeetingId;
	}
	
	public void setWeekMeetingRecordToRead(WeekMeetingRecord weekMeetingRecordToRead) {
		this.weekMeetingRecordToRead = weekMeetingRecordToRead;
	}
	public WeekMeetingRecord getWeekMeetingRecordToRead() {
		return weekMeetingRecordToRead;
	}

		
	

	public String read(){
		try{
			weekMeetingRecordToRead = weekMeetingService.getWeekMeetingRecordById(weekMeetingId);
			return "read"; 
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

}
