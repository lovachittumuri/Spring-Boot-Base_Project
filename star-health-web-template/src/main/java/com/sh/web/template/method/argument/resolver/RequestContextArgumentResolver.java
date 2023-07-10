package com.sh.web.template.method.argument.resolver;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.web.template.model.RequestContext;
import com.sh.web.template.util.RequestContextValidator;

@Component
public class RequestContextArgumentResolver implements HandlerMethodArgumentResolver {

	private HttpServletRequest request;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RequestContext<Object> requestContext;

	@Autowired
	private RequestContextValidator requestContextValidator;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.getParameterType() == RequestContext.class)
			return true;
		else
			return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		request = webRequest.getNativeRequest(HttpServletRequest.class);
        RequestContext<Object> context = process(request, parameter);
		return context;
	}

	@SuppressWarnings("unchecked")
	private RequestContext<Object> process(HttpServletRequest request, MethodParameter parameter) throws Exception {
		System.out.println("Process Http Method:" + request.getMethod());

		//Validate the headers and set to RequestContext
		requestContextValidator.validateMandatoryHeaders(getHeaders());
		requestContextValidator.validateNonMandatoryHeaders(getHeaders());
		requestContext.setHeadersMap(getHeaders());
		//Validate the Query params and set to RequestContext
		requestContextValidator.validateMandatoryQueryParams(getQueryParamsAsMap());
		requestContextValidator.validateNonMandatoryQueryParams(getQueryParamsAsMap());
		requestContext.setQueryParamsMap(getQueryParamsAsMap());
		//Validate the Path params and set to RequestContext
		requestContextValidator.validatePathParams(
				(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
		requestContext.setPathParamsMap(
				(Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
		
		requestContext.setHttpMethod(request.getMethod());
		requestContext.setRequestUrl(request.getRequestURL().toString());
		requestContext.setQueryString(request.getQueryString());

		if ("GET".equalsIgnoreCase(request.getMethod()) || "DELETE".equalsIgnoreCase(request.getMethod())) {
			requestContext.setRequestBody("");
		} else if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
			String requestPayload = IOUtils.toString(request.getReader());
			System.out.println("REQUEST PAYLOAD:"+requestPayload);
			
			String genericParam = parameter.getGenericParameterType().getTypeName();
			String genericParamName = getStringBetweenTwoCharacters(genericParam, "<", ">");
			Class<?> genericParamType = Class.forName(genericParamName);

			Object object = mapper.readValue(requestPayload, genericParamType);
			//Validate the Request body and set to RequestContext
			requestContextValidator.validateRequestBody(object);
			requestContext.setRequestBody(object);
		}
		return requestContext;
	}

	private Map<String, String> getQueryParamsAsMap() {
		String queryString = request.getQueryString();
		Map<String, String> queryParamsMap = new ConcurrentHashMap<String, String>();
		if (queryString != null) {
			List<String> queryParamsList = Arrays.asList(queryString.split("&"));
			queryParamsList.forEach(queryParam -> {
				String[] queryParamsArray = queryParam.split("=");
				queryParamsMap.put(queryParamsArray[0], queryParamsArray[1]);
			});
			return queryParamsMap;
		} else {
			return queryParamsMap;
		}
	}

	private Map<String, String> getHeaders() {
		Map<String, String> headersMap = new ConcurrentHashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		List<String> headerNamesList = new CopyOnWriteArrayList<>();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headerNamesList.add(headerName);
		}
		headerNamesList.forEach(headerName -> {
			headersMap.put(headerName, request.getHeader(headerName));
		});
		return headersMap;
	}

	private String getStringBetweenTwoCharacters(String input, String to, String from) {
		String genericParamName = input.substring(input.indexOf(to) + 1, input.lastIndexOf(from));
		System.out.println("Generic param name:" + genericParamName);
		return genericParamName;
	}
}
