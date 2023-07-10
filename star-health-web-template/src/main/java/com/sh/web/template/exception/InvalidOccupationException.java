package com.sh.web.template.exception;

public class InvalidOccupationException extends BusinessException {

	private static final long serialVersionUID = -1056162139495047146L;

	public InvalidOccupationException(String errorDescription) {
		super(errorDescription);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvalidOccupationException [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
