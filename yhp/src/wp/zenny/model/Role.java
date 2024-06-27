package wp.zenny.model;

public class Role {
	private String roleId;
	private String roleName;
	
	public String getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Role() {}

	public Role(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

}