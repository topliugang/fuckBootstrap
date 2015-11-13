package com.chinanetwork.performance.bean;

public class FuncpageCategory {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FuncpageCategory(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public FuncpageCategory() {
		super();
	}
	
	@Override
	public boolean equals(Object object){
		if(this==object){
			return true;
		}else if(object instanceof FuncpageCategory){
			FuncpageCategory category=(FuncpageCategory)object;
			if(this.getId()==category.getId()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	
}
