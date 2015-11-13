package com.chinanetwork.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinanetwork.performance.bean.Post;
import com.chinanetwork.performance.dao.PostManageDao;
import com.chinanetwork.performance.util.ConfigurationDataUtil;

public class PostManageService {
	
	private PostManageDao postManageDao = new PostManageDao();
	//------------------------------------------------------------------分页代码开始
	public List<Post> getAllPosts(int pageNo)throws Exception{
		String pageSizeStr=ConfigurationDataUtil.getStringValue("page_size");
		int pageSize=Integer.parseInt(pageSizeStr);
		
		int pageStart=(pageNo-1)*pageSize;
		
		List<Post> postList=postManageDao.getAllPosts(pageStart, pageSize);
		if(postList.size()==0){
			return null;
		}
		return postList;
	}
	public Map<String,Integer> getTotalData()throws Exception{
		Map<String,Integer> dataMap=new HashMap<String,Integer>();
		int pageSize=Integer.parseInt(ConfigurationDataUtil.getStringValue("page_size"));
		int countRecored=postManageDao.getTotalRecored();
		//计算总页数
		int countPage=countRecored/pageSize;
		int mod=countRecored%pageSize;
		if(mod!=0){
			countPage=countPage+1;
		}
		
		dataMap.put("countRecored", countRecored);
		dataMap.put("countPage", countPage);
		
		return dataMap;
	}
	//------------------------------------------------------------------分页代码结束
	public List<Post> getAllPosts() throws Exception
	{
		return postManageDao.getAllPost();
	}
	
	public Post getPost(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		return postManageDao.getPostById(parseInt);
	}

	public void addPost(Post post) throws Exception {
		postManageDao.addPost(post);
		return ;
	}

	public void deletePost(int parseInt) throws Exception {
		// TODO Auto-generated method stub
		postManageDao.deletePost(parseInt);
		
	}



	public void modifyPost(int parseInt, Post postToModify) throws Exception {
		// TODO Auto-generated method stub
		postManageDao.modifyPostById(parseInt, postToModify);
	}




	

}
