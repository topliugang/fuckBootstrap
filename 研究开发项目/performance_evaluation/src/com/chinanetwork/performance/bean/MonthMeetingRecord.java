package com.chinanetwork.performance.bean;

import java.util.Date;
/**
 * 月例会上传记录类
 * 	用于每月月底月例会的上传
 * @author Administrator
 *
 */
public class MonthMeetingRecord {
	
	private int monthMeetingRecordId;
	private String monthMeetingRecordContent;
	private Date uploadTime;
	private Staff uploadStaff;
	private Department uploadDepartment;
	
	public MonthMeetingRecord(){}

	public int getMonthMeetingRecordId() {
		return monthMeetingRecordId;
	}

	public void setMonthMeetingRecordId(int monthMeetingRecordId) {
		this.monthMeetingRecordId = monthMeetingRecordId;
	}

	public String getMonthMeetingRecordContent() {
		return monthMeetingRecordContent;
	}

	public void setMonthMeetingRecordContent(String monthMeetingRecordContent) {
		this.monthMeetingRecordContent = monthMeetingRecordContent;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Staff getUploadStaff() {
		return uploadStaff;
	}

	public void setUploadStaff(Staff uploadStaff) {
		this.uploadStaff = uploadStaff;
	}

	public Department getUploadDepartment() {
		return uploadDepartment;
	}

	public void setUploadDepartment(Department uploadDepartment) {
		this.uploadDepartment = uploadDepartment;
	}

	public MonthMeetingRecord(int monthMeetingRecordId,
			String monthMeetingRecordContent, Date uploadTime,
			Staff uploadStaff, Department uploadDepartment) {
		super();
		this.monthMeetingRecordId = monthMeetingRecordId;
		this.monthMeetingRecordContent = monthMeetingRecordContent;
		this.uploadTime = uploadTime;
		this.uploadStaff = uploadStaff;
		this.uploadDepartment = uploadDepartment;
	}
	
	
}
