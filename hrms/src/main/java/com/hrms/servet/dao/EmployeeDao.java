package com.hrms.servet.dao;

import java.sql.SQLException;
import java.util.List;

import com.hrms.servet.model.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;

	public List<Employee> getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException;

	public boolean insertEmployee(Employee employee) throws SQLException, ClassNotFoundException;

	public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException;

	public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;
}
