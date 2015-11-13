package com.chinanetwork.performance.bean;

/**
 * ��ɫ��
 * 	��װԱ���Ľ�ɫ��Ϣ
 */
public class Role {
	private int roleId;			//��ɫID
	private String roleName;	//��ɫ���
	private String roleDescription;
	/**
	 * ���췽��
	 */
	/**
	 * ���Ե�setter&getterr����
	 */
	public int getRoleId() {
		return roleId;
	}





	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	

	public Role(int roleId, String roleName, String roleDescription) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}



	public Role() {
		super();
	}

	
}
