package com.chinanetwork.performance.bean;

/**
 * ְλ��
 * 	��װԱ����ְλ��Ϣ
 * @author Administrator
 *
 */
public class Post {
	private int postId;			//ְλID
	private String postName;	//ְλ���
	
	/**
	 * ���췽��
	 */
	public Post(){}
	
	public Post(String postName){
		this.postName=postName;
	}
	
	public Post(int postId, String postName)
	{
		this.postId = postId;
		this.postName = postName;
	}
	
	/**
	 * ���Ե�getter&setter����
	 */
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
}
