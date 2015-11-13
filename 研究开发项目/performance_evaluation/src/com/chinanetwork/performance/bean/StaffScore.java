package com.chinanetwork.performance.bean;

import java.util.Date;

/**
 * 该类主要用来封装员工最后得分的信息
 * @author Administrator
 *
 * * 没有id 很蛋疼  暂且尝试一下
 * instruction 在数据库里叫 annotation 也很蛋疼  懒得改
 */
public class StaffScore {

	private Staff staff;		//部门员工信息
	private float totalScore;	//员工对应的绩效得分
	private String instruction;	//针对分数的一个说明
	private Date date;
	private int year;
	private int month;

	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public StaffScore(Staff staff, float totalScore, String instruction,
			Date date, int year, int month) {
		super();
		this.staff = staff;
		this.totalScore = totalScore;
		this.instruction = instruction;
		this.date = date;
		this.year = year;
		this.month = month;
	}
	public StaffScore() {
		super();
	}
	
	public StaffScore(Staff staff, float totalScore) {
		super();
		this.staff = staff;
		this.totalScore = totalScore;
	}
	
}
