package com.sh.web.template.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class RequestContext<T> {
	
	private Map<String, String> queryParamsMap;

	private Map<String, String> pathParamsMap;

	private Map<String, String> headersMap;

	private String httpMethod;

	private String requestUrl;

	private String queryString;

	private T requestBody;

	public String getHeader(String headerName) {
		return headersMap.get(headerName.toLowerCase());
	}

	public String getPathParam(String pathParamName) {
		return pathParamsMap.get(pathParamName);
	}

	public String getQueryParam(String queryParamName) {
		return queryParamsMap.get(queryParamName);
	}
}