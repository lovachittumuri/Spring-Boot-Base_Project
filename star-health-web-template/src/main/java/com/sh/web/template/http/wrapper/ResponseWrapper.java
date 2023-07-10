package com.sh.web.template.http.wrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.ContentCachingResponseWrapper;

public class ResponseWrapper extends ContentCachingResponseWrapper {

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public String getHeader(String name) {
		return super.getHeader(name);
	}

	public List<String> getHeaderNames() {
		Collection<String> headerNames = super.getHeaderNames();
		List<String> headerNamesList = new CopyOnWriteArrayList<>(headerNames);
		return headerNamesList;
	}
	
	public void setHeader(String name, String value) {
		super.setHeader(name, value);
	}
	
	public void setHeaders(Map<String, String> headersMap) {
		headersMap.entrySet().forEach(header -> {
			super.setHeader(header.getKey(), header.getValue());
		});
		System.out.println("HEADERS HAVE BEEN SET TO RESPONSE SUCCESSFULLY.");
	}

	public String getResponseBodyAsString() {
		try {
			byte[] contentAsByteArray = super.getContentAsByteArray();
			return new String(contentAsByteArray, 0, contentAsByteArray.length, super.getCharacterEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This method is used to modify the http response body.
	 * @author Parisapogu Venkat
	 * @param responseBody : Http Response body
	 * @since 1.0
	 */
	public void setResponseBody(String responseBody) throws IOException {
		// Clear the buffer which contained response body
		super.resetBuffer();
		// Write the new data into the output stream
		super.setContentLength(responseBody.length());
		super.setContentType(super.getContentType());
		super.getWriter().write(responseBody);
		// Commit the written data
		super.getWriter().flush();
	}

	public int getStatusCode() {
		return super.getStatus();
	}

	public void setStatusCode(int statusCode) {
		super.setStatus(statusCode);
	}

	public void commitResponse() throws IOException {
		super.copyBodyToResponse();
		System.out.println("HTTP RESPONSE COMMITTED.");
	}
}