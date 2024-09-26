package com.hrms.servet.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.hrms.servet.bean.ResponseBean;
import com.hrms.servet.dao.RoleDao;
import com.hrms.servet.dao.impl.RoleDaoImpl;
import com.hrms.servet.model.Role;
import com.hrms.servet.service.RoleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleServiceImpl implements RoleService {
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());
	private RoleDao roleDao = new RoleDaoImpl();
	private static final String[] requiredFields = {"roleName", "roleLevel"};

	@Override
	public void getAllRoles(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Role> roleList = roleDao.getAllRoles();
			request.setAttribute("roleList", roleList);
			request.setAttribute("action", "getAllRoleData");
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
				String roleCode = request.getParameter("roleCode");
				List<Role> selectedRoleList = roleDao.getRoleById(roleCode);
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
	public void insertRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMessages = new ArrayList<>();
		if (!EmployeeServiceImpl.validateRequiredFields(request, requiredFields, errorMessages)) {
			logger.severe("All required fields are not present in RoleServiceImpl --> insertRole "+errorMessages);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setMessage(String.join(", ", errorMessages));
			responseBean.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("action", "ajaxCommonResponse");
			response.setContentType("application/json");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
			return;
		}
		try {
			String roleName = request.getParameter("roleName");
			String roleLevel = request.getParameter("roleLevel");
			String reportingRole = request.getParameter("reportingRole");
			Role role = new Role();
			role.setRoleName(roleName);
			role.setRoleLevel(roleLevel);
			role.setReportingRole(reportingRole);

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
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setStatusCode(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
			responseBean.setMessage("Error in insertRole: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	public void updateRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMessages = new ArrayList<>();
		if (!EmployeeServiceImpl.validateRequiredFields(request, requiredFields, errorMessages)) {
			logger.severe("All required fields are not present in RoleServiceImpl --> insertRole "+errorMessages);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setMessage(String.join(", ", errorMessages));
			responseBean.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("action", "ajaxCommonResponse");
			response.setContentType("application/json");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
			return;
		}
		try {
			String roleCode = request.getParameter("roleCode");
			String roleName = request.getParameter("roleName");
			String roleLevel = request.getParameter("roleLevel");
			String reportingRole = request.getParameter("reportingRole");
			Role role = new Role();
			role.setRoleCode(roleCode);
			role.setRoleName(roleName);
			role.setRoleLevel(roleLevel);
			role.setReportingRole(reportingRole);

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
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setStatusCode(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
			responseBean.setMessage("Error in insertRole: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	public void deleteRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleCode = request.getParameter("roleCode");
			roleDao.deleteRole(roleCode);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Role Deleted Successfully.");
			List<Role> roleList = roleDao.getAllRoles();
			request.setAttribute("roleList", roleList);
			request.setAttribute("action", "getAllRoleData");
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> deleteRole "+e.getMessage());
		}
	}


}
