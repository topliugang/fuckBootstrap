package com.chinanetwork.performance.bean;

import java.sql.Timestamp;
import java.util.List;

public class Message {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSimpleContent() {
		return simpleContent;
	}
	public void setSimpleContent(String simpleContent) {
		this.simpleContent = simpleContent;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public Staff getSender() {
		return sender;
	}
	public void setSender(Staff sender) {
		this.sender = sender;
	}
	public List<Staff> getReceiver() {
		return receiver;
	}
	public void setReceiver(List<Staff> receiver) {
		this.receiver = receiver;
	}
	public List<String> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<String> attachment) {
		this.attachment = attachment;
	}
	public Timestamp getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}
	private int id;								//message ID
	private String title;						//消息标题
	private String content;						//消息内容
	private String simpleContent;				//
	private int messageType;					//消息类型
	private Staff sender;						//消息发送者
	private List<Staff> receiver;				//消息接收者
	private List<String> attachment;			//附件名称
	private Timestamp messageDate;					//消息发送日期
	
	public Message(int id, String title, String content, String simpleContent,
			int messageType, Staff sender, List<Staff> receiver,
			List<String> attachment, Timestamp messageDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.simpleContent = simpleContent;
		this.messageType = messageType;
		this.sender = sender;
		this.receiver = receiver;
		this.attachment = attachment;
		this.messageDate = messageDate;
	}
	public Message() {
		super();
	}
	
	
	
	
	

}
