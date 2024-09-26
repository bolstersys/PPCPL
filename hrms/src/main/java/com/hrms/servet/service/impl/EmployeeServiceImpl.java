package com.hrms.servet.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.hrms.servet.bean.ResponseBean;
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
	private RoleDao roleDao = new RoleDaoImpl();
	private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());
	private static final String[] requiredFields = {"name", "categoryOfEmployment", "designation", "department", "reportingTo", "doj", "probationPeriod",
			"location", "dob", "maritalStatus", "gender", "personalContactNo", "officialContactNo", "personalEmailId", "officialEmailId",
			"permanentAddress", "currentAddress", "highestQualification", "totalExp", "relevantExp", "bankName", "nameAsPerBank",
			"accountNumber", "ifscCodeBank"};

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
	public void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMessages = new ArrayList<>();
		if (!validateRequiredFields(request, requiredFields, errorMessages)) {
			logger.severe("All required fields are not present in EmployeeServiceImpl --> insertEmployee "+errorMessages);
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
			Employee employee = new Employee();
			employee.setName(request.getParameter("name"));
			employee.setCategoryOfEmployment(request.getParameter("categoryOfEmployment"));
			String roleCode = request.getParameter("designation");
			Role role = new Role();
			role.setRoleCode(roleCode);
			employee.setDesignation(role);
			employee.setDepartment(request.getParameter("department"));
			employee.setReportingTo(request.getParameter("reportingTo"));
			String dateOfJoining = request.getParameter("doj");
			if (dateOfJoining != null && !dateOfJoining.isEmpty()) {
				employee.setDateOfJoining(validateDate(dateOfJoining));
			}
			employee.setMonth(request.getParameter("month"));

			employee.setYearsOfCompletion(validateIFloat(request.getParameter("yearsOfCompletion")));
			employee.setApproxYearsCompletion(validateInteger(request.getParameter("approxYrsCompletion")));
			employee.setProbationPeriod(validateInteger(request.getParameter("probationPeriod")));
			employee.setDateOfConfirmation(validateDate(request.getParameter("doc")));
			employee.setLocation(request.getParameter("location"));

			String dateOfBirth = request.getParameter("dob");
			if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
				employee.setDateOfBirth(validateDate(dateOfBirth));
			}
			employee.setAgeTillDate(validateInteger(request.getParameter("ageTillDate")));
			employee.setDateOfMonth(validateInteger(request.getParameter("dateOfMonth")));
			employee.setAppointmentLetter(request.getParameter("appointmentLetter"));
			employee.setNda(request.getParameter("nda"));
			employee.setConfirmationOrProbation(request.getParameter("confProb"));
			employee.setIdCard(request.getParameter("idCard"));
			employee.setAccount(request.getParameter("account"));
			employee.setBankAccount(request.getParameter("bankAccount"));
			employee.setIfscCode(request.getParameter("ifscCode"));
			employee.setUanNumber(request.getParameter("UanNum"));
			employee.setPfNumber(request.getParameter("pfNum"));
			employee.setMaritalStatus(request.getParameter("maritalStatus"));
			employee.setGender(request.getParameter("gender"));
			employee.setOfficialContactNo(request.getParameter("officialContactNo"));
			employee.setPersonalContactNo(request.getParameter("personalContactNo"));
			employee.setAlternateContactNo(request.getParameter("alternateContactNo"));
			employee.setPersonalEmailId(request.getParameter("personalEmailId"));
			employee.setOfficialEmailId(request.getParameter("officialEmailId"));
			employee.setBloodGroup(request.getParameter("bloodGroup"));
			employee.setCurrentAddress(request.getParameter("currentAddress"));
			employee.setPermanentAddress(request.getParameter("permanentAddress"));
			employee.setHighestQualification(request.getParameter("highestQualification"));
			employee.setTotalExperience(validateInteger(request.getParameter("totalExp")));
			employee.setRelevantExperience(validateInteger(request.getParameter("relevantExp")));
			employee.setPreviousCompany(request.getParameter("previousCompany"));
			employee.setDesignationInPreviousCompany(request.getParameter("designationInPreviousCompany"));
			employee.setExperienceWithPPT(request.getParameter("expWithPPT"));
			String dateOfResignation = request.getParameter("DOR");
			if (dateOfResignation != null && !dateOfResignation.isEmpty()) {
				employee.setDateOfResignation(validateDate(dateOfResignation));
			}

			String retirementDate = request.getParameter("retirementDate");
			if (retirementDate != null && !retirementDate.isEmpty()) {
				employee.setRetirementDate(validateDate(retirementDate));
			}

			String exitDate = request.getParameter("exitDate");
			if (exitDate != null && !exitDate.isEmpty()) {
				employee.setExitDate(validateDate(exitDate));
			}

			employee.setEducationCertificate(request.getParameter("educationCertificate"));
			employee.setRationCard(request.getParameter("rationCard"));
			employee.setPanCard(request.getParameter("panCard"));
			employee.setAadharCard(request.getParameter("aadharCard"));
			employee.setDrivingLicense(request.getParameter("drivingLicense"));
			employee.setPassport(request.getParameter("passport"));
			employee.setBankName(request.getParameter("bankName"));
			employee.setNameAsPerBank(request.getParameter("nameAsPerBank"));
			employee.setAccountNumber(request.getParameter("accountNumber"));
			employee.setIfscCodeBank(request.getParameter("ifscCodeBank"));

			employeeDao.insertEmployee(employee);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Employee Created Successfully.");
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in EmployeeServiceImpl --> insertEmployee "+e.getMessage());
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setMessage("Failed to create employee: " + e.getMessage());
			responseBean.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMessages = new ArrayList<>();
		if (!validateRequiredFields(request, requiredFields, errorMessages)) {
			logger.severe("All required fields are not present in EmployeeServiceImpl --> insertEmployee "+errorMessages);
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
		Employee employee = new Employee();
		employee.setEmployeeId(validateInteger(request.getParameter("employeeId")));
		employee.setName(request.getParameter("name"));
		employee.setCategoryOfEmployment(request.getParameter("categoryOfEmployment"));
		String roleCode = request.getParameter("designation");
		Role role = new Role();
		role.setRoleCode(roleCode);
		employee.setDesignation(role); // Store Role object in designation

// Set department independently if needed
		employee.setDepartment(request.getParameter("department"));

// Set other attributes
		employee.setReportingTo(request.getParameter("reportingTo"));

// Setting date fields with appropriate parsing
		String dateOfJoining = request.getParameter("doj");
		if (dateOfJoining != null && !dateOfJoining.isEmpty()) {
			employee.setDateOfJoining(validateDate(dateOfJoining));
		}
		employee.setMonth(request.getParameter("month"));

		employee.setYearsOfCompletion(validateIFloat(request.getParameter("yearsOfCompletion")));
		employee.setApproxYearsCompletion(validateInteger(request.getParameter("approxYrsCompletion")));
		employee.setProbationPeriod(validateInteger(request.getParameter("probationPeriod")));
		employee.setDateOfConfirmation(validateDate(request.getParameter("doc")));
		employee.setLocation(request.getParameter("location"));

		String dateOfBirth = request.getParameter("dob");
		if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
			employee.setDateOfBirth(validateDate(dateOfBirth));
		}
		employee.setAgeTillDate(validateInteger(request.getParameter("ageTillDate")));
		employee.setDateOfMonth(validateInteger(request.getParameter("dateOfMonth")));
		employee.setAppointmentLetter(request.getParameter("appointmentLetter"));
		employee.setNda(request.getParameter("nda"));
		employee.setConfirmationOrProbation(request.getParameter("confProb"));
		employee.setIdCard(request.getParameter("idCard"));
		employee.setAccount(request.getParameter("account"));
		employee.setBankAccount(request.getParameter("bankAccount"));
		employee.setIfscCode(request.getParameter("ifscCode"));
		employee.setUanNumber(request.getParameter("UanNum"));
		employee.setPfNumber(request.getParameter("pfNum"));
		employee.setMaritalStatus(request.getParameter("maritalStatus"));
		employee.setGender(request.getParameter("gender"));
		employee.setOfficialContactNo(request.getParameter("officialContactNo"));
		employee.setPersonalContactNo(request.getParameter("personalContactNo"));
		employee.setAlternateContactNo(request.getParameter("alternateContactNo"));
		employee.setPersonalEmailId(request.getParameter("personalEmailId"));
		employee.setOfficialEmailId(request.getParameter("officialEmailId"));
		employee.setBloodGroup(request.getParameter("bloodGroup"));
		employee.setCurrentAddress(request.getParameter("currentAddress"));
		employee.setPermanentAddress(request.getParameter("permanentAddress"));
		employee.setHighestQualification(request.getParameter("highestQualification"));
		employee.setTotalExperience(validateInteger(request.getParameter("totalExp")));
		employee.setRelevantExperience(validateInteger(request.getParameter("relevantExp")));
		employee.setPreviousCompany(request.getParameter("previousCompany"));
		employee.setDesignationInPreviousCompany(request.getParameter("designationInPreviousCompany"));
		employee.setExperienceWithPPT(request.getParameter("expWithPPT"));

		// Setting resignation and retirement dates
		String dateOfResignation = request.getParameter("DOR");
		if (dateOfResignation != null && !dateOfResignation.isEmpty()) {
			employee.setDateOfResignation(validateDate(dateOfResignation));
		}

		String retirementDate = request.getParameter("retirementDate");
		if (retirementDate != null && !retirementDate.isEmpty()) {
			employee.setRetirementDate(validateDate(retirementDate));
		}

		String exitDate = request.getParameter("exitDate");
		if (exitDate != null && !exitDate.isEmpty()) {
			employee.setExitDate(validateDate(exitDate));
		}

		employee.setEducationCertificate(request.getParameter("educationCertificate"));
		employee.setRationCard(request.getParameter("rationCard"));
		employee.setPanCard(request.getParameter("panCard"));
		employee.setAadharCard(request.getParameter("aadharCard"));
		employee.setDrivingLicense(request.getParameter("drivingLicense"));
		employee.setPassport(request.getParameter("passport"));
		employee.setBankName(request.getParameter("bankName"));
		employee.setNameAsPerBank(request.getParameter("nameAsPerBank"));
		employee.setAccountNumber(request.getParameter("accountNumber"));
		employee.setIfscCodeBank(request.getParameter("ifscCodeBank"));

		try {
			employeeDao.updateEmployee(employee);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Employee Updated Successfully.");
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in EmployeeServiceImpl --> updateEmployee "+e.getMessage());
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setMessage("Failed to create employee: " + e.getMessage());
			responseBean.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeId = request.getParameter("employeeId");

		try {
			employeeDao.deleteEmployee(employeeId);
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(true);
			responseBean.setMessage("Employee Deleted Successfully.");
			List<Employee> employeeList = employeeDao.getAllEmployee();
			List<Role> roleList = roleDao.getAllRoles();
			request.setAttribute("employeeList", employeeList);
			request.setAttribute("roleList", roleList);
			request.setAttribute("action", "getAllEmployeeData");
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee/employee.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.severe("Error  in EmployeeServiceImpl --> deleteEmployee "+e.getMessage());
			ResponseBean responseBean = new ResponseBean();
			responseBean.setSuccess(false);
			responseBean.setMessage("Failed to create employee: " + e.getMessage());
			responseBean.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			request.setAttribute("action", "ajaxCommonResponse");
			Gson gson = new Gson();
			request.setAttribute("message", gson.toJson(responseBean));
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/common/ajax.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	public void loadEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			String action = request.getParameter("action");
			if(action.equals("updateEmployee")){
				String employeeId = request.getParameter("employeeId");
				if(employeeId != null){
					Employee employee = employeeDao.getEmployeeById(employeeId);
					request.setAttribute("selected", employee);
				}else{
					action = "insertEmployee";
				}

				logger.severe("Error  in RoleServiceImpl --> loadEmployeeForm "+employeeId);
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
	public static LocalDate validateDate(String dateStr) {
		try {
            return LocalDate.parse(dateStr);
		} catch (DateTimeParseException e) {
			return LocalDate.now();
		}
	}
	public static int validateInteger(String number) {
		try {
			return Integer.parseInt(number);
		} catch (Exception e) {
			return 0;
		}
	}
	public static float validateIFloat(String number) {
		try {
			return Float.parseFloat(number);
		} catch (Exception e) {
			return 1.1f;
		}
	}
	public static boolean validateRequiredFields(HttpServletRequest request, String[] fieldNames, List<String> errorMessages) {
		boolean isValid = true;

		for (String fieldName : fieldNames) {
			String value = request.getParameter(fieldName);
			if (value == null || value.trim().isEmpty() || value.equalsIgnoreCase("nan")) {
				isValid = false;
				errorMessages.add(fieldName + " is required");
			}
		}
		return isValid;
	}
}
