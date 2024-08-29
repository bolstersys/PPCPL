package com.hrms.servet.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface RoleService {
	public void getAllRoles(HttpServletRequest request, HttpServletResponse response);

	public void insertRole(HttpServletRequest request, HttpServletResponse response);

	public void updateRole(HttpServletRequest request, HttpServletResponse response);

	public void deleteRole(HttpServletRequest request, HttpServletResponse response);

	public void loadRoleForm(HttpServletRequest request, HttpServletResponse response);
}
