package com.chinanetwork.performance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Message;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.bean.UploadFile;
import com.chinanetwork.performance.util.DataBaseUtil;

public class MessageDao {

	StaffManageDao staffManageDao = new StaffManageDao();

	public String staffListToSQLString(List<Staff> list) {
		String sql = ";";
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Staff sf = list.get(i);
				int sfid = sf.getStaffId();
				String sfsdf = String.valueOf(sfid); 
				sql += ( sfsdf + ";");
			}
		}
		return sql;
	}

	public List<Staff> SQLStringToStaffList(String sql) throws Exception {
		List<Staff> staffs = null;
		if (sql != null && !sql.equals("") &&!sql.equals(";")) {
			staffs = new ArrayList<Staff>();
			sql = sql.substring(1);
			String[] strs = sql.split(";");
			for (int i = 0; i < strs.length; i++) {
				staffs.add(staffManageDao.getStaffById(Integer
						.parseInt(strs[i])));
			}
		}
		return staffs;
	}

	public List<String> SQLStringToStringList(String sql)throws Exception{
		List<String> attachment=new ArrayList<String>();
		if(sql != null && !sql.equals("") &&!sql.equals(";")){
			String[] strs = sql.split(";");
			for(int i=1;i<strs.length;i++){
				attachment.add(strs[i]);
			}
		}
		return attachment;
	}
	
	public List<Integer> getStaffIdsByClassIds(List<String> classIds)throws Exception{
		List<Integer> staffIds=new ArrayList<Integer>();
		String sql="select staff_id from staff where class_id like ?;";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DataBaseUtil.getConnection();
			for(int i=0;i<classIds.size();i++){
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+classIds.get(i)+"%");
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()){
					staffIds.add(resultSet.getInt("staff_id"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return staffIds;
	}
	
	public void send(Message message,String attchment) throws Exception {

		String sql1 = " insert into message( title, content, sender, receiver, attachment, message_date, received )"
				+ "values( ?, ?, ?, ?, ?, ?, ';' ) ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, message.getTitle());
		ps.setString(2, message.getContent());
		ps.setInt(3, message.getSender().getStaffId());
		ps.setString(4, staffListToSQLString(message.getReceiver()));
		ps.setString(5, attchment);
		ps.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);
	}

	public List<UploadFile> getUploadFileList(List<String> attachmentList)throws Exception{
		List<UploadFile> uploadFileList=new ArrayList<UploadFile>();
		String sql="select file_id,file_name,file_size from upload_file where file_id in(";
		
		for(int i=0;i<attachmentList.size();i++){
			sql=sql+"?,";
		}
		sql=sql.substring(0, sql.length()-1);
		sql=sql+");";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			for(int i=0;i<attachmentList.size();i++){
				preparedStatement.setString(i+1, attachmentList.get(i));
			}
			
			
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				UploadFile uploadFile=new UploadFile();
				uploadFile.setFileId(resultSet.getString("file_id"));
				uploadFile.setFileName(resultSet.getString("file_name"));
				uploadFile.setFileSize(resultSet.getLong("file_size"));
				
				uploadFileList.add(uploadFile);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return uploadFileList;
	}
	public Message read(int messageId) throws Exception {
		// TODO Auto-generated method stub
		Message message = null;
		String sql1 = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message where message_id = ?; ";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, messageId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			
		}
		DataBaseUtil.close(ps, conn);

		return message;
	}

	public List<Message> listSent(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		List<Message> messages = null;
		String sql1 = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message "
				+ "where sender = ? order by message_date; ";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setInt(1, staff.getStaffId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(messages == null)
				messages = new ArrayList<Message>();
			Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			//message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			messages.add(message);
		}
		DataBaseUtil.close(ps, conn);
		return messages;
	}
	public List<Message> listSent(Staff staff,int startRecord,int pageSize) throws Exception{
		List<Message> messages = null;
		String sql = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message "
				+ "where sender = ? order by message_date limit ?,?; ";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, staff.getStaffId());
		ps.setInt(2, startRecord);
		ps.setInt(3, pageSize);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(messages == null)
				messages = new ArrayList<Message>();
			Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			//message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			messages.add(message);
		}
		DataBaseUtil.close(ps, conn);
		return messages;
	}
	public int getSendRecord(Staff staff)throws Exception{
		int count=0;
		String sql = " select count(*) count from message "
			+ "where sender = ?; ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, staff.getStaffId());
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				count=resultSet.getInt("count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return count;
	}
	
	public List<Message> listReceived(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		List<Message> messages = null;
		String sql1 = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message "
				+ "where receiver like ? order by message_date desc;";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(messages == null)
				messages = new ArrayList<Message>();
			Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			messages.add(message);
		}
		DataBaseUtil.close(ps, conn);
		return messages;

	}
	//分页
	public List<Message> listReceived(Staff staff,int startRecord,int pageSize) throws Exception {
		List<Message> messages = null;
		String sql1 = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message "
				+ "where receiver like ? order by message_date desc limit ?,?;";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ps.setInt(2, startRecord);
		ps.setInt(3, pageSize);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(messages == null)
				messages = new ArrayList<Message>();
			Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			messages.add(message);
		}
		DataBaseUtil.close(ps, conn);
		return messages;
	}
	public int getReceivedCount(Staff staff)throws Exception{
		int count=0;
		String sql = " select count(*) count from message "
			+ "where receiver like ?;";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "%;" + String.valueOf(staff.getStaffId()) + ";%");
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				count=resultSet.getInt("count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return count;
	}
	
	public List<Message> listUnread(Staff staff) throws Exception {
		// TODO Auto-generated method stub
		List<Message> messages = null;
		String sql1 = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message "
				+ "where receiver like ? and received not like ? order by message_date; ";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ps.setString(2, "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(messages == null)
				messages = new ArrayList<Message>();
			Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			messages.add(message);
		}
		DataBaseUtil.close(ps, conn);
		return messages;

	}
	public List<Message> listUnread(Staff staff,int startRecord,int pageSize) throws Exception {
		List<Message> messages = null;
		String sql1 = " select message_id, title, content, simple_content, message_type, sender, "
				+ "receiver, received, attachment, message_date from message "
				+ "where receiver like ? and received not like ? order by message_date limit ?,?;";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ps.setString(2, "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ps.setInt(3, startRecord);
		ps.setInt(4, pageSize);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if(messages == null)
				messages = new ArrayList<Message>();
			Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setSimpleContent(rs.getString("simple_content"));
			message.setMessageType(rs.getInt("message_type"));
			message.setSender(staffManageDao.getStaffById(rs.getInt("sender")));
			message.setReceiver(SQLStringToStaffList(rs.getString("receiver")));
			message.setAttachment(SQLStringToStringList(rs.getString("attachment")));
			message.setMessageDate(rs.getTimestamp("message_date"));
			messages.add(message);
		}
		DataBaseUtil.close(ps, conn);
		return messages;
	}
	public int getUnreadTotalRecored(Staff staff)throws Exception{
		int count=0;
		String sql=" select count(*) count from message "
			+ "where receiver like ? and received not like ?";

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "%;" + String.valueOf(staff.getStaffId()) + ";%");
			preparedStatement.setString(2, "%;" + String.valueOf(staff.getStaffId()) + ";%");
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				count=resultSet.getInt("count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		
		return count;
	}
	public void receive(Staff staff, int messageId) throws Exception {
		String sql1 = " update message set received=concat(received, ?)" +
				"where message_id = ?  ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql1);
		ps.setString(1, String.valueOf(staff.getStaffId()) + ";");
		ps.setInt(2, messageId);
		ps.executeUpdate();

		DataBaseUtil.close(ps, conn);

	}

	public boolean checkUnread(Staff staff, int messageId) throws Exception {
		// TODO Auto-generated method stub
		boolean ret = false;
		String sql = "select * from message where message_id = ? " +
				"and receiver like ? and received like ? ; ";
		Connection conn = null;
		PreparedStatement ps = null;

		conn = DataBaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, messageId);
		ps.setString(2,  "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ps.setString(3,  "%;" + String.valueOf(staff.getStaffId()) + ";%");
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			ret = false;
		}
		else
		{
			ret = true;
		}
		DataBaseUtil.close(ps, conn);
		return ret;
	}

	//=========================================文件上传相关功能DAO===============================================
	public String getMaxIDFromUploadFile()throws Exception{
		String maxId="";
		String sql="select max(file_id) max from upload_file;";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				maxId=resultSet.getString("max");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(resultSet, preparedStatement, connection);
		}
		return maxId;
	}
	
	public boolean insertFileInfoIntoFileTable(UploadFile uploadFile)throws Exception{
		boolean isSuccess=false;
		String sql="insert into upload_file(file_id,file_name,file_type,file_size,creator_id,upload_time) values(?,?,?,?,?,?);";
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try{
			connection=DataBaseUtil.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, uploadFile.getFileId());
			preparedStatement.setString(2, uploadFile.getFileName());
			preparedStatement.setString(3, uploadFile.getFileType());
			preparedStatement.setLong(4, uploadFile.getFileSize());
			preparedStatement.setInt(5,uploadFile.getCreator().getStaffId());
			preparedStatement.setTimestamp(6, uploadFile.getUploadTime());
			
			preparedStatement.executeUpdate();
			
			isSuccess=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseUtil.close(preparedStatement, connection);
		}
		return isSuccess;
	}
}
