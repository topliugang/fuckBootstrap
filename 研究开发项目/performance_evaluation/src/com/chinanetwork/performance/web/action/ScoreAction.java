package com.chinanetwork.performance.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScore;
import com.chinanetwork.performance.bean.StaffScore;
import com.chinanetwork.performance.bean.StaffScoreExcel;
import com.chinanetwork.performance.service.ScoreService;
import com.chinanetwork.performance.service.StaffManageService;
import com.chinanetwork.performance.service.StatisticalQueryService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
import com.chinanetwork.performance.util.MathUtil;

/**
 * 算分action
 * @author Administrator
 *
 */
public class ScoreAction {
	
	private ScoreService getScoreService=new ScoreService();
	
	private List<String> messageList;		//负责在失败页面，显示部门评分未完成的细节
	private List<DepartmentScore> departmentScoreList;	//算分结束后，显示页面中显示所有互评部门的分数
	
	public List<DepartmentScore> getDepartmentScoreList() {
		return departmentScoreList;
	}

	public void setDepartmentScoreList(List<DepartmentScore> departmentScoreList) {
		this.departmentScoreList = departmentScoreList;
	}

	private String error;					//负责在失败页面显示信息
	
	
	
	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private boolean scoringMarker;	//评分标记	用来标记评分过程结束与否
	public boolean isScoringMarker() {
		return scoringMarker;
	}
	public void setScoringMarker(boolean scoringMarker) {
		this.scoringMarker = scoringMarker;
	}

/**
 * 部门互评计算分数
 * @return
 */
	public String calculateDepartmentScore(){
		try {
				//给部门算分，并插入数据库中
				boolean isSuccess=getScoreService.calculateDepartmentScore();
				//如果算分成功
				if(isSuccess){
					return "success";
					//return "toDepartmentScoreQuery";
				}
				//如果算分不成功,给查询页面提示“部门互评算分”不成功
				else{
					error="计算分数不成功";
					return "Failure";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error=e.getMessage();
			return "Failure";
		}
	}
	
	/**
	 * 当用户在菜单中点击“部门得分的时候”，对应的Action
	 * 		负责确定，当前的评分过程是否结束，并且提示用户。
	 * @return
	 */
	public String confirmAccomplishDepartmentScore(){
		try {
			//是否已近算过分了
			if(getScoreService.confirmOnlyCalculateDepartmentScore()){
				//判断部门互评打分是否结束
				messageList=getScoreService.confirmAccomplishDepartmentScore();
				
				//重点工作只是作为部门互评的一部分，所以对于其检测，不用独立出来
				
				
			}else{
				messageList=new ArrayList<String>();
				messageList.add("部门绩效已经计算过了");
			}
			//如果评分过程没有结束
			if(messageList.size()!=0){
				//将评分标记变成false；
				scoringMarker=false;
			}
			//评分过程结束
			else{
				//将评分标记变成true
				scoringMarker=true;
			}
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 当点击“部门得分”，如果评分过程还没有结束，那么点击返回对应的action
	 * @return
	 */
	public String goBack(){
		return "goBackWelcome";
	}
	
//======================================================================以下归为员工计算分数=================================================================
	/**
	 * 判断员工打分过程有没有结束	&& 判断是否已经计算过了
	 */
	public String confirmAccomplishStaffScore(){
		try {
			if(getScoreService.confirmOnlyCalculateStaffScore()){
				//判断“任务进度是否被录入”
				if(getScoreService.confirmTaskProgressInput()){
					//判断部门分数是否已经计算出来
					if(!getScoreService.confirmOnlyCalculateDepartmentScore()){
						//判断员工打分是否结束
						messageList=getScoreService.confirmAccomplishStaffScore();
					}else{
						messageList=new ArrayList<String>();
						messageList.add("部门分数还没有被计算出来");
					}
				}else{
					messageList=new ArrayList<String>();
					messageList.add("任务进度还没有被录入");
				}
			}else{
				messageList=new ArrayList<String>();
				messageList.add("员工绩效已经计算过了");
			}
			
			//如果评分过程没有结束
			if(messageList.size()!=0){
				//将评分标记变成false；
				scoringMarker=false;
			}
			//评分过程结束
			else{
				//将评分标记变成true
				ConfigurationDataUtil.updateValue("start_score", "false");
			//	ConfigurationDataUtil.updateValue("which_month", "00");
			//	ConfigurationDataUtil.updateValue("which_year", "0000");
				scoringMarker=true;
			}
			return "toConfirmStaffScore";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
//======================================================================================================================================================
	
	/**
	 * 用来给员工计算分数
	 * @return
	 */
	public String calculateStaffScore(){
		try {
			boolean success=getScoreService.calculateStaffScore();
			if(success){
				return "toStaffScoreExcel";
			}else{
				return "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	
	private InputStream is;		//导出excel表格的输入流
	
	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}
	

	
	public String toCalculateDepartmentScore(){
		return "toCalculateDepartmentScore";
	}
	public String toCalculateStaffScore(){
		return "toCalculateStaffScore";
	}
}