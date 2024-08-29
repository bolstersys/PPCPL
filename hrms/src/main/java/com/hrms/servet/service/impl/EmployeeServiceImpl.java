package com.hrms.servet.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.google.gson.Gson;
import com.hrms.servet.dao.EmployeeDao;
import com.hrms.servet.dao.RoleDao;
import com.hrms.servet.dao.impl.EmployeeDaoImpl;
import com.hrms.servet.dao.impl.RoleDaoImpl;
import com.hrms.servet.model.Address;
import com.hrms.servet.model.BankDetail;
import com.hrms.servet.model.Employee;
import com.hrms.servet.model.Role;
import com.hrms.servet.service.EmployeeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	private RoleDao roleDao = new RoleDaoImpl();
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
			logger.severe("Error  in RoleServiceImpl --> getAllEmployee "+e.getMessage());
		}
		
	}

	@Override
	public void getEmployeeById(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertEmployee(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = new Employee();
		employee.setEmployeeId(request.getParameter("employeeId"));  // Employee ID created by user
		employee.setEmployeeFirstName(request.getParameter("employeeFirstName"));
		employee.setEmployeeMiddleName(request.getParameter("employeeMiddleName"));
		employee.setEmployeeLastName(request.getParameter("employeeLastName"));
		employee.setEmployeeAge(request.getParameter("employeeAge"));
		employee.setEmployeeDob(request.getParameter("employeeDob"));
		employee.setEmployeeBranch(request.getParameter("employeeBranch"));
		employee.setEmployeeIpAddress(request.getParameter("employeeIpAddress"));
		employee.setReportingPersonEmployeeId(request.getParameter("reportingPersonEmployeeId"));

		// Set Role
		Role role = new Role();
		role.setRoleCode(request.getParameter("roleCode"));
		employee.setRole(role);

		// Set Address
		Address address = new Address();
		address.setAddressLine1(request.getParameter("addressLine1"));
		address.setAddressLine2(request.getParameter("addressLine2"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setCountry(request.getParameter("country"));
		address.setPinCode(request.getParameter("pinCode"));
		employee.setAddress(address);

		// Set Bank Details
		BankDetail bankDetail = new BankDetail();
		bankDetail.setBankName(request.getParameter("bankName"));
		bankDetail.setBankAccNo(request.getParameter("bankAccNo"));
		bankDetail.setIfscNo(request.getParameter("ifscNo"));
		bankDetail.setUpiId(request.getParameter("upiId"));
		employee.setBankDetail(bankDetail);

		try {
			employeeDao.insertEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = new Employee();
		employee.setEmployeeId(request.getParameter("employeeId"));  // Employee ID created by user
		employee.setEmployeeFirstName(request.getParameter("employeeFirstName"));
		employee.setEmployeeMiddleName(request.getParameter("employeeMiddleName"));
		employee.setEmployeeLastName(request.getParameter("employeeLastName"));
		employee.setEmployeeAge(request.getParameter("employeeAge"));
		employee.setEmployeeDob(request.getParameter("employeeDob"));
		employee.setEmployeeBranch(request.getParameter("employeeBranch"));
		employee.setEmployeeIpAddress(request.getParameter("employeeIpAddress"));
		employee.setReportingPersonEmployeeId(request.getParameter("reportingPersonEmployeeId"));

		// Set Role
		Role role = new Role();
		role.setRoleCode(request.getParameter("roleCode"));
		employee.setRole(role);

		// Set Address
		Address address = new Address();
		address.setAddressLine1(request.getParameter("addressLine1"));
		address.setAddressLine2(request.getParameter("addressLine2"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setCountry(request.getParameter("country"));
		address.setPinCode(request.getParameter("pinCode"));
		employee.setAddress(address);

		// Set Bank Details
		BankDetail bankDetail = new BankDetail();
		bankDetail.setBankName(request.getParameter("bankName"));
		bankDetail.setBankAccNo(request.getParameter("bankAccNo"));
		bankDetail.setIfscNo(request.getParameter("ifscNo"));
		bankDetail.setUpiId(request.getParameter("upiId"));
		employee.setBankDetail(bankDetail);

		try {
			employeeDao.updateEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();

			logger.severe("Error  in RoleServiceImpl --> updateEmployee "+e.getMessage());
		}
	}

	@Override
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		String employeeId = request.getParameter("employeeId");

		try {
			employeeDao.deleteEmployee(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> deleteEmployee "+e.getMessage());
		}
	}

	@Override
	public void loadEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getParameter("action");
			if(action.equals("updateEmployee")){
				String employeeId = request.getParameter("employeeId");
				Employee employee = employeeDao.getEmployeeById(employeeId);
				Gson gson = new Gson();
				Map selectedRoleMap = gson.fromJson(gson.toJson(employee), Map.class);
				request.setAttribute("selectedEmployee", selectedRoleMap);
			}
			List<Employee> employeeList = employeeDao.getAllEmployee();
			List<Role> roleList = roleDao.getAllRoles();
			request.setAttribute("employeeList", employeeList);
			request.setAttribute("roleList", roleList);
			request.setAttribute("action", action);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee/employee.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in RoleServiceImpl --> loadRoleForm "+e.getMessage());
		}
	}
}
