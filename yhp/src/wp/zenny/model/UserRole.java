package wp.zenny.model;

import java.util.Date;

public class UserRole {

	private String userName;
	private String roleId;
	private Date dateCreate;
	private String creator;
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public UserRole(String userName, String roleId, Date dateCreate, String creator) {
		this.userName = userName;
		this.roleId = roleId;
		this.dateCreate = dateCreate;
		this.creator = creator;
	}
	
	public UserRole() {}

}
