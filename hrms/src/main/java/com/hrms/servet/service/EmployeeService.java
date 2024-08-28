package com.hrms.servet.service;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface EmployeeService {

	public void getAllEmployee(HttpServletRequest request, HttpServletResponse response);

	public void getEmployeeById(HttpServletRequest request, HttpServletResponse response);

	public void insertEmployee(HttpServletRequest request, HttpServletResponse response);

	public void updateEmployee(HttpServletRequest request, HttpServletResponse response);

	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response);
}
