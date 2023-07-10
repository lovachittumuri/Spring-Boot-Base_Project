package com.sh.web.template.util;

import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Validated
public class RequestContextValidator {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	public <T> void validateRequestBody(@Valid T requestBody) {
		// NO-Operations
	}

	@SuppressWarnings("unchecked")
	public boolean validateMandatoryHeaders(Map<String, String> mandatoryHeadersMap) {
		String headersStr = environment.getProperty("mandatory-headers");
		Map<String,String> mandatoryHeaders=null; 
		if(headersStr!=null) {
			System.out.println("Mandatory headers:"+headersStr);
			try {
				mandatoryHeaders = mapper.readValue(headersStr, Map.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			return false;
		}
		
		int count = 0;
		for (Map.Entry<String, String> entry : mandatoryHeaders.entrySet()) {
			String key1 = entry.getKey().toLowerCase();
			String key2 = null;
			for (Map.Entry<String, String> ent : mandatoryHeadersMap.entrySet()) {
				key2 = ent.getKey().toLowerCase();
				if (key1.equalsIgnoreCase(key2)) {
					count++;
					break;
				}
			}
			if (count == 0) {
				throw new RuntimeException(key1 + " is mandatory.");
			} else {
				String value1 = mandatoryHeadersMap.get(key2);
				String value2 = entry.getValue();
				String[] array = value2.split(";");
				String regex = array[0];
				String message = array[1];
				boolean flag = Pattern.compile(regex).matcher(value1).matches();
				if (!flag) {
					throw new RuntimeException(key1 + ":" + message);
				}
			}
			count = 0;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean validateNonMandatoryHeaders(Map<String, String> nonMandatoryHeadersMap) {
		String headersStr = environment.getProperty("nonmandatory-headers");
		Map<String,String> nonMandatoryHeaders=null; 
		if(headersStr!=null) {
			System.out.println("Non-mandatory headers:"+headersStr);
			try {
				nonMandatoryHeaders = mapper.readValue(headersStr, Map.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			return false;
		}
		int count = 0;
		for (Map.Entry<String, String> entry : nonMandatoryHeaders.entrySet()) {
			String key1 = entry.getKey().toLowerCase();
			String key2 = null;
			for (Map.Entry<String, String> ent : nonMandatoryHeadersMap.entrySet()) {
				key2 = ent.getKey().toLowerCase();
				if (key1.equalsIgnoreCase(key2)) {
					count++;
					break;
				}
			}
			if (count > 0) {
				String value1 = nonMandatoryHeadersMap.get(key2);
				String value2 = entry.getValue();
				String[] array = value2.split(";");
				String regex = array[0];
				String message = array[1];
				boolean flag = Pattern.compile(regex).matcher(value1).matches();
				if (!flag) {
					throw new RuntimeException(key1 + ":" + message);
				}
			}
			count = 0;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean validateMandatoryQueryParams(Map<String, String> mandatoryQueryParamsMap) {
		String queryParams = environment.getProperty("mandatory-query-params");
		Map<String,String> mandatoryQueryParams=null; 
		if(queryParams!=null) {
			System.out.println("Mandatory Query params:"+queryParams);
			try {
				mandatoryQueryParams = mapper.readValue(queryParams, Map.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			return false;
		}
		int count = 0;
		for (Map.Entry<String, String> entry : mandatoryQueryParams.entrySet()) {
			String key1 = entry.getKey();
			String key2 = null;
			for (Map.Entry<String, String> ent : mandatoryQueryParamsMap.entrySet()) {
				key2 = ent.getKey();
				if (key1.equalsIgnoreCase(key2)) {
					count++;
					break;
				}
			}
			if (count == 0) {
				throw new RuntimeException(key1 + " is mandatory.");
			} else {
				String value1 = mandatoryQueryParamsMap.get(key2);
				String value2 = entry.getValue();
				String[] array = value2.split(";");
				String regex = array[0];
				String message = array[1];
				boolean flag = Pattern.compile(regex).matcher(value1).matches();
				if (!flag) {
					throw new RuntimeException(key1 + ":" + message);
				}
			}
			count = 0;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean validateNonMandatoryQueryParams(Map<String, String> nonMandatoryQueryParamsMap) {
		String queryParams = environment.getProperty("nonmandatory-query-params");
		Map<String,String> nonMandatoryQueryParams=null; 
		if(queryParams!=null) {
			System.out.println("Non-mandatory Query params:"+queryParams);
			try {
				nonMandatoryQueryParams = mapper.readValue(queryParams, Map.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			return false;
		}
		int count = 0;
		for (Map.Entry<String, String> entry : nonMandatoryQueryParams.entrySet()) {
			String key1 = entry.getKey();
			String key2 = null;
			for (Map.Entry<String, String> ent : nonMandatoryQueryParamsMap.entrySet()) {
				key2 = ent.getKey();
				if (key1.equalsIgnoreCase(key2)) {
					count++;
					break;
				}
			}
			if (count > 0) {
				String value1 = nonMandatoryQueryParamsMap.get(key2);
				String value2 = entry.getValue();
				String[] array = value2.split(";");
				String regex = array[0];
				String message = array[1];
				boolean flag = Pattern.compile(regex).matcher(value1).matches();
				if (!flag) {
					throw new RuntimeException(key1 + ":" + message);
				}
			}
			count = 0;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean validatePathParams(Map<String, String> pathParamsMap) {
		String params = environment.getProperty("path-params");
		Map<String,String> pathParams=null; 
		if(params!=null) {
			System.out.println("Path params:"+params);
			try {
				pathParams = mapper.readValue(params, Map.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			return false;
		}
		int count = 0;
		for (Map.Entry<String, String> entry : pathParams.entrySet()) {
			String key1 = entry.getKey();
			String key2 = null;
			for (Map.Entry<String, String> ent : pathParamsMap.entrySet()) {
				key2 = ent.getKey();
				if (key1.equalsIgnoreCase(key2)) {
					count++;
					break;
				}
			}
			if (count > 0) {
				String value1 = pathParamsMap.get(key2);
				String value2 = entry.getValue();
				String[] array = value2.split(";");
				String regex = array[0];
				String message = array[1];
				boolean flag = Pattern.compile(regex).matcher(value1).matches();
				if (!flag) {
					throw new RuntimeException(key1 + ":" + message);
				}
			}
			count = 0;
		}
		return true;
	}
}