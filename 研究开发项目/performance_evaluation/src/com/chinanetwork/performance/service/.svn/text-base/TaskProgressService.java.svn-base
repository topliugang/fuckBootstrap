package com.chinanetwork.performance.service;

import java.util.List;

import com.chinanetwork.performance.bean.TaskProgress;
import com.chinanetwork.performance.dao.TaskProgressDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class TaskProgressService {
	
	private TaskProgressDao taskProgressDao = new TaskProgressDao();

	public boolean check(int year, int month) throws Exception {
		// TODO Auto-generated method stub
		return taskProgressDao.check(year, month);
	}

	public void input(TaskProgress taskProgress) throws Exception {
		// TODO Auto-generated method stub
		taskProgressDao.input(taskProgress);
	}
	
	public TaskProgress getTaskProgress(int year, int month) throws Exception 
	{
		return taskProgressDao.getTaskProgress(year, month);
	}

	/**
	 * 判断任务进度是否已经被录入
	 * @return
	 * @throws Exception
	 */
	public boolean check()throws Exception{
		int year = ConfigurationDataUtil.getIntegerVlaue("which_year");
		int month = ConfigurationDataUtil.getIntegerVlaue("which_month");
		
		return taskProgressDao.check(year, month);
	}

	public List<TaskProgress> getAllTaskProgress() throws Exception {
		// TODO Auto-generated method stub
		return taskProgressDao.getAllTaskProgress();
	}
}
