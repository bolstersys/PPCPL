package com.hrms.servet.model;

import java.time.LocalDate;

public class Employee {

	private Integer employeeId;
	private String name;
	private String categoryOfEmployment;
	private Role designation;
	private String department;
	private String reportingTo;
	private LocalDate dateOfJoining; // DOJ
	private String month; // Month
	private float yearsOfCompletion; // Years Of Completion
	private int approxYearsCompletion; // Approx Yrs Completion
	private int probationPeriod; // Probation Period
	private LocalDate dateOfConfirmation; // DOC
	private String location;
	private LocalDate dateOfBirth; // DOB
	private int ageTillDate; // Age Till Date
	private int dateOfMonth; // Date Of Month
	private String appointmentLetter; // Appointment Letter
	private String nda; // NDA
	private String confirmationOrProbation; // Conformation/Probation
	private String idCard; // ID Card
	private String kotakAccount; // Kotak Account
	private String axisAccount; // Axis Account
	private String bankAccount; // Bank Account
	private String ifscCode; // IFSC Code
	private String uanNumber; // UAN NUM
	private String pfNumber; // PF NUM
	private String maritalStatus; // Married/UnMarried
	private String gender;
	private String officialContactNo; // Official Contact No.
	private String personalContactNo; // Personal No
	private String alternateContactNo; // Alternate No
	private String personalEmailId; // Personal Email ID
	private String officialEmailId; // Official Email ID
	private String bloodGroup;
	private String currentAddress;
	private String permanentAddress;
	private String highestQualification;
	private int totalExperience; // Total Exp
	private int relevantExperience; // Relevant Exp
	private String previousCompany;
	private String designationInPreviousCompany; // Designation In Previous Company
	private String experienceWithPPT; // Exp With PPT
	private LocalDate dateOfResignation; // DOR
	private LocalDate retirementDate;
	private LocalDate exitDate;
	private String educationCertificate; // Education Certificate
	private String rationCard; // Ration Card
	private String panCard; // PAN Card
	private String aadharCard; // Aadhar Card
	private String drivingLicense; // Driving License
	private String passport; // Passport
	private String bankName; // Bank Name
	private String nameAsPerBank; // Name As Per Bank
	private String accountNumber; // Account No
	private String ifscCodeBank; // IFSC Code

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryOfEmployment() {
		return categoryOfEmployment;
	}

	public void setCategoryOfEmployment(String categoryOfEmployment) {
		this.categoryOfEmployment = categoryOfEmployment;
	}

	public Role getDesignation() {
		return designation;
	}

	public void setDesignation(Role designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getYearsOfCompletion() {
		return yearsOfCompletion;
	}

	public void setYearsOfCompletion(float yearsOfCompletion) {
		this.yearsOfCompletion = yearsOfCompletion;
	}

	public int getApproxYearsCompletion() {
		return approxYearsCompletion;
	}

	public void setApproxYearsCompletion(int approxYearsCompletion) {
		this.approxYearsCompletion = approxYearsCompletion;
	}

	public int getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(int probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public LocalDate getDateOfConfirmation() {
		return dateOfConfirmation;
	}

	public void setDateOfConfirmation(LocalDate dateOfConfirmation) {
		this.dateOfConfirmation = dateOfConfirmation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAgeTillDate() {
		return ageTillDate;
	}

	public void setAgeTillDate(int ageTillDate) {
		this.ageTillDate = ageTillDate;
	}

	public int getDateOfMonth() {
		return dateOfMonth;
	}

	public void setDateOfMonth(int dateOfMonth) {
		this.dateOfMonth = dateOfMonth;
	}

	public String getAppointmentLetter() {
		return appointmentLetter;
	}

	public void setAppointmentLetter(String appointmentLetter) {
		this.appointmentLetter = appointmentLetter;
	}

	public String getNda() {
		return nda;
	}

	public void setNda(String nda) {
		this.nda = nda;
	}

	public String getConfirmationOrProbation() {
		return confirmationOrProbation;
	}

	public void setConfirmationOrProbation(String confirmationOrProbation) {
		this.confirmationOrProbation = confirmationOrProbation;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getKotakAccount() {
		return kotakAccount;
	}

	public void setKotakAccount(String kotakAccount) {
		this.kotakAccount = kotakAccount;
	}

	public String getAxisAccount() {
		return axisAccount;
	}

	public void setAxisAccount(String axisAccount) {
		this.axisAccount = axisAccount;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOfficialContactNo() {
		return officialContactNo;
	}

	public void setOfficialContactNo(String officialContactNo) {
		this.officialContactNo = officialContactNo;
	}

	public String getPersonalContactNo() {
		return personalContactNo;
	}

	public void setPersonalContactNo(String personalContactNo) {
		this.personalContactNo = personalContactNo;
	}

	public String getAlternateContactNo() {
		return alternateContactNo;
	}

	public void setAlternateContactNo(String alternateContactNo) {
		this.alternateContactNo = alternateContactNo;
	}

	public String getPersonalEmailId() {
		return personalEmailId;
	}

	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

	public String getOfficialEmailId() {
		return officialEmailId;
	}

	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public int getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}

	public int getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(int relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public String getPreviousCompany() {
		return previousCompany;
	}

	public void setPreviousCompany(String previousCompany) {
		this.previousCompany = previousCompany;
	}

	public String getDesignationInPreviousCompany() {
		return designationInPreviousCompany;
	}

	public void setDesignationInPreviousCompany(String designationInPreviousCompany) {
		this.designationInPreviousCompany = designationInPreviousCompany;
	}

	public String getExperienceWithPPT() {
		return experienceWithPPT;
	}

	public void setExperienceWithPPT(String experienceWithPPT) {
		this.experienceWithPPT = experienceWithPPT;
	}

	public LocalDate getDateOfResignation() {
		return dateOfResignation;
	}

	public void setDateOfResignation(LocalDate dateOfResignation) {
		this.dateOfResignation = dateOfResignation;
	}

	public LocalDate getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(LocalDate retirementDate) {
		this.retirementDate = retirementDate;
	}

	public LocalDate getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}

	public String getEducationCertificate() {
		return educationCertificate;
	}

	public void setEducationCertificate(String educationCertificate) {
		this.educationCertificate = educationCertificate;
	}

	public String getRationCard() {
		return rationCard;
	}

	public void setRationCard(String rationCard) {
		this.rationCard = rationCard;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNameAsPerBank() {
		return nameAsPerBank;
	}

	public void setNameAsPerBank(String nameAsPerBank) {
		this.nameAsPerBank = nameAsPerBank;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCodeBank() {
		return ifscCodeBank;
	}

	public void setIfscCodeBank(String ifscCodeBank) {
		this.ifscCodeBank = ifscCodeBank;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"employeeId=" + employeeId +
				", name='" + name + '\'' +
				", categoryOfEmployment='" + categoryOfEmployment + '\'' +
				", designation=" + designation +
				", department='" + department + '\'' +
				", reportingTo='" + reportingTo + '\'' +
				", dateOfJoining=" + dateOfJoining +
				", month='" + month + '\'' +
				", yearsOfCompletion=" + yearsOfCompletion +
				", approxYearsCompletion=" + approxYearsCompletion +
				", probationPeriod=" + probationPeriod +
				", dateOfConfirmation=" + dateOfConfirmation +
				", location='" + location + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", ageTillDate=" + ageTillDate +
				", dateOfMonth=" + dateOfMonth +
				", appointmentLetter='" + appointmentLetter + '\'' +
				", nda='" + nda + '\'' +
				", confirmationOrProbation='" + confirmationOrProbation + '\'' +
				", idCard='" + idCard + '\'' +
				", kotakAccount='" + kotakAccount + '\'' +
				", axisAccount='" + axisAccount + '\'' +
				", bankAccount='" + bankAccount + '\'' +
				", ifscCode='" + ifscCode + '\'' +
				", uanNumber='" + uanNumber + '\'' +
				", pfNumber='" + pfNumber + '\'' +
				", maritalStatus='" + maritalStatus + '\'' +
				", gender='" + gender + '\'' +
				", officialContactNo='" + officialContactNo + '\'' +
				", personalContactNo='" + personalContactNo + '\'' +
				", alternateContactNo='" + alternateContactNo + '\'' +
				", personalEmailId='" + personalEmailId + '\'' +
				", officialEmailId='" + officialEmailId + '\'' +
				", bloodGroup='" + bloodGroup + '\'' +
				", currentAddress='" + currentAddress + '\'' +
				", permanentAddress='" + permanentAddress + '\'' +
				", highestQualification='" + highestQualification + '\'' +
				", totalExperience=" + totalExperience +
				", relevantExperience=" + relevantExperience +
				", previousCompany='" + previousCompany + '\'' +
				", designationInPreviousCompany='" + designationInPreviousCompany + '\'' +
				", experienceWithPPT='" + experienceWithPPT + '\'' +
				", dateOfResignation=" + dateOfResignation +
				", retirementDate=" + retirementDate +
				", exitDate=" + exitDate +
				", educationCertificate='" + educationCertificate + '\'' +
				", rationCard='" + rationCard + '\'' +
				", panCard='" + panCard + '\'' +
				", aadharCard='" + aadharCard + '\'' +
				", drivingLicense='" + drivingLicense + '\'' +
				", passport='" + passport + '\'' +
				", bankName='" + bankName + '\'' +
				", nameAsPerBank='" + nameAsPerBank + '\'' +
				", accountNumber='" + accountNumber + '\'' +
				", ifscCodeBank='" + ifscCodeBank + '\'' +
				'}';
	}
}
