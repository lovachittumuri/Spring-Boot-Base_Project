package com.sh.web.template.http.wrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestWrapper extends ContentCachingRequestWrapper {

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
	}

	public String getRequestUrl() {
		String url = super.getRequestURL().toString();
		System.out.println("Request Url:" + url);
		return url;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getPathParams() {
		Map<String, String> pathParamsMap = (Map<String, String>) super.getAttribute(
				HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println("Path params:" + pathParamsMap);
		return pathParamsMap;
	}

	public List<String> getQueryParamsAsList() {
		String queryString = super.getQueryString();
		List<String> queryParamsList = Arrays.asList(queryString.split("&"));
		return queryParamsList;
	}

	public Map<String, String> getQueryParamsAsMap() {
		String queryString = super.getQueryString();
		List<String> queryParamsList = Arrays.asList(queryString.split("&"));
		Map<String, String> queryParamsMap = new ConcurrentHashMap<String, String>();
		queryParamsList.forEach(queryParam -> {
			String[] queryParamsArray = queryParam.split("=");
			queryParamsMap.put(queryParamsArray[0], queryParamsArray[1]);
		});
		return queryParamsMap;
	}

	public List<String> getHeaderNamesAsList() {
		Enumeration<String> headerNames = super.getHeaderNames();
		List<String> headerNamesList = new CopyOnWriteArrayList<>();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerNamesList.add(headerName);
		}
		return headerNamesList;
	}

	public Map<String, String> getHeaders() {
		Map<String, String> headersMap = new ConcurrentHashMap<String, String>();
		List<String> headerNamesList = getHeaderNamesAsList();
		headerNamesList.forEach(headerName -> {
			headersMap.put(headerName, super.getHeader(headerName));
		});
		return headersMap;
	}

	public String getRequestBodyAsString() {
		try {
			byte[] contentAsByteArray = super.getContentAsByteArray();
			return new String(contentAsByteArray, 0, contentAsByteArray.length, super.getCharacterEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}