package com.chinanetwork.performance.web.action;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

/**
 * 作文件下载测试
 * @author Administrator
 *
 */
public class DownLoadAction {

	private InputStream downLoad;
	private String fileName;
	
	public InputStream getDownLoad() {
		return downLoad;
	}
	public void setDownLoad(InputStream downLoad) {
		this.downLoad = downLoad;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String execute(){
		
		System.out.println("执行开始");
		String downLoadFileName=File.separator+"upload"+File.separator+"File000001.dat";
		System.out.println("路径："+downLoadFileName);
		fileName="excel.xls";
		
		downLoad=ServletActionContext.getServletContext().getResourceAsStream(downLoadFileName);
		System.out.println("流"+downLoad);
		System.out.println("执行结束");
		return "success";
	}
}
