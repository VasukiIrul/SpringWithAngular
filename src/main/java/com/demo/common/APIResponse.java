package com.demo.common;

import org.springframework.http.HttpStatus;

public class APIResponse {

	private Object data;
	private Object error;
	private Integer status;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public APIResponse() {
		
		this.data = data;
		this.error = error;
		this.status = HttpStatus.OK.value();
	}
	

}
