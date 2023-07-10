package com.sh.web.template.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@Component
@JsonInclude(Include.NON_NULL)
public class StarHealthErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = -4662815242713239089L;
	private Integer statusCode;
	private String status;
	private List<String> errorMessage;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponseDTO [statusCode=");
		builder.append(statusCode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}
}
