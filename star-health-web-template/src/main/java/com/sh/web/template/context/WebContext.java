package com.sh.web.template.context;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;
@Component
 public class WebContext implements HttpSessionListener, ServletContextListener 
{
	 @Override
	 public void contextInitialized(ServletContextEvent sce)
	{
		 System.out.println("Servlet context initialized...........");
	}

	 @Override
	 public void contextDestroyed(ServletContextEvent sce)
	{
		 System.out.println("Servlet context destroyed...........");
		 
	}

	 @Override
	 public void sessionCreated(HttpSessionEvent se)
	{
	
		 System.out.println("Session started......");
	}

	 @Override
	 public void sessionDestroyed(HttpSessionEvent se)
	{
		 System.out.println("Session destroyed......");
	
	}
}