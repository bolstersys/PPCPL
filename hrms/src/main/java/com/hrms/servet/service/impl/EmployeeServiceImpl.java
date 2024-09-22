package com.hrms.servet.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDao employeeDao = new EmployeeDaoImpl();
	private RoleDao roleDao = new RoleDaoImpl();
	private static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

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
		try {
			Employee employee = new Employee();
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
				employee.setDateOfJoining(LocalDate.parse(dateOfJoining));
			}
			employee.setMonth(request.getParameter("month"));

			employee.setYearsOfCompletion(Float.parseFloat(request.getParameter("yearsOfCompletion")));
			employee.setApproxYearsCompletion(Integer.parseInt(request.getParameter("approxYrsCompletion")));
			employee.setProbationPeriod(Integer.parseInt(request.getParameter("probationPeriod")));
			employee.setDateOfConfirmation(LocalDate.parse(request.getParameter("doc")));
			employee.setLocation(request.getParameter("location"));

			String dateOfBirth = request.getParameter("dob");
			if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
				employee.setDateOfBirth(LocalDate.parse(dateOfBirth));
			}
			employee.setAgeTillDate(Integer.parseInt(request.getParameter("ageTillDate")));
			employee.setDateOfMonth(Integer.parseInt(request.getParameter("dateOfMonth")));
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
			employee.setTotalExperience(Integer.parseInt(request.getParameter("totalExp")));
			employee.setRelevantExperience(Integer.parseInt(request.getParameter("relevantExp")));
			employee.setPreviousCompany(request.getParameter("previousCompany"));
			employee.setDesignationInPreviousCompany(request.getParameter("designationInPreviousCompany"));
			employee.setExperienceWithPPT(request.getParameter("expWithPPT"));

			// Setting resignation and retirement dates
			String dateOfResignation = request.getParameter("DOR");
			if (dateOfResignation != null && !dateOfResignation.isEmpty()) {
				employee.setDateOfResignation(LocalDate.parse(dateOfResignation));
			}

			String retirementDate = request.getParameter("retirementDate");
			if (retirementDate != null && !retirementDate.isEmpty()) {
				employee.setRetirementDate(LocalDate.parse(retirementDate));
			}

			String exitDate = request.getParameter("exitDate");
			if (exitDate != null && !exitDate.isEmpty()) {
				employee.setExitDate(LocalDate.parse(exitDate));
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
			logger.severe("Error  in RoleServiceImpl --> insertRole "+e.getMessage());
		}
	}

	@Override
	public void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = new Employee();
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
			employee.setDateOfJoining(LocalDate.parse(dateOfJoining));
		}
		employee.setMonth(request.getParameter("month"));

		employee.setYearsOfCompletion(Float.parseFloat(request.getParameter("yearsOfCompletion")));
		employee.setApproxYearsCompletion(Integer.parseInt(request.getParameter("approxYrsCompletion")));
		employee.setProbationPeriod(Integer.parseInt(request.getParameter("probationPeriod")));
		employee.setDateOfConfirmation(LocalDate.parse(request.getParameter("doc")));
		employee.setLocation(request.getParameter("location"));

		String dateOfBirth = request.getParameter("dob");
		if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
			employee.setDateOfBirth(LocalDate.parse(dateOfBirth));
		}
		employee.setAgeTillDate(Integer.parseInt(request.getParameter("ageTillDate")));
		employee.setDateOfMonth(Integer.parseInt(request.getParameter("dateOfMonth")));
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
		employee.setTotalExperience(Integer.parseInt(request.getParameter("totalExp")));
		employee.setRelevantExperience(Integer.parseInt(request.getParameter("relevantExp")));
		employee.setPreviousCompany(request.getParameter("previousCompany"));
		employee.setDesignationInPreviousCompany(request.getParameter("designationInPreviousCompany"));
		employee.setExperienceWithPPT(request.getParameter("expWithPPT"));

		// Setting resignation and retirement dates
		String dateOfResignation = request.getParameter("DOR");
		if (dateOfResignation != null && !dateOfResignation.isEmpty()) {
			employee.setDateOfResignation(LocalDate.parse(dateOfResignation));
		}

		String retirementDate = request.getParameter("retirementDate");
		if (retirementDate != null && !retirementDate.isEmpty()) {
			employee.setRetirementDate(LocalDate.parse(retirementDate));
		}

		String exitDate = request.getParameter("exitDate");
		if (exitDate != null && !exitDate.isEmpty()) {
			employee.setExitDate(LocalDate.parse(exitDate));
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

			logger.severe("Error  in RoleServiceImpl --> updateEmployee "+e.getMessage());
		}
	}

	@Override
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
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
			logger.severe("Error  in RoleServiceImpl --> deleteEmployee "+e.getMessage());
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
}
