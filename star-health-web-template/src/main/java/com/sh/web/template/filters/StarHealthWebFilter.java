package com.sh.web.template.filters;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import com.sh.web.template.http.wrapper.RequestWrapper;
import com.sh.web.template.http.wrapper.ResponseWrapper;

@Component
//@Order(1)
public class StarHealthWebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		System.out.println(" From doFilter( )/Before calling Endpoint.");
		long startingMilliSec = System.currentTimeMillis();
		DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");
		Date result = new Date(startingMilliSec);
		System.out.println("Request came at:" + simple.format(result));

		RequestWrapper requestWrapper = new RequestWrapper(req);
		ResponseWrapper responseWrapper = new ResponseWrapper(resp);

		chain.doFilter(requestWrapper, responseWrapper);

		System.out.println("After do Filter( )/After Calling Endpoint.");
		responseWrapper.setHeader("x-hsbc-corelationId", "243");
		Map<String, String> headersMap = new ConcurrentHashMap<String, String>();
		headersMap.put("x-hsbc-country", "UK");
		headersMap.put("x-hsbc-clientId", "CL32576");
		responseWrapper.setHeaders(headersMap);
		String respBody = responseWrapper.getResponseBodyAsString();
		System.out.println("Response came from Controller:" + respBody);
		// Setting new data or modifying response data
		// respBody =
		// "{\"premium\":\"1000\",\"serviceTax\":\"25\",\"totalPremium\":\"1025\",\"discountAmount\":\"0\"}";
		// responseWrapper.setResponseBody(respBody);
		responseWrapper.commitResponse();

		log(requestWrapper, respBody);

		long endingMilliSec = System.currentTimeMillis();
		result = new Date(endingMilliSec);
		System.out.println("Request ended at:" + simple.format(result));
		System.out.println("Time taken: " + (endingMilliSec - startingMilliSec) + " Milliseconds");
	}

	private void log(RequestWrapper requestWrapper, String responseBody) {
		String httpMethod = requestWrapper.getMethod();
		String url = requestWrapper.getRequestUrl();
		String requestBody = requestWrapper.getRequestBodyAsString();
		String requestId = requestWrapper.getHeader("x-hsbc-req-id");
		System.out.println("++++++++++REQUEST & RESPONSE LOGGING++++++++++++++++++++");
		System.out.println("Request ID:" + requestId);
		if ("POST".equalsIgnoreCase(httpMethod) || "PUT".equalsIgnoreCase(httpMethod)) {
			System.out.println("Url:" + url);
			System.out.println("Request Payload:" + requestBody);
			System.out.println("Response payload:" + responseBody);
		} else if ("GET".equalsIgnoreCase(httpMethod) || "DELETE".equalsIgnoreCase(httpMethod)) {
			System.out.println("Url:" + url);
			System.out.println("Response payload:" + responseBody);
		}
		System.out.println("++++++++++++++++++++++LOGGING ENDS+++++++++++++++++++++++++++++++++++++++");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("From filter init( )");
	}

	@Override
	public void destroy() {
		System.out.println("From filter destroy( )");
	}
}