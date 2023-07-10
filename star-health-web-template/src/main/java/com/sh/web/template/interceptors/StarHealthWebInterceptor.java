package com.sh.web.template.interceptors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
//@Order(1)
 public class StarHealthWebInterceptor implements HandlerInterceptor
{
	long startingMilliSec=0;
	 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("---Before Method Execution[INTERCEPTOR]---");
		 startingMilliSec = System.currentTimeMillis();
		DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");
	        Date result = new Date(startingMilliSec);
	       System.out.println("Request came at:"+simple.format(result));
		   return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception 
	{
		System.out.println("---After Method Execution[INTERCEPTOR]---");
		 long endingMilliSec = System.currentTimeMillis();
			DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");
		    Date result = new Date(endingMilliSec);
		    System.out.println("Request ended at:"+simple.format(result));
		  System.out.println("Time taken: "+(endingMilliSec-startingMilliSec)+" Milliseconds");  
	}
}