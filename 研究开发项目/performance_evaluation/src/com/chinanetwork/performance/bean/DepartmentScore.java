package com.chinanetwork.performance.bean;

import java.util.Date;

/**
 * 部门得分类，对应数据库中的部门得分表
 * @author Administrator
 * 没有id 很蛋疼  暂且尝试一下
 * instruction 在数据库里叫 annotation 也很蛋疼  懒得改
 */
public class DepartmentScore {

	private Department department;
	private float score;
	private String instruction;
	private Date date;
	private int year;	
	private int month;
	
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DepartmentScore(Department department, float score,
			String instruction, Date date, int year, int month) {
		super();
		this.department = department;
		this.score = score;
		this.instruction = instruction;
		this.date = date;
		this.year = year;
		this.month = month;
	}
	public DepartmentScore() {
		super();
	}
	
	public DepartmentScore(Department department, float score)
	{
		super();
		this.department = department;
		this.score = score;
	}
	
	

	
	
}
