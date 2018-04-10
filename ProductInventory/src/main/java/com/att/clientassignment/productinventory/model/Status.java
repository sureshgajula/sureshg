package com.att.clientassignment.productinventory.model;


public class Status {
	
	private int statusCode;
	private String statusDesc;	
	private String statusMessage;
	

	public Status() {
		
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "Status [statusCode=" + statusCode + ", statusDesc=" + statusDesc + ", statusMessage=" + statusMessage
				+ "]";
	}
	
	

}
