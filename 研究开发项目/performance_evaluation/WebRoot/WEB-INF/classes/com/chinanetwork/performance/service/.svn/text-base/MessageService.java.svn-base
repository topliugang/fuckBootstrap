package com.chinanetwork.performance.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chinanetwork.performance.bean.Message;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.UploadFile;
import com.chinanetwork.performance.dao.DepartmentManageDao;
import com.chinanetwork.performance.dao.MessageDao;

public class MessageService {
	
	private MessageDao messageDao = new MessageDao();
	
	
	public List<Staff> mergeStaff(List<Integer> departments, List<Integer> staffs) throws Exception
	{
		List<Staff> receivers = null;
		Set<Integer> receiversets = null;
		if(departments != null)
		{
			if(receiversets == null)
				receiversets = new HashSet<Integer>();
			
			DepartmentManageDao departmentManageDao = new DepartmentManageDao();
			for( int i=0; i<departments.size(); i++ )
			{
				List<Integer> tmpstaffs = departmentManageDao.getStaffIdsByDepartmentId(departments.get(i));
				if(tmpstaffs != null)
					receiversets.addAll(tmpstaffs);
			}
		}
		if(staffs != null)
		{
			if(receiversets == null)
				receiversets = new HashSet<Integer>();
			receiversets.addAll(staffs);
		}
		
		if(receiversets != null)
		{
			receivers = new ArrayList<Staff>();
			for(Integer staffId : receiversets)
			{
				Staff staff = new Staff();
				staff.setStaffId(staffId);
				receivers.add(staff);	
			}
		}
		return receivers;
	}
	public List<Staff> mergeStaff(List<Integer> departments, List<Integer> staffs,List<String> classIdList) throws Exception
	{
		List<Staff> receivers = null;
		Set<Integer> receiversets = null;
		if(departments != null)
		{
			if(receiversets == null)
				receiversets = new HashSet<Integer>();
			
			DepartmentManageDao departmentManageDao = new DepartmentManageDao();
			for( int i=0; i<departments.size(); i++ )
			{
				List<Integer> tmpstaffs = departmentManageDao.getStaffIdsByDepartmentId(departments.get(i));
				if(tmpstaffs != null)
					receiversets.addAll(tmpstaffs);
			}
		}
		if(classIdList != null)
		{
			if(receiversets == null)
				receiversets = new HashSet<Integer>();
			
				List<Integer> tmpstaffs =messageDao.getStaffIdsByClassIds(classIdList);
				if(tmpstaffs != null)
					receiversets.addAll(tmpstaffs);
		}
		if(staffs != null)
		{
			if(receiversets == null)
				receiversets = new HashSet<Integer>();
			receiversets.addAll(staffs);
		}
		
		if(receiversets != null)
		{
			receivers = new ArrayList<Staff>();
			for(Integer staffId : receiversets)
			{
				Staff staff = new Staff();
				staff.setStaffId(staffId);
				receivers.add(staff);	
			}
		}
		return receivers;
	}
//===============================================文件上传相关功能Service======================================
	public List<String> getUploadFileSaveName(List<String> uploadsFileName)throws Exception{
		
		List<String> fileNames=new ArrayList<String>();
		
		String maxId=messageDao.getMaxIDFromUploadFile();
		String firstId="";
		if(maxId!=null&&!maxId.equals("")){
			firstId=maxId;
		}else{
			firstId="File000000";
		}
		
		for(int i=0;i<uploadsFileName.size();i++){
			String nextFileId=createNextFileId(firstId);
			firstId=nextFileId;
			fileNames.add(nextFileId+".dat");
		}
		return fileNames;
	}
	
	private String createNextFileId(String firstId)throws Exception{
		//获取fistId的“File”后面的数字
		String numberStr=firstId.split("File")[1];
		//将字符串数字转换成数字并且加1
		int nextNumber=Integer.parseInt(numberStr)+1;
		//获取nextNumber的位数
		int length=(nextNumber+"").length();
		//获取nextNumber前面的“0”的个数，并建立该数量“0”的字符串
		String zero="";
		for(int i=0;i<(6-length);i++){
			zero=zero+"0";
		}
		//生成下一个文件ID
		String nextFileId="File"+zero+nextNumber;
		return nextFileId;
	}
	
	public boolean addUploadFileInfo(Staff staff,File upload,String fileName,String saveFileName)throws Exception{
		boolean isSuccess=false;
		//获取上传文件ID
		String uploadFileId=saveFileName.split("\\.")[0];
		//获取上传文件类型
		String fileType="";
		if(fileName.lastIndexOf(".")<0){
			fileType="未知类型";
		}else{
			fileType=fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		//构建UploadFile类型对象
		UploadFile uploadFile=new UploadFile();
		uploadFile.setFileId(uploadFileId);
		uploadFile.setFileName(fileName);
		uploadFile.setFileType(fileType);
		uploadFile.setCreator(staff);
		uploadFile.setFileSize(upload.length());
		uploadFile.setUploadTime(new Timestamp(System.currentTimeMillis()));
		
		//向数据库中插入文件信息
		try{
			isSuccess=messageDao.insertFileInfoIntoFileTable(uploadFile);
		}catch(Exception e){
			e.printStackTrace();
			isSuccess=false;
		}
		return isSuccess;
	}
	
	public boolean saveFileOnServer(String savePath,List<File> uploads,List<String> fileNames)throws Exception{
		boolean isSuccess=false;
		try{
			for(int i=0;i<uploads.size();i++){
				if(uploads.get(i)!=null){
					File saveUploadFile=new File(new File(savePath),fileNames.get(i));
					
					InputStream uploadInputStream=new FileInputStream(uploads.get(i));
					OutputStream saveUploadFileOutputStream=new FileOutputStream(saveUploadFile);
					byte[] buffer=new byte[1024];
					int length=0;
					while((length=uploadInputStream.read(buffer))>0){
						saveUploadFileOutputStream.write(buffer, 0, length);
					}
					uploadInputStream.close();
					saveUploadFileOutputStream.close();
				}
			}
			isSuccess=true;
		}catch(Exception e){
			e.printStackTrace();
			isSuccess=false;
		}
		return isSuccess;
	}
//==============================================文件上传相关功能Service结束===================================	
	public void send(Message message) throws Exception
	{
		List<String> attachments=message.getAttachment();
		String attachment=";";
		if(attachments!=null){
			if(attachments.size()!=0){
				for(int i=0;i<attachments.size();i++){
					attachment=attachment+attachments.get(i)+";";
				}
			}
		}
		messageDao.send(message,attachment);
	}
	
	public List<UploadFile> getAttachment(List<String> attachmentList)throws Exception{
		
		List<UploadFile> attachment=new ArrayList<UploadFile>();
		attachment=messageDao.getUploadFileList(attachmentList);
		
		return attachment;
	}
	
	public Message read(int messageId) throws Exception {
		// TODO Auto-generated method stub
		return messageDao.read(messageId);
	}
	
	public void receive(Staff staff, int messageId) throws Exception
	{
		messageDao.receive(staff, messageId);
	}

	public List<Message> listSent(Staff staff) throws Exception{
		// TODO Auto-generated method stub
		return messageDao.listSent(staff);
	}
	public List<Message> listSent(Staff staff,int pageNo,int pageSize) throws Exception{
		// TODO Auto-generated method stub
		//确定起始记录
		int startRecord=(pageNo-1)*pageSize;
		return messageDao.listSent(staff,startRecord,pageSize);
	}
	public int getSendTotalCount(Staff staff)throws Exception{
		return messageDao.getSendRecord(staff);
	}
	
	public List<Message> listReceived(Staff staff) throws Exception{
		// TODO Auto-generated method stub
		return messageDao.listReceived(staff);
	}
	public List<Message> listReceived(Staff staff,int pageNo,int pageSize) throws Exception{
		// TODO Auto-generated method stub
		//确定起始记录
		int startRecord=(pageNo-1)*pageSize;
		return messageDao.listReceived(staff,startRecord,pageSize);
	}
	public int getReceivedTotalCount(Staff staff)throws Exception{
		return messageDao.getReceivedCount(staff);
	}
	public int getTotalPage(int pageSize,int totalRecord)throws Exception{
		if(totalRecord%pageSize==0){
			return totalRecord/pageSize;
		}else{
			return totalRecord/pageSize+1;
		}
	}
	
	public List<Message> listUnread(Staff staff) throws Exception{
		// TODO Auto-generated method stub
		return messageDao.listUnread(staff);
	}
	public List<Message> listUnread(Staff staff,int pageNo,int pageSize) throws Exception{
		// TODO Auto-generated method stub
		//确定起始记录
		int startRecord=(pageNo-1)*pageSize;
		return messageDao.listUnread(staff,startRecord,pageSize);
	}
	public int getUnreadTotalCount(Staff staff)throws Exception{
		return messageDao.getUnreadTotalRecored(staff);
	}
	
	
	public boolean checkUnread(Staff staff, int messageId) throws Exception {
		// TODO Auto-generated method stub
		return messageDao.checkUnread(staff, messageId);
	}

	public static void main(String[] args) {
		String str="a,b,c,";
		System.out.println(str.substring(0, str.length()-1));
	}
}
