package com.odeal.automation.exceptions;

import java.io.Serializable;

public class ApiErrorResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6868000782083203012L;
	private int errCode;
	private String errorMessage;
	
	public ApiErrorResponse(int errCode, String errorMessage) {
		super();
		this.errCode = errCode;
		this.errorMessage = errorMessage;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
