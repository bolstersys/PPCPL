package com.hrms.servet.model;

public class Employee {

	private Integer employeeId;
	private String employeeFirstName;
	private String employeeMiddleName;
	private String employeeLastName;
	private Role role;
	private String employeeAge;
	private String employeeDob;
	private String employeeBranch;	
	private Address address;
	private String reportingPersonEmployeeId;
	private String employeeIpAddress;
	private BankDetail bankDetail;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeMiddleName() {
		return employeeMiddleName;
	}
	public void setEmployeeMiddleName(String employeeMiddleName) {
		this.employeeMiddleName = employeeMiddleName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getEmployeeAge() {
		return employeeAge;
	}
	public void setEmployeeAge(String employeeAge) {
		this.employeeAge = employeeAge;
	}
	public String getEmployeeDob() {
		return employeeDob;
	}
	public void setEmployeeDob(String employeeDob) {
		this.employeeDob = employeeDob;
	}
	public String getEmployeeBranch() {
		return employeeBranch;
	}
	public void setEmployeeBranch(String employeeBranch) {
		this.employeeBranch = employeeBranch;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getReportingPersonEmployeeId() {
		return reportingPersonEmployeeId;
	}
	public void setReportingPersonEmployeeId(String reportingPersonEmployeeId) {
		this.reportingPersonEmployeeId = reportingPersonEmployeeId;
	}
	public String getEmployeeIpAddress() {
		return employeeIpAddress;
	}
	public void setEmployeeIpAddress(String employeeIpAddress) {
		this.employeeIpAddress = employeeIpAddress;
	}
	public BankDetail getBankDetail() {
		return bankDetail;
	}
	public void setBankDetail(BankDetail bankDetail) {
		this.bankDetail = bankDetail;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [employeeId=");
		builder.append(employeeId);
		builder.append(", employeeFirstName=");
		builder.append(employeeFirstName);
		builder.append(", employeeMiddleName=");
		builder.append(employeeMiddleName);
		builder.append(", employeeLastName=");
		builder.append(employeeLastName);
		builder.append(", role=");
		builder.append(role);
		builder.append(", employeeAge=");
		builder.append(employeeAge);
		builder.append(", employeeDob=");
		builder.append(employeeDob);
		builder.append(", employeeBranch=");
		builder.append(employeeBranch);
		builder.append(", address=");
		builder.append(address);
		builder.append(", reportingPersonEmployeeId=");
		builder.append(reportingPersonEmployeeId);
		builder.append(", employeeIpAddress=");
		builder.append(employeeIpAddress);
		builder.append(", bankDetail=");
		builder.append(bankDetail);
		builder.append("]");
		return builder.toString();
	}
	
}
