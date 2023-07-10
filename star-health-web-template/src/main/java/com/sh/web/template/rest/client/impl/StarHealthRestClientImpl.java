package com.sh.web.template.rest.client.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.web.template.model.StarHealthRestClientResponse;
import com.sh.web.template.rest.client.StarHealthRestClient;

@Component
public final class StarHealthRestClientImpl<T, R, ER> implements StarHealthRestClient<T, R, ER> {
	@Autowired
	private RestTemplate restTemplate;

	public StarHealthRestClientImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	public StarHealthRestClientResponse<R, ER> postMethod(String url, T requestBody, Class<R> responseType,
			Map<String, String> uriVariables, Map<String, List<String>> multipleHeaderValuesMap,
			Class<ER> errorResponseType) {
		if (null != url && null != requestBody && null != responseType && null != uriVariables
				&& null != multipleHeaderValuesMap && null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			R response = null;
			HttpHeaders headers = new HttpHeaders();
			// Set headers
			headers.putAll(multipleHeaderValuesMap);
			HttpEntity<T> entity = new HttpEntity<T>(requestBody, headers);
			StarHealthRestClientResponse<R, ER> successResponse = null;
			try {
				responseEntity = restTemplate.postForEntity(url, entity, responseType, uriVariables);
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Map<String, String> headersMap,
			Class<R> responseType, Class<ER> errorResponseType) {
		if (null != url && null != requestBody && null != responseType && null != headersMap
				&& null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			R response = null;
			StarHealthRestClientResponse<R, ER> successResponse = null;
			// Set headers
			HttpHeaders httpHeaders = new HttpHeaders();
			Set<Entry<String, String>> set = headersMap.entrySet();
			Stream<Entry<String, String>> stream = set.stream();
			stream.forEach(me -> {
				httpHeaders.add(me.getKey(), me.getValue());
			});
			// Set headers & request body to http entity
			HttpEntity<T> entity = new HttpEntity<>(requestBody, httpHeaders);
			try {
				// Call rest provider and get response
				responseEntity = restTemplate.postForEntity(url, entity, responseType);
				// Process response entity
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Class<R> responseType,
			Class<ER> errorResponseType) {
		if (null != url && null != requestBody && null != responseType && null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			R response = null;
			StarHealthRestClientResponse<R, ER> successResponse = null;
			try {
				responseEntity = restTemplate.postForEntity(url, requestBody, responseType);
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Class<R> responseType,
			Map<String, String> uriVariables, Class<ER> errorResponseType) {
		if (null != url && null != requestBody && null != responseType && null != uriVariables
				&& null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			R response = null;
			StarHealthRestClientResponse<R, ER> successResponse = null;
			try {
				responseEntity = restTemplate.postForEntity(url, requestBody, responseType, uriVariables);
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Class<R> responseType,
			Map<String, String> uriVariables, Map<String, String> headersMap, Class<ER> errorResponseType) {
		if (null != url & null != requestBody & null != responseType & null != uriVariables & null != headersMap
				&& null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			HttpHeaders headers = new HttpHeaders();
			Map<String, List<String>> headerMap = new ConcurrentHashMap<String, List<String>>();
			// Iterate Map
			Set<Entry<String, String>> entrySet = headersMap.entrySet();
			Stream<Entry<String, String>> stream = entrySet.stream();
			stream.forEach(s -> {
				List<String> headerList = new CopyOnWriteArrayList<String>();
				headerList.add(s.getValue());
				headerMap.put(s.getKey(), headerList);
			});
			// Set headers
			headers.putAll(headerMap);
			HttpEntity<T> entity = new HttpEntity<T>(requestBody, headers);
			StarHealthRestClientResponse<R, ER> successResponse = null;
			try {
				responseEntity = restTemplate.postForEntity(url, entity, responseType, uriVariables);
				R response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callGetMethod(String url, Class<R> responseType,
			Class<ER> errorResponseType) {
		if (null != url && null != responseType && null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			R response = null;
			StarHealthRestClientResponse<R, ER> successResponse = null;
			try {
				responseEntity = restTemplate.getForEntity(url, responseType);
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callGetMethod(String url, Class<R> responseType,
			Map<String, String> uriVariables, Class<ER> errorResponseType) {
		if (null != url && null != responseType && null != uriVariables && null != errorResponseType) {
			ResponseEntity<R> responseEntity = null;
			R response = null;
			StarHealthRestClientResponse<R, ER> successResponse = null;
			try {
				responseEntity = restTemplate.getForEntity(url, responseType, uriVariables);
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public StarHealthRestClientResponse<R, ER> callGetMethod(String url, Map<String, String> headersMap,
			Map<String, String> uriVariables, Class<R> responseType, Class<ER> errorResponseType) {
		if (null != url && null != headersMap && null != responseType && null != uriVariables
				&& null != errorResponseType) {

			ResponseEntity<R> responseEntity = null;
			R response = null;
			StarHealthRestClientResponse<R, ER> successResponse = null;
			// Set headers
			HttpHeaders httpHeaders = new HttpHeaders();
			Set<Entry<String, String>> set = headersMap.entrySet();
			Stream<Entry<String, String>> stream = set.stream();
			stream.forEach(me -> {
				httpHeaders.add(me.getKey(), me.getValue());
			});
			// Set headers & request body to http entity
			HttpEntity<T> entity = new HttpEntity<>(httpHeaders);
			try {
				// Call rest provider and get response
				responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType, uriVariables);
				// Process response entity
				response = processResponseEntity(responseEntity);
				successResponse = new StarHealthRestClientResponse<R, ER>();
				successResponse.setSuccessResponse(response);
				return successResponse;
			} catch (RestClientResponseException exp) {
				StarHealthRestClientResponse<R, ER> errorResponse = new StarHealthRestClientResponse<R, ER>();
				ER errorResponseBody = null;
				int statusCode = exp.getRawStatusCode();
				System.out.println("Status Code: " + statusCode);
				String errorBody = exp.getResponseBodyAsString();
				System.out.println("Error Response Body: " + errorBody);
				try {
					errorResponseBody = new ObjectMapper().readValue(errorBody, errorResponseType);
				} catch (IOException e1) {
				}
				errorResponse.setErrorResponse(errorResponseBody);
				return errorResponse;
			} catch (Exception exp) {
				System.out.println("Service is not available");
				System.out.println("Error details from rest provider :" + exp.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	private R processResponseEntity(ResponseEntity<R> responseEntity) {
		R body = null;
		int statusCode = responseEntity.getStatusCodeValue();
		if (statusCode == 200 || statusCode==201) {
			body = responseEntity.getBody();
			return body;
		} else {
			return body;
		}
	}
}
