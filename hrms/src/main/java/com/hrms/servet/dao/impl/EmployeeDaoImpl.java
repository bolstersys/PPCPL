package com.hrms.servet.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.hrms.servet.dao.EmployeeDao;
import com.hrms.servet.model.Address;
import com.hrms.servet.model.BankDetail;
import com.hrms.servet.model.Employee;
import com.hrms.servet.model.Role;
import com.hrms.servet.service.impl.EmployeeServiceImpl;
import com.hrms.servet.util.Utility;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class.getName());
	private static final String GET_ALL_EMPLOYEE_QUERY = "SELECT * FROM employees";
	private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT employees.employee_id, employees.employee_first_name, employees.employee_middle_name, employees.employee_last_name, employees.role_id, employees.employee_age, employees.employee_dob, employees.employee_branch, employees.reporting_person_employee_id, employees.employee_ip_address FROM emp_management_sys.employees WHERE employees.employee_id = ?";

	private static final String GET_EMPLOYEE_DETAILS_BY_ID_QUERY = "SELECT e.employee_id, e.employee_first_name, e.employee_middle_name, e.employee_last_name, e.employee_age, e.employee_dob, e.employee_branch, e.reporting_person_employee_id, e.employee_ip_address, r.role_id, r.role_name, r.role_level, r.role_reporting_to, a.address_id, a.address_line1, a.address_line2, a.city, a.state, a.country, a.pin_code, b.bank_detail_id, b.bank_name, b.bank_acc_no, b.ifsc_no, b.upi_id FROM employees e LEFT JOIN roles r ON e.role_id = r.role_id LEFT JOIN addresses a ON e.employee_id = a.employee_id LEFT JOIN bank_details b ON e.employee_id = b.employee_id WHERE e.employee_id = ?\n";

	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO emp_management_sys.employees (employee_id, employee_first_name, employee_middle_name, employee_last_name, role_id, employee_age, employee_dob, employee_branch, reporting_person_employee_id, employee_ip_address) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_ADDRESS_QUERY = "INSERT INTO emp_management_sys.addresses (employee_id, address_line1, address_line2, city, state, country, pin_code, created_date, updated_date) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_BANK_DTL_QUERY = "INSERT INTO emp_management_sys.bank_details (employee_id, bank_name, bank_acc_no, ifsc_no, upi_id) VALUES (?,?,?,?,?)";

	private static final String UPDATE_EMPLOYEES_QUERY = "UPDATE emp_management_sys.employees SET employee_first_name = ?, employee_middle_name = ?, employee_last_name = ?, role_id = ?, employee_age = ?, employee_dob = ?, employee_branch = ?, reporting_person_employee_id = ?, employee_ip_address = ? WHERE employee_id = ?";
	private static final String UPDATE_ADDRESS_QUERY = "UPDATE emp_management_sys.addresses SET address_line1 = ?, address_line2 = ?, city = ?, state = ?, country = ?, pin_code = ?, updated_date = ? WHERE employee_id = ?";
	private static final String UPDATE_BANK_DTL_QUERY = "UPDATE emp_management_sys.bank_details SET bank_name = ?, bank_acc_no = ?, ifsc_no = ?, upi_id = ? WHERE employee_id = ?";

	private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM emp_management_sys.employees WHERE employee_id = ?";
	private static final String DELETE_ADDRESS_QUERY = "DELETE FROM emp_management_sys.addresses WHERE employee_id = ?";
	private static final String DELETE_BANK_DTL_QUERY = "DELETE FROM emp_management_sys.bank_details WHERE employee_id = ?";



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

				employeeList.add(employee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(String employeeId) throws SQLException, ClassNotFoundException {
		Employee employee = null;

		try (Connection connection = Utility.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(GET_EMPLOYEE_DETAILS_BY_ID_QUERY)) {

			stmt.setString(1, employeeId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeeFirstName(rs.getString("employee_first_name"));
				employee.setEmployeeMiddleName(rs.getString("employee_middle_name"));
				employee.setEmployeeLastName(rs.getString("employee_last_name"));
				employee.setEmployeeAge(rs.getString("employee_age"));
				employee.setEmployeeDob(rs.getString("employee_dob"));
				employee.setEmployeeBranch(rs.getString("employee_branch"));
				employee.setReportingPersonEmployeeId(rs.getString("reporting_person_employee_id"));
				employee.setEmployeeIpAddress(rs.getString("employee_ip_address"));

				Role role = new Role();
				role.setRoleCode(rs.getString("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setRoleLevel(rs.getString("role_level"));
				role.setRoleReportingTo(rs.getString("role_reporting_to"));
				employee.setRole(role);

				Address address = new Address();
				address.setSrNo(rs.getString("address_id"));
				address.setAddressLine1(rs.getString("address_line1"));
				address.setAddressLine2(rs.getString("address_line2"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setPinCode(rs.getString("pin_code"));
				employee.setAddress(address);

				BankDetail bankDetail = new BankDetail();
				bankDetail.setSrNo(rs.getString("bank_detail_id"));
				bankDetail.setBankName(rs.getString("bank_name"));
				bankDetail.setBankAccNo(rs.getString("bank_acc_no"));
				bankDetail.setIfscNo(rs.getString("ifsc_no"));
				bankDetail.setUpiId(rs.getString("upi_id"));
				employee.setBankDetail(bankDetail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return employee;
	}

	@Override
	public void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		Connection connection = null;

		try {
			connection = Utility.getConnection();
			connection.setAutoCommit(false);  // Start transaction

			String generatedEmployeeId = null;

			// Insert into employees table and retrieve the generated employee_id
			try (PreparedStatement employeeStmt = connection.prepareStatement(INSERT_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				employeeStmt.setString(1, null);
				employeeStmt.setString(2, employee.getEmployeeFirstName());
				employeeStmt.setString(3, employee.getEmployeeMiddleName());
				employeeStmt.setString(4, employee.getEmployeeLastName());
				employeeStmt.setString(5, employee.getRole().getRoleCode());
				employeeStmt.setString(6, employee.getEmployeeAge());
				employeeStmt.setString(7, employee.getEmployeeDob());
				employeeStmt.setString(8, employee.getEmployeeBranch());
				employeeStmt.setString(9, employee.getReportingPersonEmployeeId());
				employeeStmt.setString(10, employee.getEmployeeIpAddress());

				int affectedRows = employeeStmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Inserting employee failed, no rows affected.");
				}

				// Retrieve the generated employee_id
				try (ResultSet generatedKeys = employeeStmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedEmployeeId = generatedKeys.getString(1);
					} else {
						throw new SQLException("Inserting employee failed, no ID obtained.");
					}
				}
			}

			// Set the generated employee ID to the employee object
			employee.setEmployeeId(generatedEmployeeId);

			// Insert into address table
			try (PreparedStatement addressStmt = connection.prepareStatement(INSERT_ADDRESS_QUERY)) {
				Address address = employee.getAddress();
				addressStmt.setString(1, generatedEmployeeId); // Relational key
				addressStmt.setString(2, address.getAddressLine1());
				addressStmt.setString(3, address.getAddressLine2());
				addressStmt.setString(4, address.getCity());
				addressStmt.setString(5, address.getState());
				addressStmt.setString(6, address.getCountry());
				addressStmt.setString(7, address.getPinCode());
				addressStmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));  // created_date
				addressStmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));  // updated_date

				addressStmt.executeUpdate();
			}

			// Insert into bank details table
			try (PreparedStatement bankStmt = connection.prepareStatement(INSERT_BANK_DTL_QUERY)) {
				BankDetail bankDetail = employee.getBankDetail();
				bankStmt.setString(1, generatedEmployeeId); // Relational key
				bankStmt.setString(2, bankDetail.getBankName());
				bankStmt.setString(3, bankDetail.getBankAccNo());
				bankStmt.setString(4, bankDetail.getIfscNo());
				bankStmt.setString(5, bankDetail.getUpiId());

				bankStmt.executeUpdate();
			}

			connection.commit();  // Commit transaction if all insertions succeed

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();  // Rollback transaction in case of failure
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		boolean isUpdated = false;
		Connection connection = null;

		try {
			connection = Utility.getConnection();
			connection.setAutoCommit(false);  // Start transaction

			// Update employees table
			try (PreparedStatement employeeStmt = connection.prepareStatement(UPDATE_EMPLOYEES_QUERY)) {
				employeeStmt.setString(1, employee.getEmployeeFirstName());
				employeeStmt.setString(2, employee.getEmployeeMiddleName());
				employeeStmt.setString(3, employee.getEmployeeLastName());
				employeeStmt.setString(4, employee.getRole().getRoleCode());
				employeeStmt.setString(5, employee.getEmployeeAge());
				employeeStmt.setString(6, employee.getEmployeeDob());
				employeeStmt.setString(7, employee.getEmployeeBranch());
				employeeStmt.setString(8, employee.getReportingPersonEmployeeId());
				employeeStmt.setString(9, employee.getEmployeeIpAddress());
				employeeStmt.setString(10, employee.getEmployeeId());  // Use employee_id in WHERE clause

				employeeStmt.executeUpdate();
			}

			// Update address table
			try (PreparedStatement addressStmt = connection.prepareStatement(UPDATE_ADDRESS_QUERY)) {
				Address address = employee.getAddress();
				addressStmt.setString(1, address.getAddressLine1());
				addressStmt.setString(2, address.getAddressLine2());
				addressStmt.setString(3, address.getCity());
				addressStmt.setString(4, address.getState());
				addressStmt.setString(5, address.getCountry());
				addressStmt.setString(6, address.getPinCode());
				addressStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));  // updated_date
				addressStmt.setString(8, employee.getEmployeeId());  // Use employee_id in WHERE clause

				addressStmt.executeUpdate();
			}

			// Update bank details table
			try (PreparedStatement bankStmt = connection.prepareStatement(UPDATE_BANK_DTL_QUERY)) {
				BankDetail bankDetail = employee.getBankDetail();
				bankStmt.setString(1, bankDetail.getBankName());
				bankStmt.setString(2, bankDetail.getBankAccNo());
				bankStmt.setString(3, bankDetail.getIfscNo());
				bankStmt.setString(4, bankDetail.getUpiId());
				bankStmt.setString(5, employee.getEmployeeId());  // Use employee_id in WHERE clause

				bankStmt.executeUpdate();
			}

			connection.commit();  // Commit transaction if all updates succeed
			isUpdated = true;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();  // Rollback transaction in case of failure
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
		boolean isDeleted = false;
		Connection connection = null;

		try {
			connection = Utility.getConnection();
			connection.setAutoCommit(false);  // Start transaction

			// Delete from addresses table
			try (PreparedStatement addressStmt = connection.prepareStatement(DELETE_ADDRESS_QUERY)) {
				addressStmt.setString(1, employeeId);
				addressStmt.executeUpdate();
			}

			// Delete from bank_details table
			try (PreparedStatement bankStmt = connection.prepareStatement(DELETE_BANK_DTL_QUERY)) {
				bankStmt.setString(1, employeeId);
				bankStmt.executeUpdate();
			}

			// Delete from employees table
			try (PreparedStatement employeeStmt = connection.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
				employeeStmt.setString(1, employeeId);
				employeeStmt.executeUpdate();
			}

			connection.commit();  // Commit transaction if all deletions succeed
			isDeleted = true;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();  // Rollback transaction in case of failure
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}


}
