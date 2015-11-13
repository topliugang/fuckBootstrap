package com.chinanetwork.performance.bean;

/**
 * Ա����
 * 	��װԱ���ı�Ҫ����Ϣ��
 */
public class Staff {
	private int staffId;		// Ա����
	private String staffName;	//Ա�����
	private int staffNo;
	private String staffSex;	//Ա���Ա�
	private String password;	//Ա����½����
	private Department department;	//Ա������������Ϣ
	private Post post;				//Ա��ְλ��Ϣ
	private Role role;				//Ա����ɫ��Ϣ
	private StaffClass staffClass;
	
	
	public Staff(int staffId, String staffName, int staffNo, String staffSex,
			String password, Department department, Post post, Role role,
			StaffClass staffClass) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffNo = staffNo;
		this.staffSex = staffSex;
		this.password = password;
		this.department = department;
		this.post = post;
		this.role = role;
		this.staffClass = staffClass;
	}
	
	public Staff(int staffId, String staffName, int staffNo, String staffSex,
			String password, Department department, Post post, Role role
			) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffNo = staffNo;
		this.staffSex = staffSex;
		this.password = password;
		this.department = department;
		this.post = post;
		this.role = role;
	}
	
	
	
	
	public Staff() {
		super();
	}




	public StaffClass getStaffClass() {
		return staffClass;
	}

	public void setStaffClass(StaffClass staffClass) {
		this.staffClass = staffClass;
	}

	/**
	 *	���췽��
	 */

	
	/**
	 * ���Ե�getter&setter����
	 */
	public int getStaffId() {
		return staffId;
	}



	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffSex() {
		return staffSex;
	}

	public void setStaffSex(String staffSex) {
		this.staffSex = staffSex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	public int getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}

	/**
	 * toString ����
	 */
	@Override
	public String toString(){
		return this.staffId+","+this.staffName+","+this.staffNo+","+this.staffSex+","+this.department.getDepartmentName()+","+post.getPostName()+","+role.getRoleName();
	}
	
	/**
	 * equals ����
	 * 	�Ƚϵ���Ա���Ĺ���
	 */
	@Override
	public boolean equals(Object object){
		if(this==object){
			return true;
		}else if(object instanceof Staff){
			Staff staff=(Staff)object;
			if(this.getStaffId()==staff.getStaffId()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return staffId;
	}
}
