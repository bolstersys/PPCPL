package com.hrms.servet.dao.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.hrms.servet.dao.EmployeeDao;
import com.hrms.servet.model.Employee;
import com.hrms.servet.model.Role;
import com.hrms.servet.util.Utility;

public class EmployeeDaoImpl implements EmployeeDao {


	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class.getName());

	private static final String GET_ALL_EMPLOYEE_QUERY = "SELECT * FROM employee";
	private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT employees.employee_id, employees.employee_first_name, employees.employee_middle_name, employees.employee_last_name, employees.role_id, employees.employee_age, employees.employee_dob, employees.employee_branch, employees.reporting_person_employee_id, employees.employee_ip_address FROM employees WHERE employees.employee_id = ?";

	private static final String GET_EMPLOYEE_DETAILS_BY_ID_QUERY = "SELECT * FROM employee e where e.employee_id = ? ";

	private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employee ("
			  + "name, category_of_employment, designation, department, reporting_to, date_of_joining, month, "
			  + "years_of_completion, approx_years_completion, probation_period, date_of_confirmation, location, "
			  + "date_of_birth, age_till_date, date_of_month, appointment_letter, nda, confirmation_or_probation, "
			  + "id_card, account, bank_account, ifsc_code, uan_number, pf_number, marital_status, "
			  + "gender, official_contact_no, personal_contact_no, alternate_contact_no, personal_email_id, official_email_id, "
			  + "blood_group, current_address, permanent_address, highest_qualification, total_experience, relevant_experience, "
			  + "previous_company, designation_in_previous_company, experience_with_ppt, date_of_resignation, retirement_date, "
			  + "exit_date, education_certificate, ration_card, pan_card, aadhar_card, driving_license, passport, bank_name, "
			  + "name_as_per_bank, account_number, ifsc_code_bank) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String INSERT_ADDRESS_QUERY = "INSERT INTO addresses (employee_id, address_line1, address_line2, city, state, country, pin_code, created_date, updated_date) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_BANK_DTL_QUERY = "INSERT INTO bank_details (employee_id, bank_name, bank_acc_no, ifsc_no, upi_id) VALUES (?,?,?,?,?)";

	private static final String UPDATE_EMPLOYEES_QUERY = "UPDATE employee SET name = ?, category_of_employment = ?, role_code = ?, department = ?, reporting_to = ?, date_of_joining = ?, month = ?, years_of_completion = ?, approx_years_completion = ?, probation_period = ?, date_of_confirmation = ?, location = ?, date_of_birth = ?, age_till_date = ?, date_of_month = ?, appointment_letter = ?, nda = ?, confirmation_or_probation = ?, id_card = ?, account = ?, bank_account = ?, ifsc_code = ?, uan_number = ?, pf_number = ?, marital_status = ?, gender = ?, official_contact_no = ?, personal_contact_no = ?, alternate_contact_no = ?, personal_email_id = ?, official_email_id = ?, blood_group = ?, current_address = ?, permanent_address = ?, highest_qualification = ?, total_experience = ?, relevant_experience = ?, previous_company = ?, designation_in_previous_company = ?, experience_with_ppt = ?, date_of_resignation = ?, retirement_date = ?, exit_date = ?, education_certificate = ?, ration_card = ?, pan_card = ?, aadhar_card = ?, driving_license = ?, passport = ?, bank_name = ?, name_as_per_bank = ?, account_number = ?, ifsc_code_bank = ? WHERE employee_id = ?";
	private static final String UPDATE_ADDRESS_QUERY = "UPDATE addresses SET address_line1 = ?, address_line2 = ?, city = ?, state = ?, country = ?, pin_code = ?, updated_date = ? WHERE employee_id = ?";
	private static final String UPDATE_BANK_DTL_QUERY = "UPDATE bank_details SET bank_name = ?, bank_acc_no = ?, ifsc_no = ?, upi_id = ? WHERE employee_id = ?";

	private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employee WHERE employee_id = ?";
	private static final String DELETE_ADDRESS_QUERY = "DELETE FROM addresses WHERE employee_id = ?";
	private static final String DELETE_BANK_DTL_QUERY = "DELETE FROM bank_details WHERE employee_id = ?";



	@Override
	public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		try (Connection connection = Utility.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_QUERY)) {

			System.out.println(preparedStatement);

			// Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Process the ResultSet object
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt("employee_id"));
				employee.setName(resultSet.getString("name"));
				employee.setCategoryOfEmployment(resultSet.getString("category_of_employment"));

				// Retrieve the roleCode (primary key) from the database
				String roleCode = resultSet.getString("designation");

				// Assuming you have a method to get Role by its code, e.g., Role.getRoleByCode(roleCode)
				if (roleCode != null) {
					Role role = (new RoleDaoImpl()).getRoleById(roleCode) != null ? (new RoleDaoImpl()).getRoleById(roleCode).get(0) : new Role(); // You need to implement this method
					employee.setDesignation(role);
				}

				employee.setDepartment(resultSet.getString("department"));
				employee.setReportingTo(resultSet.getString("reporting_to"));
				employee.setDateOfJoining(resultSet.getObject("date_of_joining", LocalDate.class));
				employee.setMonth(resultSet.getString("month"));
				employee.setYearsOfCompletion(resultSet.getFloat("years_of_completion"));
				employee.setApproxYearsCompletion(resultSet.getInt("approx_years_completion"));
				employee.setProbationPeriod(resultSet.getInt("probation_period"));
				employee.setDateOfConfirmation(resultSet.getObject("date_of_confirmation", LocalDate.class));
				employee.setLocation(resultSet.getString("location"));
				employee.setDateOfBirth(resultSet.getObject("date_of_birth", LocalDate.class));
				employee.setAgeTillDate(resultSet.getInt("age_till_date"));
				employee.setDateOfMonth(resultSet.getInt("date_of_month"));
				employee.setAppointmentLetter(resultSet.getString("appointment_letter"));
				employee.setNda(resultSet.getString("nda"));
				employee.setConfirmationOrProbation(resultSet.getString("confirmation_or_probation"));
				employee.setIdCard(resultSet.getString("id_card"));
				employee.setAccount(resultSet.getString("account"));
				employee.setBankAccount(resultSet.getString("bank_account"));
				employee.setIfscCode(resultSet.getString("ifsc_code"));
				employee.setUanNumber(resultSet.getString("uan_number"));
				employee.setPfNumber(resultSet.getString("pf_number"));
				employee.setMaritalStatus(resultSet.getString("marital_status"));
				employee.setGender(resultSet.getString("gender"));
				employee.setOfficialContactNo(resultSet.getString("official_contact_no"));
				employee.setPersonalContactNo(resultSet.getString("personal_contact_no"));
				employee.setAlternateContactNo(resultSet.getString("alternate_contact_no"));
				employee.setPersonalEmailId(resultSet.getString("personal_email_id"));
				employee.setOfficialEmailId(resultSet.getString("official_email_id"));
				employee.setBloodGroup(resultSet.getString("blood_group"));
				employee.setCurrentAddress(resultSet.getString("current_address"));
				employee.setPermanentAddress(resultSet.getString("permanent_address"));
				employee.setHighestQualification(resultSet.getString("highest_qualification"));
				employee.setTotalExperience(resultSet.getInt("total_experience"));
				employee.setRelevantExperience(resultSet.getInt("relevant_experience"));
				employee.setPreviousCompany(resultSet.getString("previous_company"));
				employee.setDesignationInPreviousCompany(resultSet.getString("designation_in_previous_company"));
				employee.setExperienceWithPPT(resultSet.getString("experience_with_ppt"));
				employee.setDateOfResignation(resultSet.getObject("date_of_resignation", LocalDate.class));
				employee.setRetirementDate(resultSet.getObject("retirement_date", LocalDate.class));
				employee.setExitDate(resultSet.getObject("exit_date", LocalDate.class));
				employee.setEducationCertificate(resultSet.getString("education_certificate"));
				employee.setRationCard(resultSet.getString("ration_card"));
				employee.setPanCard(resultSet.getString("pan_card"));
				employee.setAadharCard(resultSet.getString("aadhar_card"));
				employee.setDrivingLicense(resultSet.getString("driving_license"));
				employee.setPassport(resultSet.getString("passport"));
				employee.setBankName(resultSet.getString("bank_name"));
				employee.setNameAsPerBank(resultSet.getString("name_as_per_bank"));
				employee.setAccountNumber(resultSet.getString("account_number"));
				employee.setIfscCodeBank(resultSet.getString("ifsc_code_bank"));

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
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				employee = new Employee();
				employee.setEmployeeId(resultSet.getInt("employee_id"));
				employee.setName(resultSet.getString("name"));
				employee.setCategoryOfEmployment(resultSet.getString("category_of_employment"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setReportingTo(resultSet.getString("reporting_to"));
				employee.setDateOfJoining(resultSet.getObject("date_of_joining", LocalDate.class));
				employee.setMonth(resultSet.getString("month"));
				employee.setYearsOfCompletion(resultSet.getFloat("years_of_completion"));
				employee.setApproxYearsCompletion(resultSet.getInt("approx_years_completion"));
				employee.setProbationPeriod(resultSet.getInt("probation_period"));
				employee.setDateOfConfirmation(resultSet.getObject("date_of_confirmation", LocalDate.class));
				employee.setLocation(resultSet.getString("location"));
				employee.setDateOfBirth(resultSet.getObject("date_of_birth", LocalDate.class));
				employee.setAgeTillDate(resultSet.getInt("age_till_date"));
				employee.setDateOfMonth(resultSet.getInt("date_of_month"));
				employee.setAppointmentLetter(resultSet.getString("appointment_letter"));
				employee.setNda(resultSet.getString("nda"));
				employee.setConfirmationOrProbation(resultSet.getString("confirmation_or_probation"));
				employee.setIdCard(resultSet.getString("id_card"));
				employee.setAccount(resultSet.getString("account"));
				employee.setBankAccount(resultSet.getString("bank_account"));
				employee.setIfscCode(resultSet.getString("ifsc_code"));
				employee.setUanNumber(resultSet.getString("uan_number"));
				employee.setPfNumber(resultSet.getString("pf_number"));
				employee.setMaritalStatus(resultSet.getString("marital_status"));
				employee.setGender(resultSet.getString("gender"));
				employee.setOfficialContactNo(resultSet.getString("official_contact_no"));
				employee.setPersonalContactNo(resultSet.getString("personal_contact_no"));
				employee.setAlternateContactNo(resultSet.getString("alternate_contact_no"));
				employee.setPersonalEmailId(resultSet.getString("personal_email_id"));
				employee.setOfficialEmailId(resultSet.getString("official_email_id"));
				employee.setBloodGroup(resultSet.getString("blood_group"));
				employee.setCurrentAddress(resultSet.getString("current_address"));
				employee.setPermanentAddress(resultSet.getString("permanent_address"));
				employee.setHighestQualification(resultSet.getString("highest_qualification"));
				employee.setTotalExperience(resultSet.getInt("total_experience"));
				employee.setRelevantExperience(resultSet.getInt("relevant_experience"));
				employee.setPreviousCompany(resultSet.getString("previous_company"));
				employee.setDesignationInPreviousCompany(resultSet.getString("designation_in_previous_company"));
				employee.setExperienceWithPPT(resultSet.getString("experience_with_ppt"));
				employee.setDateOfResignation(resultSet.getObject("date_of_resignation", LocalDate.class));
				employee.setRetirementDate(resultSet.getObject("retirement_date", LocalDate.class));
				employee.setExitDate(resultSet.getObject("exit_date", LocalDate.class));
				employee.setEducationCertificate(resultSet.getString("education_certificate"));
				employee.setRationCard(resultSet.getString("ration_card"));
				employee.setPanCard(resultSet.getString("pan_card"));
				employee.setAadharCard(resultSet.getString("aadhar_card"));
				employee.setDrivingLicense(resultSet.getString("driving_license"));
				employee.setPassport(resultSet.getString("passport"));
				employee.setBankName(resultSet.getString("bank_name"));
				employee.setNameAsPerBank(resultSet.getString("name_as_per_bank"));
				employee.setAccountNumber(resultSet.getString("account_number"));
				employee.setIfscCodeBank(resultSet.getString("ifsc_code_bank"));

				// Process Role attributes
				Role role = new Role();
				role.setRoleCode(resultSet.getString("designation"));

				employee.setDesignation(role);
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

			// Insert into employees table and retrieve the generated employee_id
			try (PreparedStatement employeeStmt = connection.prepareStatement(INSERT_EMPLOYEE_QUERY)) {

				employeeStmt.setString(1, employee.getName());
				employeeStmt.setString(2, employee.getCategoryOfEmployment());
				employeeStmt.setString(3, employee.getDesignation().getRoleCode());
				employeeStmt.setString(4, employee.getDepartment());
				employeeStmt.setString(5, employee.getReportingTo());
				employeeStmt.setObject(6, employee.getDateOfJoining()); // Assuming LocalDate
				employeeStmt.setString(7, employee.getMonth());
				employeeStmt.setFloat(8, employee.getYearsOfCompletion());
				employeeStmt.setInt(9, employee.getApproxYearsCompletion());
				employeeStmt.setInt(10, employee.getProbationPeriod());
				employeeStmt.setObject(11, employee.getDateOfConfirmation());
				employeeStmt.setString(12, employee.getLocation());
				employeeStmt.setObject(13, employee.getDateOfBirth());
				employeeStmt.setInt(14, employee.getAgeTillDate());
				employeeStmt.setInt(15, employee.getDateOfMonth());
				employeeStmt.setString(16, employee.getAppointmentLetter());
				employeeStmt.setString(17, employee.getNda());
				employeeStmt.setString(18, employee.getConfirmationOrProbation());
				employeeStmt.setString(19, employee.getIdCard());
				employeeStmt.setString(20, employee.getAccount());
				employeeStmt.setString(21, employee.getBankAccount());
				employeeStmt.setString(22, employee.getIfscCode());
				employeeStmt.setString(23, employee.getUanNumber());
				employeeStmt.setString(24, employee.getPfNumber());
				employeeStmt.setString(25, employee.getMaritalStatus());
				employeeStmt.setString(26, employee.getGender());
				employeeStmt.setString(27, employee.getOfficialContactNo());
				employeeStmt.setString(28, employee.getPersonalContactNo());
				employeeStmt.setString(29, employee.getAlternateContactNo());
				employeeStmt.setString(30, employee.getPersonalEmailId());
				employeeStmt.setString(31, employee.getOfficialEmailId());
				employeeStmt.setString(32, employee.getBloodGroup());
				employeeStmt.setString(33, employee.getCurrentAddress());
				employeeStmt.setString(34, employee.getPermanentAddress());
				employeeStmt.setString(35, employee.getHighestQualification());
				employeeStmt.setInt(36, employee.getTotalExperience());
				employeeStmt.setInt(37, employee.getRelevantExperience());
				employeeStmt.setString(38, employee.getPreviousCompany());
				employeeStmt.setString(39, employee.getDesignationInPreviousCompany());
				employeeStmt.setString(40, employee.getExperienceWithPPT());
				employeeStmt.setObject(41, employee.getDateOfResignation());
				employeeStmt.setObject(42, employee.getRetirementDate());
				employeeStmt.setObject(43, employee.getExitDate());
				employeeStmt.setString(44, employee.getEducationCertificate());
				employeeStmt.setString(45, employee.getRationCard());
				employeeStmt.setString(46, employee.getPanCard());
				employeeStmt.setString(47, employee.getAadharCard());
				employeeStmt.setString(48, employee.getDrivingLicense());
				employeeStmt.setString(49, employee.getPassport());
				employeeStmt.setString(50, employee.getBankName());
				employeeStmt.setString(51, employee.getNameAsPerBank());
				employeeStmt.setString(52, employee.getAccountNumber());
				employeeStmt.setString(53, employee.getIfscCodeBank());


				int affectedRows = employeeStmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Inserting employee failed, no rows affected.");
				}


			}

//			// Set the generated employee ID to the employee object
//			employee.setEmployeeId(generatedEmployeeId);
//
//			// Insert into address table
//			try (PreparedStatement addressStmt = connection.prepareStatement(INSERT_ADDRESS_QUERY)) {
//				Address address = employee.getAddress();
//				addressStmt.setInt(1, generatedEmployeeId); // Relational key
//				addressStmt.setString(2, address.getAddressLine1());
//				addressStmt.setString(3, address.getAddressLine2());
//				addressStmt.setString(4, address.getCity());
//				addressStmt.setString(5, address.getState());
//				addressStmt.setString(6, address.getCountry());
//				addressStmt.setString(7, address.getPinCode());
//				addressStmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));  // created_date
//				addressStmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));  // updated_date
//
//				addressStmt.executeUpdate();
//			}
//
//			// Insert into bank details table
//			try (PreparedStatement bankStmt = connection.prepareStatement(INSERT_BANK_DTL_QUERY)) {
//				BankDetail bankDetail = employee.getBankDetail();
//				bankStmt.setInt(1, generatedEmployeeId); // Relational key
//				bankStmt.setString(2, bankDetail.getBankName());
//				bankStmt.setString(3, bankDetail.getBankAccNo());
//				bankStmt.setString(4, bankDetail.getIfscNo());
//				bankStmt.setString(5, bankDetail.getUpiId());
//
//				bankStmt.executeUpdate();
//			}

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
				employeeStmt.setString(1, employee.getName());
				employeeStmt.setString(2, employee.getCategoryOfEmployment());
				employeeStmt.setString(3, employee.getDesignation().getRoleCode()); // Assuming Role has a getRoleCode() method
				employeeStmt.setString(4, employee.getDepartment());
				employeeStmt.setString(5, employee.getReportingTo());
				employeeStmt.setObject(6, employee.getDateOfJoining()); // For LocalDate
				employeeStmt.setString(7, employee.getMonth());
				employeeStmt.setFloat(8, employee.getYearsOfCompletion());
				employeeStmt.setInt(9, employee.getApproxYearsCompletion());
				employeeStmt.setInt(10, employee.getProbationPeriod());
				employeeStmt.setObject(11, employee.getDateOfConfirmation());
				employeeStmt.setString(12, employee.getLocation());
				employeeStmt.setObject(13, employee.getDateOfBirth());
				employeeStmt.setInt(14, employee.getAgeTillDate());
				employeeStmt.setInt(15, employee.getDateOfMonth());
				employeeStmt.setString(16, employee.getAppointmentLetter());
				employeeStmt.setString(17, employee.getNda());
				employeeStmt.setString(18, employee.getConfirmationOrProbation());
				employeeStmt.setString(19, employee.getIdCard());
				employeeStmt.setString(20, employee.getAccount());
				employeeStmt.setString(21, employee.getBankAccount());
				employeeStmt.setString(22, employee.getIfscCode());
				employeeStmt.setString(23, employee.getUanNumber());
				employeeStmt.setString(24, employee.getPfNumber());
				employeeStmt.setString(25, employee.getMaritalStatus());
				employeeStmt.setString(26, employee.getGender());
				employeeStmt.setString(27, employee.getOfficialContactNo());
				employeeStmt.setString(28, employee.getPersonalContactNo());
				employeeStmt.setString(29, employee.getAlternateContactNo());
				employeeStmt.setString(30, employee.getPersonalEmailId());
				employeeStmt.setString(31, employee.getOfficialEmailId());
				employeeStmt.setString(32, employee.getBloodGroup());
				employeeStmt.setString(33, employee.getCurrentAddress());
				employeeStmt.setString(34, employee.getPermanentAddress());
				employeeStmt.setString(35, employee.getHighestQualification());
				employeeStmt.setInt(36, employee.getTotalExperience());
				employeeStmt.setInt(37, employee.getRelevantExperience());
				employeeStmt.setString(38, employee.getPreviousCompany());
				employeeStmt.setString(39, employee.getDesignationInPreviousCompany());
				employeeStmt.setString(40, employee.getExperienceWithPPT());
				employeeStmt.setObject(41, employee.getDateOfResignation());
				employeeStmt.setObject(42, employee.getRetirementDate());
				employeeStmt.setObject(43, employee.getExitDate());
				employeeStmt.setString(44, employee.getEducationCertificate());
				employeeStmt.setString(45, employee.getRationCard());
				employeeStmt.setString(46, employee.getPanCard());
				employeeStmt.setString(47, employee.getAadharCard());
				employeeStmt.setString(48, employee.getDrivingLicense());
				employeeStmt.setString(49, employee.getPassport());
				employeeStmt.setString(50, employee.getBankName());
				employeeStmt.setString(51, employee.getNameAsPerBank());
				employeeStmt.setString(52, employee.getAccountNumber());
				employeeStmt.setString(53, employee.getIfscCodeBank());

				// Set the employee_id to update the specific employee record
				employeeStmt.setInt(55, employee.getEmployeeId());  // Use employee_id in WHERE clause

				employeeStmt.executeUpdate();
			}

//			// Update address table
//			try (PreparedStatement addressStmt = connection.prepareStatement(UPDATE_ADDRESS_QUERY)) {
//				Address address = employee.getAddress();
//				addressStmt.setString(1, address.getAddressLine1());
//				addressStmt.setString(2, address.getAddressLine2());
//				addressStmt.setString(3, address.getCity());
//				addressStmt.setString(4, address.getState());
//				addressStmt.setString(5, address.getCountry());
//				addressStmt.setString(6, address.getPinCode());
//				addressStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));  // updated_date
//				addressStmt.setInt(8, employee.getEmployeeId());  // Use employee_id in WHERE clause
//
//				addressStmt.executeUpdate();
//			}
//
//			// Update bank details table
//			try (PreparedStatement bankStmt = connection.prepareStatement(UPDATE_BANK_DTL_QUERY)) {
//				BankDetail bankDetail = employee.getBankDetail();
//				bankStmt.setString(1, bankDetail.getBankName());
//				bankStmt.setString(2, bankDetail.getBankAccNo());
//				bankStmt.setString(3, bankDetail.getIfscNo());
//				bankStmt.setString(4, bankDetail.getUpiId());
//				bankStmt.setInt(5, employee.getEmployeeId());  // Use employee_id in WHERE clause
//
//				bankStmt.executeUpdate();
//			}

			connection.commit();  // Commit transaction if all updates succeed

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

//			// Delete from addresses table
//			try (PreparedStatement addressStmt = connection.prepareStatement(DELETE_ADDRESS_QUERY)) {
//				addressStmt.setString(1, employeeId);
//				addressStmt.executeUpdate();
//			}
//
//			// Delete from bank_details table
//			try (PreparedStatement bankStmt = connection.prepareStatement(DELETE_BANK_DTL_QUERY)) {
//				bankStmt.setString(1, employeeId);
//				bankStmt.executeUpdate();
//			}

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
