package com.chinanetwork.performance.web.action;

import java.util.List;

import com.chinanetwork.performance.bean.Funcpage;
import com.chinanetwork.performance.bean.FuncpageCategory;
import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.service.ToMenuService;

public class ToMenuAction extends BaseAction{

	private ToMenuService toMenuService=new ToMenuService();
	
	private List<Funcpage> funcpageList;
	private List<FuncpageCategory> categoryList;
	public List<Funcpage> getFuncpageList() {
		return funcpageList;
	}
	public void setFuncpageList(List<Funcpage> funcpageList) {
		this.funcpageList = funcpageList;
	}
	public List<FuncpageCategory> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<FuncpageCategory> categoryList) {
		this.categoryList = categoryList;
	}
	
	@Override
	public String execute(){
		Staff staff=(Staff)this.getSession().get("staff");
		try{
			funcpageList=toMenuService.getFuncpages(staff);
			
			
			
			categoryList=toMenuService.getFuncpageCategories(funcpageList);
			for(int i=0;i<funcpageList.size();i++){
				System.out.println(funcpageList.get(i).getName());
			}
			for(int i=0;i<categoryList.size();i++){
				System.out.println(categoryList.get(i).getName());
			}
		}catch(Exception e){
			return "error";
		}
		return "toMenu";
	}
}
