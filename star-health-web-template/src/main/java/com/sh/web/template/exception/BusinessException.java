package com.sh.web.template.exception;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 7826287480447697407L;
	private String errorDescription;

	public BusinessException(String errorDescription) {
		super();
		this.errorDescription = errorDescription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessException [errorDescription=");
		builder.append(errorDescription);
		builder.append("]");
		return builder.toString();
	}
}
