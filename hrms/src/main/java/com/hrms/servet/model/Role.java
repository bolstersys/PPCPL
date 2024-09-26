package com.hrms.servet.model;

public class Role {

	private String roleCode;
	private String roleName;
	private String roleLevel;
	private String reportingRole;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getReportingRole() {
		return reportingRole;
	}

	public void setReportingRole(String reportingRole) {
		this.reportingRole = reportingRole;
	}

	@Override
	public String toString() {
		return "Role [roleCode=" + roleCode + ", roleName=" + roleName + ", roleLevel=" + roleLevel + ", reportingRole=" + reportingRole
				+ "]";
	}

}
