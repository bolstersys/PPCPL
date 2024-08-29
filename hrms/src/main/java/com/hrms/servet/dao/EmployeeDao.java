package com.hrms.servet.dao;

import java.sql.SQLException;
import java.util.List;

import com.hrms.servet.model.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;

	public Employee getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException;

	public void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException;

	public void updateEmployee(Employee employee) throws SQLException, ClassNotFoundException;

	public void deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;
}
