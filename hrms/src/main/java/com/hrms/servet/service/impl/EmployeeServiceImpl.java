package com.hrms.servet.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.hrms.servet.dao.EmployeeDao;
import com.hrms.servet.dao.RoleDao;
import com.hrms.servet.dao.impl.EmployeeDaoImpl;
import com.hrms.servet.dao.impl.RoleDaoImpl;
import com.hrms.servet.model.Employee;
import com.hrms.servet.model.Role;
import com.hrms.servet.service.EmployeeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());

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
	public void getAllEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Employee> roleList = employeeDao.getAllEmployee();
			request.setAttribute("employeeList", roleList);
			request.setAttribute("action", "getAllEmployeeData");
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee/employee.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> getAllRoles "+e.getMessage());
		}
		
	}

	@Override
	public void getEmployeeById(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	


}
