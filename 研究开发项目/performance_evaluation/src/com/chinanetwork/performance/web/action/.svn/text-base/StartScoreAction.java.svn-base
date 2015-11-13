package com.chinanetwork.performance.web.action;

import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class StartScoreAction {

	private String result;
	private boolean isStarted;


	

	private int whichYear;
	private int whichMonth;
	private String startDate;
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getWhichYear() {
		return whichYear;
	}

	public void setWhichYear(int whichYear) {
		this.whichYear = whichYear;
	}

	public int getWhichMonth() {
		return whichMonth;
	}

	public void setWhichMonth(int whichMonth) {
		this.whichMonth = whichMonth;
	}
	



	
	public String list() throws Exception {
		if(ConfigurationDataUtil.getStringValue("start_score").equals("true"))
		{
			this.result = "true";
			this.isStarted = true;
		}
		else if(ConfigurationDataUtil.getStringValue("start_score").equals("false"))
		{
			this.result = "false";
			this.isStarted = false;
		}
		
		this.whichYear = ConfigurationDataUtil.getIntegerVlaue("which_year");
		this.whichMonth = ConfigurationDataUtil.getIntegerVlaue("which_month");
		this.startDate = ConfigurationDataUtil.getStringValue("start_time");
		this.endDate = ConfigurationDataUtil.getStringValue("end_time");
		


		return "list";
	}

	public String input() throws Exception {

		ConfigurationDataUtil.updateValue("which_year", String.valueOf(this.whichYear));
		ConfigurationDataUtil.updateValue("which_month", String.valueOf(this.whichMonth));
		ConfigurationDataUtil.updateValue("start_time", startDate);
		ConfigurationDataUtil.updateValue("end_time", endDate);
		ConfigurationDataUtil.updateValue("start_score", "true");
		ConfigurationDataUtil.updateValue("department_score_record_modify", "true");
		ConfigurationDataUtil.updateValue("staff_score_record_modify", "true");
		ConfigurationDataUtil.updateValue("department_score_modify", "true");
		ConfigurationDataUtil.updateValue("staff_score_modify", "true");
		return "input";
	}
	
	public String goBack(){
		return "goBack";
	}

}
