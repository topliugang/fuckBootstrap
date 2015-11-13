package com.chinanetwork.performance.bean;

import java.util.Date;

/**
 * 培训计划类
 * 	用于每月的月底培训计划的上传
 * @author Administrator
 *
 */
public class TrainingPlan {
	private int trainingPlanId;
	private String trainingPlanContent;
	private Staff uploadStaff;
	private Department uploadDepartment;
	private Date uploadTime;
	
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public TrainingPlan(){}

	public int getTrainingPlanId() {
		return trainingPlanId;
	}

	public void setTrainingPlanId(int trainingPlanId) {
		this.trainingPlanId = trainingPlanId;
	}

	public String getTrainingPlanContent() {
		return trainingPlanContent;
	}

	public void setTrainingPlanContent(String trainingPlanContent) {
		this.trainingPlanContent = trainingPlanContent;
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

	public TrainingPlan(int trainingPlanId, String trainingPlanContent,
			Staff uploadStaff, Department uploadDepartment, Date uploadTime) {
		super();
		this.trainingPlanId = trainingPlanId;
		this.trainingPlanContent = trainingPlanContent;
		this.uploadStaff = uploadStaff;
		this.uploadDepartment = uploadDepartment;
		this.uploadTime = uploadTime;
	}
}
