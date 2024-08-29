package com.hrms.servet.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.google.gson.Gson;
import com.hrms.servet.RoleServlet;
import com.hrms.servet.bean.ResponseBean;
import com.hrms.servet.dao.RoleDao;
import com.hrms.servet.dao.impl.RoleDaoImpl;
import com.hrms.servet.model.Role;
import com.hrms.servet.service.RoleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleServiceImpl implements RoleService {
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());
	private RoleDao roleDao = new RoleDaoImpl();

	@Override
	public void getAllRoles(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Role> roleList = roleDao.getAllRoles();
			request.setAttribute("roleList", roleList);
			request.setAttribute("action", "getAllRoleData");
			logger.severe("Error  in RoleServiceImpl --> getAllRoles ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> getAllRoles "+e.getMessage());
		}
	}

	@Override
	public void loadRoleForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getParameter("action");
			if(action.equals("updateRole")){
				String roleId = request.getParameter("roleId");
				List<Role> selectedRoleList = roleDao.getRoleById(roleId);
				Gson gson = new Gson();
				Map selectedRoleMap = gson.fromJson(gson.toJson(selectedRoleList.get(0)), Map.class);
				request.setAttribute("selectedRole", selectedRoleMap);
			}
			List<Role> roleList = roleDao.getAllRoles();
			request.setAttribute("roleList", roleList);
			request.setAttribute("action", action);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> loadRoleForm "+e.getMessage());
		}
	}

	@Override
	public void insertRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleName = request.getParameter("roleName");
			String roleLevel = request.getParameter("roleLevel");
			String roleReportingTo = request.getParameter("roleReportingTo");
			Role role = new Role();
			role.setRoleName(roleName);
			role.setRoleLevel(roleLevel);
			role.setRoleReportingTo(roleReportingTo);

			roleDao.insertRole(role);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Role Created Successfully.");
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> insertRole "+e.getMessage());
		}
	}

	@Override
	public void updateRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleCode = request.getParameter("roleCode");
			String roleName = request.getParameter("roleName");
			String roleLevel = request.getParameter("roleLevel");
			String roleReportingTo = request.getParameter("roleReportingTo").isEmpty() ? null : request.getParameter("roleReportingTo");
			Role role = new Role();
			role.setRoleCode(roleCode);
			role.setRoleName(roleName);
			role.setRoleLevel(roleLevel);
			role.setRoleReportingTo(roleReportingTo);

			roleDao.updateRole(role);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Role Updated Successfully.");
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> updateRole "+e.getMessage());
		}
	}

	@Override
	public void deleteRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleCode = request.getParameter("roleId");
			roleDao.deleteRole(roleCode);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Role Deleted Successfully.");
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> deleteRole "+e.getMessage());
		}
	}


}
