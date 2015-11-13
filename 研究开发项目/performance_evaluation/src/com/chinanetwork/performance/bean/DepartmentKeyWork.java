package com.chinanetwork.performance.bean;
/**
 * 部门阶段性重点工作类
 * @author Administrator
 *
 */
public class DepartmentKeyWork {
	private int keyWorkId;				//重点工作ID
	private Department department;		//所属部门ID
	private String keyWorkContent;		//重点工作内容
	private int startYear;				//开始年份
	private int startMonth;				//开始月份
	private int endYear;				//结束年份
	private int endMonth;				//结束月份
	public int getKeyWorkId() {
		return keyWorkId;
	}
	public void setKeyWorkId(int keyWorkId) {
		this.keyWorkId = keyWorkId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getKeyWorkContent() {
		return keyWorkContent;
	}
	public void setKeyWorkContent(String keyWorkContent) {
		this.keyWorkContent = keyWorkContent;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	
	
	public DepartmentKeyWork(){}
	
	public DepartmentKeyWork(int keyWorkId,Department department,String keyWorkContent,int startYear,int startMonth,int endYear,int endMonth){
		this.keyWorkId=keyWorkId;
		this.department=department;
		this.keyWorkContent=keyWorkContent;
		this.startYear=startYear;
		this.startMonth=startMonth;
		this.endYear=endYear;
		this.endMonth=endMonth;
	}
}
