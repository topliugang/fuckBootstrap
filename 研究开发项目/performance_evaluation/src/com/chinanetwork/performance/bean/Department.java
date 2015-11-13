package com.chinanetwork.performance.bean;

/**
 * ������
 * 	��װ���ŵ������Ϣ
 */
public class Department {
	private int departmentId;		//����ID			
	private String departmentName;	//�������	
	private int higherDepartmentId;	//�ϼ�����ID
	private int nature;
	
	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}

	/**
	 * ���췽��
	 */
	public Department(){}
	
	public Department(int departmentId, String departmentName, int higherDepartmentId){
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.higherDepartmentId = higherDepartmentId;
	}
	
	/**
	 * ���Ե�getter&setter����
	 */
	public int getDepartmentId() {
		return departmentId;
	}
	
	public boolean equels(Department department)
	{
		if(department.getDepartmentId() == this.getDepartmentId() && department.getDepartmentName().equals(this.getDepartmentName()))
			return true;
		return false;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getHigherDepartmentId() {
		return higherDepartmentId;
	}

	public void setHigherDepartmentId(int higherDepartmentId) {
		this.higherDepartmentId = higherDepartmentId;
	}

	@Override
	public boolean equals(Object object){
		if(this==object){
			return true;
		}else if(object instanceof Department){
			Department department=(Department)object;
			if(this.getDepartmentId()==department.getDepartmentId()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return departmentId;
	}
}
