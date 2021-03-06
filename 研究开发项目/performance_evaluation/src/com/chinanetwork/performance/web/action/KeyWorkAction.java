package com.chinanetwork.performance.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentKeyWork;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.service.KeyWorkService;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

/**
 * 提供对 “添加重点工作” “重点工作管理”等功能的服务
 * @author Administrator
 *
 */
public class KeyWorkAction extends BaseAction{

	private KeyWorkService departmentKeyWorkService=new KeyWorkService();
	private List<DepartmentKeyWork> departmentKeyWorkList=new ArrayList<DepartmentKeyWork>();	//重点工作列表

	public List<DepartmentKeyWork> getDepartmentKeyWorkList() {
		return departmentKeyWorkList;
	}

	public void setDepartmentKeyWorkList(
			List<DepartmentKeyWork> departmentKeyWorkList) {
		this.departmentKeyWorkList = departmentKeyWorkList;
	}
	
	//---------------------------------------------------------------------分页代码开始
	private String open="false";		//开关 ----- 是否显示分页
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	private int pageNo=1;  //记录当前页数
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	private int totalPage;		//总页数
	private int totalRecorder;	//总记录数
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecorder() {
		return totalRecorder;
	}

	public void setTotalRecorder(int totalRecorder) {
		this.totalRecorder = totalRecorder;
	}
	
	private int pageSize;	//每页显示的记录数
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private String previous="false";
	private String next="false";
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
	public String getMyDepartmentKeyWorkList(){
		next="false";
		previous="false";
		try {
			Map<String,Integer> dataMap=departmentKeyWorkService.getTotalData();
			totalPage=dataMap.get("countPage");
			totalRecorder=dataMap.get("countRecored");
			String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
			pageSize=Integer.parseInt(pageSizeStr);
			
			if(pageNo>1){
				previous="true";
			}
			System.out.println(pageNo+","+totalPage);
			if(pageNo<totalPage){	
				next="true";
			}
			departmentKeyWorkList=departmentKeyWorkService.getLoginStaffKeyWorkList(pageNo);
			return "toListDepartmentKeyWork";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	//---------------------------------------------------------------------分页代码结束
	private String departmentName;	//申请部门名称
	private int keyWorkId;			//申请重点工作编号
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getKeyWorkId() {
		return keyWorkId;
	}

	public void setKeyWorkId(int keyWorkId) {
		this.keyWorkId = keyWorkId;
	}

	public String toAddDepartmentKeyWork(){
		Staff staff = (Staff)this.getSession().get("staff");
		try{
			//获取申请部门
			Department department=departmentKeyWorkService.getDepartment(staff);
			departmentName=department.getDepartmentName();
			//获取申请重点工作编号
			keyWorkId=departmentKeyWorkService.getMaxKeyWorkId();
			return "toAddDepartmentKeyWork";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	private String keyWorkContent;
	private String startTime;
	private String endTime;
	private boolean mark=true;	//验证标记
	private String markInfo;//验证信息
	public String getKeyWorkContent() {
		return keyWorkContent;
	}

	public void setKeyWorkContent(String keyWorkContent) {
		this.keyWorkContent = keyWorkContent;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	public String getMarkInfo() {
		return markInfo;
	}

	public void setMarkInfo(String markInfo) {
		this.markInfo = markInfo;
	}

	public String addDepartmentKeyWork(){
		Staff staff = (Staff)this.getSession().get("staff");
		try {
			String startYear="";
			String startMonth="";
			String endYear="";
			String endMonth="";
			
			String[] strs=startTime.split("-");
			startYear=strs[0];
			startMonth=strs[1];
			strs=endTime.split("-");
			endYear=strs[0];
			endMonth=strs[1];
			
			departmentKeyWorkService.addDepartmentKeyWork(keyWorkId, staff, keyWorkContent, startYear, startMonth, endYear, endMonth);
			return "toDepartmentKeyWorkList";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
}
