package com.sh.web.template.model;

import java.io.Serializable;

public class StarHealthRestClientResponse<R,ER> implements Serializable {
	private static final long serialVersionUID = -7888934067542056836L;
	private R successResponse;
	private ER errorResponse;
	
	public R getSuccessResponse() {
		return successResponse;
	}
	public void setSuccessResponse(R successResponse) {
		this.successResponse = successResponse;
	}
	public ER getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ER errorResponse) {
		this.errorResponse = errorResponse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StarHealthRestClientResponse [successResponse=");
		builder.append(successResponse);
		builder.append(", errorResponse=");
		builder.append(errorResponse);
		builder.append("]");
		return builder.toString();
	}
}
