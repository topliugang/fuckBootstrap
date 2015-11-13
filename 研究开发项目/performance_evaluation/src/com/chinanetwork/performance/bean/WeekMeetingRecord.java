package com.chinanetwork.performance.bean;

import java.util.Date;
/**
 * 周例会上传记录类
 * 	用于每周三的例会记录的上传
 * @author Administrator
 *
 */
public class WeekMeetingRecord {
	private int weekMeetingRecordId;
	private String weekMeetingRecordContent;
	private Date uploadTime;
	private Staff uploadStaff;
	private Department uploadDepartment;
	

	public WeekMeetingRecord(){}

	public int getWeekMeetingRecordId() {
		return weekMeetingRecordId;
	}

	public void setWeekMeetingRecordId(int weekMeetingRecordId) {
		this.weekMeetingRecordId = weekMeetingRecordId;
	}

	public String getWeekMeetingRecordContent() {
		return weekMeetingRecordContent;
	}

	public void setWeekMeetingRecordContent(String weekMeetingRecordContent) {
		this.weekMeetingRecordContent = weekMeetingRecordContent;
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

	public WeekMeetingRecord(int weekMeetingRecordId,
			String weekMeetingRecordContent, Date uploadTime,
			Staff uploadStaff, Department uploadDepartment) {
		super();
		this.weekMeetingRecordId = weekMeetingRecordId;
		this.weekMeetingRecordContent = weekMeetingRecordContent;
		this.uploadTime = uploadTime;
		this.uploadStaff = uploadStaff;
		this.uploadDepartment = uploadDepartment;
	}
	

}
