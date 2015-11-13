package com.chinanetwork.performance.web.action;

import java.sql.Date;
import java.util.List;

import com.chinanetwork.performance.bean.TaskProgress;
import com.chinanetwork.performance.service.TaskProgressService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class TaskProgressAction {

	public String execute() {
		return "success";
	}

	private TaskProgressService taskProgressService = new TaskProgressService();

	private List<TaskProgress> taskProgressessss; 
	private TaskProgress taskProgress;
	private boolean canInput;
	
	public boolean isCanInput() {
		return canInput;
	}
	public void setCanInput(boolean canInput) {
		this.canInput = canInput;
	}
	public List<TaskProgress> getTaskProgressessss() {
		return taskProgressessss;
	}
	public void setTaskProgressessss(List<TaskProgress> taskProgressessss) {
		this.taskProgressessss = taskProgressessss;
	}
	public TaskProgress getTaskProgress() {
		return taskProgress;
	}
	public void setTaskProgress(TaskProgress taskProgress) {
		this.taskProgress = taskProgress;
	}
	
	
	public String list() throws Exception {
		taskProgressessss = taskProgressService.getAllTaskProgress();

		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		canInput = taskProgressService.check(year, month);
		return "list";
	}
	
	
	
	private int whichyear;
	private int whichmonth;
	private float progress;



	public int getWhichyear() {
		return whichyear;
	}
	public void setWhichyear(int whichyear) {
		this.whichyear = whichyear;
	}
	public int getWhichmonth() {
		return whichmonth;
	}
	public void setWhichmonth(int whichmonth) {
		this.whichmonth = whichmonth;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	
	public String input() throws Exception {
		//int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		//int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		TaskProgress taskProgress = new TaskProgress();
		taskProgress.setDate(new Date(System.currentTimeMillis()));
		taskProgress.setWhichYear(whichyear);
		taskProgress.setWhichMonth(whichmonth);
		taskProgress.setTaskProgress(progress);
		taskProgressService.input(taskProgress);
		return "input";
	}
	
	public String toInput()
	{
		return "toInput";
	}

	
	public String goBack(){
		return "goBack";
	}
}
