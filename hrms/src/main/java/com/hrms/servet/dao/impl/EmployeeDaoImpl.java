package com.hrms.servet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hrms.servet.dao.EmployeeDao;
import com.hrms.servet.model.Address;
import com.hrms.servet.model.BankDetail;
import com.hrms.servet.model.Employee;
import com.hrms.servet.model.Role;
import com.hrms.servet.util.Utility;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String GET_ALL_EMPLOYEE_QUERY = "SELECT `employees`.`employee_id`,`employees`.`employee_first_name`, `employees`.`employee_middle_name`, `employees`.`employee_last_name`, `employees`.`role_id`, `employees`.`employee_age`, `employees`.`employee_dob`, `employees`.`employee_branch`, `employees`.`reporting_person_employee_id`, `employees`.`employee_ip_address` FROM `emp_management_sys`.`employees`";
	private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT `employees`.`employee_id`,`employees`.`employee_first_name`, `employees`.`employee_middle_name`, `employees`.`employee_last_name`, `employees`.`role_id`, `employees`.`employee_age`, `employees`.`employee_dob`, `employees`.`employee_branch`, `employees`.`reporting_person_employee_id`, `employees`.`employee_ip_address` FROM `emp_management_sys`.`employees` WHERE `employees`.`employee_id` = ?";
	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO `emp_management_sys`.`employees` (`employee_id`, `employee_first_name`, `employee_middle_name`, `employee_last_name`, `role_id`, `employee_age`, `employee_dob`, `employee_branch`, `reporting_person_employee_id`, `employee_ip_address`) VALUES (?,?,?,?,?,?,?,?,?,?)";

	private static final String DELETE_EMPLOYEE_QUERY = "";
	private static final String UPDATE_EMPLOYEES_QUERY = "UPDATE `emp_management_sys`.`employees` SET `employee_id` = ?, `employee_first_name` = ?, `employee_middle_name` = ?, `employee_last_name` = ?, `role_id` = ?, `employee_age` = ?, `employee_dob` = ?, `employee_branch` = ?, `reporting_person_employee_id` = ?, `employee_ip_address` = ? WHERE `employee_id` = ?";
	private static final String INSERT_BANK_DTL_QUERY = "INSERT INTO `emp_management_sys`.`bank_details`(`bank_detail_id`,`employee_id`,`bank_name`,`bank_acc_no`,`ifsc_no`,`upi_id`) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_BANK_DTL_QUERY = "UPDATE `emp_management_sys`.`bank_details` SET `bank_detail_id` = ?,`employee_id` = ?,`bank_name` = ?,`bank_acc_no` = ?,`ifsc_no` = ?,`upi_id` = ?,WHERE `bank_detail_id` = ?";
	private static final String INSERT_ADDRESS_QUERY = "INSERT INTO `emp_management_sys`.`addresses`(`address_id`,`employee_id`,`address_line1`,`address_line2`,`city`,`state`,`country`,`pin_code`,`created_date`,`updated_date`)  VALUES (?,?,?,?,?,?,?,?)";	
	private static final String UPDATE_ADDRESS_QUERY = "UPDATE `emp_management_sys`.`addresses` SET `address_id` = ?,`employee_id` = ?,`address_line1` = ?,`address_line2` = ?,`city` = ?,`state` = ?,`country` = ?,`pin_code` = ? WHERE `address_id` = ?";
	
	@Override
	public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_QUERY)) {

			System.out.println(preparedStatement);

			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Process the ResultSet object
			while (rs.next()) {
				String employeeId = rs.getString("employee_id");
				String employeeFirstName = rs.getString("employee_first_name");
				String employeeMiddleName = rs.getString("employee_middle_name");
				String employeeLastName = rs.getString("employee_last_name");

				String roleId = rs.getString("role_id");

				String employeeAge = rs.getString("employee_age");
				String employeeDob = rs.getString("employee_dob");
				String employeeBranch = rs.getString("employee_branch");

//				String addressId = rs.getString("roleCode");

				String reportingPersonEmployeeId = rs.getString("reporting_person_employee_id");
				String employeeIpAddress = rs.getString("employee_ip_address");

//				String employeeBankSrNo = rs.getString("roleReportingTo");

				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				employee.setEmployeeFirstName(employeeFirstName);
				employee.setEmployeeMiddleName(employeeMiddleName);
				employee.setEmployeeLastName(employeeLastName);
				employee.setEmployeeAge(employeeAge);
				employee.setEmployeeDob(employeeDob);
				employee.setEmployeeBranch(employeeBranch);
				employee.setReportingPersonEmployeeId(reportingPersonEmployeeId);
				employee.setEmployeeIpAddress(employeeIpAddress);

				Role role = new Role();
				role.setRoleCode(roleId);
				employee.setRole(role);

//				Address address = new Address();
//				address.setSrNo(addressId);
//				employee.setAddress(address);
//
//				BankDetail bankDetail = new BankDetail();
//				bankDetail.setSrNo(employeeBankSrNo);
//				employee.setBankDetail(bankDetail);

				employeeList.add(employee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID_QUERY)) {
			preparedStatement.setString(1, employeeId);
			System.out.println(preparedStatement);

			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Process the ResultSet object
			while (rs.next()) {
				String empId = rs.getString("roleCode");
				String employeeFirstName = rs.getString("roleName");
				String employeeMiddleName = rs.getString("roleLevel");
				String employeeLastName = rs.getString("roleReportingTo");

				String roleId = rs.getString("roleCode");

				String employeeAge = rs.getString("roleName");
				String employeeDob = rs.getString("roleLevel");
				String employeeBranch = rs.getString("roleReportingTo");

				String addressId = rs.getString("roleCode");

				String reportingPersonEmployeeId = rs.getString("roleName");
				String employeeIpAddress = rs.getString("roleLevel");

				String employeeBankSrNo = rs.getString("roleReportingTo");

				Employee employee = new Employee();
				employee.setEmployeeId(empId);
				employee.setEmployeeFirstName(employeeFirstName);
				employee.setEmployeeMiddleName(employeeMiddleName);
				employee.setEmployeeLastName(employeeLastName);
				employee.setEmployeeAge(employeeAge);
				employee.setEmployeeDob(employeeDob);
				employee.setEmployeeBranch(employeeBranch);
				employee.setReportingPersonEmployeeId(reportingPersonEmployeeId);
				employee.setEmployeeIpAddress(employeeIpAddress);

				Role role = new Role();
				role.setRoleCode(roleId);
				employee.setRole(role);

				Address address = new Address();
				address.setSrNo(addressId);
				employee.setAddress(address);

				BankDetail bankDetail = new BankDetail();
				bankDetail.setSrNo(employeeBankSrNo);
				employee.setBankDetail(bankDetail);

				employeeList.add(employee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		boolean isInserted = false;
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_QUERY)) {

			preparedStatement.setString(1, employee.getEmployeeFirstName());
			preparedStatement.setString(2, employee.getEmployeeMiddleName());
			preparedStatement.setString(3, employee.getEmployeeLastName());

			System.out.println(preparedStatement);

			preparedStatement.executeUpdate();
			isInserted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isInserted;
	}

	@Override
	public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
