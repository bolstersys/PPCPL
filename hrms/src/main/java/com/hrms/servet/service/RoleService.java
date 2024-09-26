package com.hrms.servet.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface RoleService {
	public void getAllRoles(HttpServletRequest request, HttpServletResponse response);

	public void insertRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public void deleteRole(HttpServletRequest request, HttpServletResponse response);

	public void loadRoleForm(HttpServletRequest request, HttpServletResponse response);
}
