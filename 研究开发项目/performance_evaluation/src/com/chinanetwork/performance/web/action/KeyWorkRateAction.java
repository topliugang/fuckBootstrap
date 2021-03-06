package com.chinanetwork.performance.web.action;

import java.util.ArrayList;
import java.util.List;
import com.chinanetwork.performance.bean.CompleteDepartmentScoreItem;
import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentKeyWork;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.service.KeyWorkService;
import com.chinanetwork.performance.service.ScoreService;

public class KeyWorkRateAction extends BaseAction{
	private ScoreService getScoreService=new ScoreService();
	private KeyWorkService keyWorkService = new KeyWorkService();

//================================================================以下用于给部门重点工作打分=================================================================
	
	private List<Department> workDepartmentList=new ArrayList<Department>();//下拉列表  拥有重点工作的部门列表

	public List<Department> getWorkDepartmentList() {
		return workDepartmentList;
	}

	public void setWorkDepartmentList(List<Department> workDepartmentList) {
		this.workDepartmentList = workDepartmentList;
	}
	
	private String departmentNameChosen;  //从下拉列表中选中的部门名称
	
	public String getDepartmentNameChosen() {
		return departmentNameChosen;
	}

	public void setDepartmentNameChosen(String departmentNameChosen) {
		this.departmentNameChosen = departmentNameChosen;
	}

	private String departmentIdChosen;
	public String getDepartmentIdChosen() {
		return departmentIdChosen;
	}
	public void setDepartmentIdChosen(String departmentIdChosen) {
		this.departmentIdChosen = departmentIdChosen;
	}

	private List<DepartmentScoreItem> departmentScoreItemList;  //在界面中显示出根据所选部门名称查找出的相应的重点工作条目列表
	
	public List<DepartmentScoreItem> getDepartmentScoreItemList() {
		return departmentScoreItemList;
	}

	public void setDepartmentScoreItemList(
			List<DepartmentScoreItem> departmentScoreItemList) {
		this.departmentScoreItemList = departmentScoreItemList;
	}

	private List<String> itemsIds;	//用于从页面接收打分的重点工作的条目ID
	
	public List<String> getItemsIds() {
		return itemsIds;
	}

	public void setItemsIds(List<String> itemsIds) {
		this.itemsIds = itemsIds;
	}

	private List<String> departmentScores;	//	用于从页面接收重点工作条目的所打的分数

	public List<String> getDepartmentScores() {
		return departmentScores;
	}

	public void setDepartmentScores(List<String> departmentScores) {
		this.departmentScores = departmentScores;
	}

	String errorInfo="";
	
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * 用于显示 对应部门的重点工作列表
	 */
	private List<DepartmentKeyWork> departmentKeyWorkList;
	
	
	public List<DepartmentKeyWork> getDepartmentKeyWorkList() {
		return departmentKeyWorkList;
	}

	public void setDepartmentKeyWorkList(
			List<DepartmentKeyWork> departmentKeyWorkList) {
		this.departmentKeyWorkList = departmentKeyWorkList;
	}

	//-----------------------------------------------------------------------------------------------------------------
	/**
	 * 功能菜单“部门重点工作得分” Action
	 */
	public String listWorkScoreDepartment(){
		try {
			//下拉列表中 当前没有“重点工作”评分记录的部门
			Staff staff=(Staff)this.getSession().get("staff");
			workDepartmentList=keyWorkService.getNoScoredKeyworkDepartment(staff.getDepartment());
			
			//显示下拉列表中第一个部门的“重点工作”
			if(workDepartmentList!=null&&workDepartmentList.size()!=0){
				departmentScoreItemList=keyWorkService.getDepartmentWorkScoreItem(String.valueOf(workDepartmentList.get(0).getDepartmentId()));
				departmentKeyWorkList=keyWorkService.getDepartmentKeyWork(String.valueOf(workDepartmentList.get(0).getDepartmentId()));
				departmentIdChosen=String.valueOf(workDepartmentList.get(0).getDepartmentId());
			}
			return "departmentWorkScoreItem";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 当用户在下拉列表中选择了一个部门，点击查询时，对应的Action
	 * @return
	 */
	public String listDepartmentWorkScoredItem(){
		try {
			//下拉列表中 当前没有“重点工作”评分记录的部门
			Staff staff=(Staff)this.getSession().get("staff");
			workDepartmentList=keyWorkService.getNoScoredKeyworkDepartment(staff.getDepartment());
			//显示出用户所选择的部门对应的“重点工作”内容和条目信息
			departmentScoreItemList=keyWorkService.getDepartmentWorkScoreItem(departmentIdChosen);
			departmentKeyWorkList=keyWorkService.getDepartmentKeyWork(departmentIdChosen);
			return "departmentWorkScoreItem";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	/**
	 * 当用户评分完毕之后，点击“提交”对应的Action
	 * @return
	 */
	public String submitDepartmentWorkScore(){
		try {
			//找到登陆用户，他所属的部门作为打分部门
			Staff staff=(Staff)this.getSession().get("staff");
			if(getScoreService.submitDepartmentWorkScore(staff, itemsIds, departmentScores)){
				//如果打分过程成功
				//填写修改次数
				getScoreService.setModifyKeyWorkScoreTimes("2", staff.getDepartment().getDepartmentId(),Integer.parseInt( departmentIdChosen), "重点工作");
				//下拉列表  当前没有“重点工作”评分记录的部门
				workDepartmentList=keyWorkService.getNoScoredKeyworkDepartment(staff.getDepartment());
				//显示下拉列表中第一个部门对应的“重点工作”
				if(workDepartmentList!=null&&workDepartmentList.size()!=0){
					departmentScoreItemList=keyWorkService.getDepartmentWorkScoreItem(String.valueOf(workDepartmentList.get(0).getDepartmentId()));
					departmentKeyWorkList=keyWorkService.getDepartmentKeyWork(String.valueOf(workDepartmentList.get(0).getDepartmentId()));
					departmentIdChosen=String.valueOf(workDepartmentList.get(0).getDepartmentId());
				}
				return "departmentWorkScoreItem";
			}else{
				return "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

//===================================================================以下用于给重点工作部门改分==============================================================
	private List<Department> beScoredWorkDepartmentList;	//下拉列表  重点工作已经被打分完毕的部门列表
	
	public List<Department> getBeScoredWorkDepartmentList() {
		return beScoredWorkDepartmentList;
	}

	public void setBeScoredWorkDepartmentList(
			List<Department> beScoredWorkDepartmentList) {
		this.beScoredWorkDepartmentList = beScoredWorkDepartmentList;
	}

	private String beScoredDepartmentNameChosen;       //接收下拉列表所选的部门名称
	
	public String getBeScoredDepartmentNameChosen() {
		return beScoredDepartmentNameChosen;
	}

	public void setBeScoredDepartmentNameChosen(String beScoredDepartmentNameChosen) {
		this.beScoredDepartmentNameChosen = beScoredDepartmentNameChosen;
	}
	private String beScoredDepartmentIdChosen;
	public String getBeScoredDepartmentIdChosen() {
		return beScoredDepartmentIdChosen;
	}
	public void setBeScoredDepartmentIdChosen(String beScoredDepartmentIdChosen) {
		this.beScoredDepartmentIdChosen = beScoredDepartmentIdChosen;
	}

	private List<DepartmentScoreItem> departmentWorkScoreItemList=new ArrayList<DepartmentScoreItem>();//用于存储根据部门名称查询出的相对应的条目列表
	private List<Float> itemScoreList=new ArrayList<Float>();//用于存储根据部门名称查询出的性对应的条目的分数列表
	public List<DepartmentScoreItem> getDepartmentWorkScoreItemList() {
		return departmentWorkScoreItemList;
	}

	public void setDepartmentWorkScoreItemList(
			List<DepartmentScoreItem> departmentWorkScoreItemList) {
		this.departmentWorkScoreItemList = departmentWorkScoreItemList;
	}

	public List<Float> getItemScoreList() {
		return itemScoreList;
	}

	public void setItemScoreList(List<Float> itemScoreList) {
		this.itemScoreList = itemScoreList;
	}
	
	private List<CompleteDepartmentScoreItem> completeDepartmentScoreItemList=new ArrayList<CompleteDepartmentScoreItem>();//用于存储根据部门名称查询出的相对应的完整的条目列表（包括每一条条目的得分）

	public List<CompleteDepartmentScoreItem> getCompleteDepartmentScoreItemList() {
		return completeDepartmentScoreItemList;
	}

	public void setCompleteDepartmentScoreItemList(
			List<CompleteDepartmentScoreItem> completeDepartmentScoreItemList) {
		this.completeDepartmentScoreItemList = completeDepartmentScoreItemList;
	}
	
	private List<String> scoreItemIdList;				//用于从页面接收修改分数对应的条目列表
	private List<String> departmentScoreList;       //用于从页面接收修改的分数列表

	public List<String> getScoreItemIdList() {
		return scoreItemIdList;
	}

	public void setScoreItemIdList(List<String> scoreItemIdList) {
		this.scoreItemIdList = scoreItemIdList;
	}

	public List<String> getDepartmentScoreList() {
		return departmentScoreList;
	}

	public void setDepartmentScoreList(List<String> departmentScoreList) {
		this.departmentScoreList = departmentScoreList;
	}
	private String modifyTime;
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	//--------------------------------------------------------------------------------------------------------------
	/**
	 * 功能菜单“部门重点工作得分修改”对应的Action
	 */
	public String modifyDepartmentWorkScore(){
		try {
			//下拉列表中，目前拥有“重点工作”的评分记录的部门
			beScoredWorkDepartmentList=getScoreService.getBeScoredDepartmentList();
			//显示下拉列表中，第一个部门对应的“重点工作”评分记录
			if(beScoredWorkDepartmentList!=null&&beScoredWorkDepartmentList.size()!=0){
				completeDepartmentScoreItemList=getScoreService.getDepartmentWorkBeScoredItem(String.valueOf(beScoredWorkDepartmentList.get(0).getDepartmentId()));
				departmentKeyWorkList=keyWorkService.getDepartmentKeyWork(String.valueOf(beScoredWorkDepartmentList.get(0).getDepartmentId()));
				beScoredDepartmentIdChosen=String.valueOf(beScoredWorkDepartmentList.get(0).getDepartmentId());
			}
			return "departmentWorkScoreModify";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	/**
	 * 当用户在下拉列表中选择部门名称之后,对应的Action
	 * @return
	 */
	public String modifyDepartmentWorkScoreItem(){
		try {
			/*departmentWorkScoreItemList=getScoreService.getDepartmentWorkBeScoredItem(beScoredDepartmentNameChosen, itemScoreList);
			for(int i=0;i<departmentWorkScoreItemList.size();i++){
				CompleteDepartmentScoreItem completeDepartmentScoreItem=new CompleteDepartmentScoreItem(departmentWorkScoreItemList.get(i),itemScoreList.get(i));
				completeDepartmentScoreItemList.add(completeDepartmentScoreItem);
			}*/
			//根据用户选择的部门，找到对应的“重点工作”评分记录
			completeDepartmentScoreItemList=getScoreService.getDepartmentWorkBeScoredItem(beScoredDepartmentIdChosen);
			departmentKeyWorkList=keyWorkService.getDepartmentKeyWork(beScoredDepartmentIdChosen);//completeDepartmentScoreItemList.get(0).getDepartmentScoreItem().getDepartment().getDepartmentName());
			//下拉列表，下拉列表中，目前拥有“重点工作”的评分记录的部门
			beScoredWorkDepartmentList=getScoreService.getBeScoredDepartmentList();
			return"departmentWorkScoreModify";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	/**
	 * 当用户修改分数完毕之后，点击“提交”对应的Action
	 * @return
	 */
	public String modifyDepartmentWorkScoreRecord(){
		try {
			System.out.println("提交参数：");
			System.out.println("修改次数："+modifyTime);
			System.out.println("页面显示的部门号："+beScoredDepartmentIdChosen);
			System.out.println("修改条目ID列表："+scoreItemIdList);
			System.out.println("修改条目分数列表："+departmentScoreList);
			//找到登陆用户，他所属的部门作为打分部门
			Staff staff=(Staff)this.getSession().get("staff");
			//修改“重点工作”的得分
			getScoreService.modifyDepartmentWorkScoreRecord(staff,scoreItemIdList,departmentScoreList);

			//下拉列表中，目前拥有“重点工作”的评分记录的部门
			beScoredWorkDepartmentList=getScoreService.getBeScoredDepartmentList();
			/*departmentWorkScoreItemList=getScoreService.getDepartmentWorkBeScoredItem(beScoredDepartmentNameChosen, itemScoreList);
			for(int i=0;i<departmentWorkScoreItemList.size();i++){
				CompleteDepartmentScoreItem completeDepartmentScoreItem=new CompleteDepartmentScoreItem(departmentWorkScoreItemList.get(i),itemScoreList.get(i));
				completeDepartmentScoreItemList.add(completeDepartmentScoreItem);
			}*/
			//显示修改之后的结果
			completeDepartmentScoreItemList=getScoreService.getDepartmentWorkBeScoredItem(beScoredDepartmentIdChosen);
			departmentKeyWorkList=keyWorkService.getDepartmentKeyWork(beScoredDepartmentIdChosen);
			return "departmentWorkScoreModify";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
