package com.livemore.model;

import java.io.Serializable;

public class ResponseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int  status;
	public String errorMsg;
	public Exception exception;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	

}
