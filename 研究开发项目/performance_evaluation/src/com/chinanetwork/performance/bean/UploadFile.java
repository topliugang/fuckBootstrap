package com.chinanetwork.performance.bean;

import java.sql.Timestamp;

/**
 * 上传文件类
 * @author Administrator
 *
 */
public class UploadFile {
	private String fileId;				//上传文件ID
	private String fileName;			//上传文件名称
	private String fileType;			//上传文件类型
	private long fileSize;				//上传文件大小
	private Timestamp uploadTime;		//上传文件时间
	private Staff creator;				//上传文件的创建者
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Staff getCreator() {
		return creator;
	}
	public void setCreator(Staff creator) {
		this.creator = creator;
	}
	
	public UploadFile(){}
}
