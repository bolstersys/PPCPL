package com.hrms.servet.model;

public class BankDetail {
	private String srNo;
	private String bankName;
	private String bankAccNo;
	private String ifscNo;
	private String upiId;

	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public String getIfscNo() {
		return ifscNo;
	}
	public void setIfscNo(String ifscNo) {
		this.ifscNo = ifscNo;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankDetail [srNo=");
		builder.append(srNo);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", bankAccNo=");
		builder.append(bankAccNo);
		builder.append(", ifscNo=");
		builder.append(ifscNo);
		builder.append(", upiId=");
		builder.append(upiId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
