package com.sh.web.template.exception;

public class InvalidSumAssuredException extends BusinessException {
	private static final long serialVersionUID = 430725576589214029L;
	
	public InvalidSumAssuredException(String errorDescription) {
		super(errorDescription);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvalidSumAssuredException [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
