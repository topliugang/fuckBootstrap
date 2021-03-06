package com.chinanetwork.performance.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.chinanetwork.performance.bean.Department;
import com.chinanetwork.performance.bean.DepartmentScoreItem;
import com.chinanetwork.performance.bean.DepartmentScoreRecord;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.StaffScoreItem;
import com.chinanetwork.performance.bean.StaffScoreRecord;
import com.chinanetwork.performance.dao.ScoreDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;
/*
 * 
 * 各种打分
 */
public class RateService {

	ScoreDao scoreDao = new ScoreDao();

	//返回需要打分的部门
	public List<Department> getNoScoreDepartment(Department departmentFrom)
			throws Exception {
		if(ConfigurationDataUtil.getStringValue("start_score").equals("false"))
			return null;
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		return scoreDao.getNoScoreDepartment(departmentFrom, year, month, 1, 2);
	}

	
	public List<DepartmentScoreItem> getDepartmentScoreItem(
			Department departmentTo, Department departmentFrom)
			throws Exception {

		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		return scoreDao.getDepartmentScoreItem(departmentTo, departmentFrom,
				year, month, 1, 2, 7);
	}



	public void departmentScoreByItemId(List<String> itemIds,
			List<String> departmentScores,
			List<String> departmentScoreComments, int departmentFromId,
			int departmentToId, int ownerId) throws Exception {
		scoreDao.departmentScoreByItemId(itemIds, departmentScores,
				departmentScoreComments, departmentFromId, departmentToId,
				ownerId);
	}

	public List<Department> getScoreDepartment(Department departmentFrom)
			throws Exception {

		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		return scoreDao.getScoreDepartment(departmentFrom, year, month, 1, 2, 7);
	}

	public List<DepartmentScoreRecord> getDepartmentScoreRecord(
			Department departmentTo, Department departmentFrom, int year,
			int month, int... categorys) throws Exception {
		return scoreDao.getDepartmentScoreRecord(departmentTo, departmentFrom,
				year, month, categorys);
	}

	public List<Staff> getNoScoredStaff(Staff staffFrom) throws Exception {
		// TODO Auto-generated method stub
		if(ConfigurationDataUtil.getStringValue("start_score").equals("false"))
			return null;
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		return scoreDao.getNoScoredStaff(staffFrom, year, month);
	}

	public List<Staff> getScoreStaff(Staff staffFrom) throws Exception {
		// TODO Auto-generated method stub
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		return scoreDao.getScoreStaff(staffFrom, year, month);
	}

	public List<StaffScoreRecord> getStaffScoredRecord(Staff staffTo,
			Staff staffFrom, int year, int month) throws SQLException,
			Exception {
		// TODO Auto-generated method stub
		return scoreDao.getStaffScoredRecord(staffTo, staffFrom, year, month);
	}

	public void staffScoreByItemIdAndStaffId(List<String> itemsIds,
			List<String> staffScores, int staffIdTo, int staffFromId,
			int ownerId) throws Exception {
		scoreDao.staffScoreByItemIdAndStaffId(itemsIds, staffScores, staffIdTo,
				staffFromId, ownerId);
	}

	public List<StaffScoreItem> getStaffScoreItemByStaff(Staff staff)
			throws Exception {
		// TODO Auto-generated method stub
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		return scoreDao.getStaffScoreItemByStaff(staff, year, month);
	}

	public boolean modifyDepartmentScore(List<Integer> recordIds,
			List<String> departmentScores, List<String> departmentScoreComments)
			throws Exception {
		boolean isSuccess = false;
		Date date = new Date(System.currentTimeMillis());
		try {
			for (int i = 0; i < recordIds.size(); i++) {
				isSuccess = scoreDao.updateDepartmentScoreRecord(recordIds
						.get(i), Float.parseFloat(departmentScores.get(i)),
						departmentScoreComments.get(i), date);
				if (!isSuccess) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean modifyStaffScore(List<Integer> recordid,
			List<String> staffScores) throws Exception {
		boolean isSuccess = false;
		Date date = new Date(System.currentTimeMillis());
		try {
			for (int i = 0; i < recordid.size(); i++) {
				isSuccess = scoreDao.updateStaffScoreRecord(recordid.get(i),
						Float.parseFloat(staffScores.get(i)), date);
				if (!isSuccess) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	/*
	 * public boolean modifyModTimes(String modTimes,int fDepId,int
	 * tDepId)throws Exception{ boolean isSuccess=false; try{
	 * isSuccess=scoreDao.
	 * updateDepartmentScoreRevocation(Integer.parseInt(modTimes), fDepId,
	 * tDepId); }catch(Exception e){ e.printStackTrace(); isSuccess=false; }
	 * return isSuccess; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public boolean modifyModStaTimes(String modTimes,int fStaId,int
	 * tStaId)throws Exception{ boolean isSuccess=false; try{
	 * isSuccess=scoreDao.updateStaffScoreRevocation(Integer.parseInt(modTimes),
	 * fStaId, tStaId); }catch(Exception e){ e.printStackTrace();
	 * isSuccess=false; } return isSuccess; }
	 */
}
