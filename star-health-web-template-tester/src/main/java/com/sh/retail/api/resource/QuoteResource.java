package com.sh.retail.api.resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.retail.api.model.QuoteRequest;
import com.sh.retail.api.model.QuoteResponse;
import com.sh.web.template.model.RequestContext;

@RestController
public class QuoteResource {
	
	@PostMapping(path = "/post/premium/calculate/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<QuoteResponse> getQuickQuote(RequestContext<QuoteRequest> requestContext) throws Exception {
		System.out.println("CONTROLLER STARTED.................");
		System.out.println("Request in Resource :"+ requestContext.getRequestBody());
		System.out.println("HEADERS:" + requestContext.getHeadersMap());
		System.out.println("Query params:" + requestContext.getQueryParamsMap());
		System.out.println("Path params:" + requestContext.getPathParamsMap());
		System.out.println("Http Method:"+requestContext.getHttpMethod());
		System.out.println("Request Url:"+requestContext.getRequestUrl());
		System.out.println("Query string:"+requestContext.getQueryString());
        
		QuoteResponse quoteResponse = new QuoteResponse();
		quoteResponse.setPremium("1000");
		quoteResponse.setServiceTax("25");
		quoteResponse.setTotalPremium("1020");
		quoteResponse.setDiscountAmount("5");
		System.out.println("REQUEST WAS PROCESSED[CONTROLLER ENDED]");
		return new ResponseEntity<>(quoteResponse, HttpStatus.OK);
	}
	@PutMapping(path = "/put/premium/calculate/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<QuoteResponse> quickQuote(RequestContext<QuoteRequest> requestContext) throws Exception {

		System.out.println("Request in Resource :"+ requestContext.getRequestBody());
		System.out.println("HEADERS:" + requestContext.getHeadersMap());
		System.out.println("Query params:" + requestContext.getQueryParamsMap());
		System.out.println("Path params:" + requestContext.getPathParamsMap());
		System.out.println("Http Method:"+requestContext.getHttpMethod());
		System.out.println("Request Url:"+requestContext.getRequestUrl());
		System.out.println("Query string:"+requestContext.getQueryString());
		
		QuoteResponse quoteResponse = new QuoteResponse();
		quoteResponse.setPremium("1000");
		quoteResponse.setServiceTax("25");
		quoteResponse.setTotalPremium("1020");
		quoteResponse.setDiscountAmount("5");
		System.out.println("REQUEST WAS PROCESSED");
		return new ResponseEntity<>(quoteResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/premium/calculate/{id}", produces = "application/json")
	public ResponseEntity<QuoteResponse> getQuote(RequestContext<Void> requestContext) throws Exception {
		System.out.println("CONTROLLER STARTED.................");
		System.out.println("HEADERS:" + requestContext.getHeadersMap());
		System.out.println("Query params:" + requestContext.getQueryParamsMap());
		System.out.println("Path params:" + requestContext.getPathParamsMap());
		System.out.println("Http Method:"+requestContext.getHttpMethod());
		System.out.println("Request Url:"+requestContext.getRequestUrl());
		System.out.println("Query string:"+requestContext.getQueryString());
		
		QuoteResponse quoteResponse = new QuoteResponse();
		quoteResponse.setPremium("1000");
		quoteResponse.setServiceTax("25");
		quoteResponse.setTotalPremium("1020");
		quoteResponse.setDiscountAmount("5");
		System.out.println("REQUEST WAS PROCESSED[CONTROLLER ENDED]");
		return new ResponseEntity<>(quoteResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/premium/calculate/{id}", produces = "application/json")
	public ResponseEntity<QuoteResponse> deleteQuote(RequestContext<Void> requestContext) throws Exception {
		System.out.println("CONTROLLER STARTED.................");
		System.out.println("HEADERS:" + requestContext.getHeadersMap());
		System.out.println("Query params:" + requestContext.getQueryParamsMap());
		System.out.println("Path params:" + requestContext.getPathParamsMap());
		System.out.println("Http Method:"+requestContext.getHttpMethod());
		System.out.println("Request Url:"+requestContext.getRequestUrl());
		System.out.println("Query string:"+requestContext.getQueryString());
		
		QuoteResponse quoteResponse = new QuoteResponse();
		quoteResponse.setPremium("1000");
		quoteResponse.setServiceTax("25");
		quoteResponse.setTotalPremium("1020");
		quoteResponse.setDiscountAmount("5");
		System.out.println("REQUEST WAS PROCESSED[CONTROLLER ENDED]");
		return new ResponseEntity<>(quoteResponse, HttpStatus.OK);
	}
}
