package com.sh.web.template.exception;

public class StarHealthBadRequestException extends BusinessException {
	private static final long serialVersionUID = 7842748424992920701L;

	public StarHealthBadRequestException(String errorDescription) {
		super(errorDescription);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StarHealthBadRequestException [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
