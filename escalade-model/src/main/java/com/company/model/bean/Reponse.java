package com.company.model.bean;

public class Reponse {
	
	private Boolean isError = false;
	private String message;
	
	public Reponse(Boolean isError, String message) {
		this.isError = isError;
		this.message = message;
	}

	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
