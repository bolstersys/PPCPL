package com.hrms.servet.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.hrms.servet.RoleServlet;
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

	static {
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("D:/STS extract/STS-4.24/workspace/logs/mylog.log", true);
		
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
		} catch (SecurityException | IOException e) {
			logger.severe("Error  in RoleServiceImpl --> logger "+e.getMessage());
		}
	}
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
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> getAllRoles "+e.getMessage());
		}
	}

	@Override
	public void loadRoleForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("action", "insertRole");
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> loadRoleForm "+e.getMessage());
		}
	}
	@Override
	public void getRoleById(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleId = request.getParameter("roleId");
			List<Role> roleList = roleDao.getRoleById(roleId);
			request.setAttribute("roleList", roleList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> getRoleById "+e.getMessage());
		}
	}

	@Override
	public void insertRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleName = request.getParameter("");
			String roleLevel = request.getParameter("");
			String roleReportingTo = request.getParameter("");
			Role role = new Role();
			role.setRoleName(roleName);
			role.setRoleLevel(roleLevel);
			role.setRoleReportingTo(roleReportingTo);

			boolean isInserted = roleDao.insertRole(role);
			request.setAttribute("isInserted", isInserted);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> insertRole "+e.getMessage());
		}
	}

	@Override
	public void updateRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleCode = request.getParameter("");
			String roleName = request.getParameter("");
			String roleLevel = request.getParameter("");
			String roleReportingTo = request.getParameter("");
			Role role = new Role();
			role.setRoleCode(roleCode);
			role.setRoleName(roleName);
			role.setRoleLevel(roleLevel);
			role.setRoleReportingTo(roleReportingTo);

			boolean isUpdated = roleDao.updateRole(role);
			request.setAttribute("isUpdated", isUpdated);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> updateRole "+e.getMessage());
		}
	}

	@Override
	public void deleteRole(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleCode = request.getParameter("");
			boolean isDeleted = roleDao.deleteRole(roleCode);
			request.setAttribute("isDeleted", isDeleted);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> deleteRole "+e.getMessage());
		}
	}

	@Override
	public void showRolePage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/roles/role.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> showRolePage "+e.getMessage());
		}
	}

}
