package com.chinanetwork.performance.bean;

import java.util.Date;

/**
 * 
 * @author Administrator
 *
 *	用于封装导出到excel表格中的信息
 *	员工工号，姓名，性别，所属部门，绩效年份，绩效月份，绩效成绩，描述，工资基数，绩效工资，以及日期
 */
public class StaffScoreExcel {
	private Staff staff;
	private int year;
	private int month;
	private float staffScore;
	private float departmentScore;
	private String instruction;
	private float salary;
	private float performanceSalary;
	private Date date;
	
	public float getStaffScore() {
		return staffScore;
	}

	public void setStaffScore(float staffScore) {
		this.staffScore = staffScore;
	}

	public float getDepartmentScore() {
		return departmentScore;
	}

	public void setDepartmentScore(float departmentScore) {
		this.departmentScore = departmentScore;
	}

	public StaffScoreExcel(){}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}



	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(float performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
