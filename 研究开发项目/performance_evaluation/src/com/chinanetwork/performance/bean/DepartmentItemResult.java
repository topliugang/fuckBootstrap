package com.chinanetwork.performance.bean;

public class DepartmentItemResult {
	private int departmentScoreItemId;
	private float average;
	private String comment;
	
	public int getDepartmentScoreItemId() {
		return departmentScoreItemId;
	}
	public void setDepartmentScoreItemId(int departmentScoreItemId) {
		this.departmentScoreItemId = departmentScoreItemId;
	}
	public float getAverage() {
		return average;
	}
	public void setAverage(float average) {
		this.average = average;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public DepartmentItemResult(){}
	public DepartmentItemResult(float average,String comment){
		this.average=average;
		this.comment=comment;
	}
}
