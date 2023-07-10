POINTS TO BE NOTED:
==================
## All the files and folders present under src/main/resources are automatically added to Jar.

## Not only @Configuration classes but also you can register all the stereo typed annotated classes.
   Ex: @Component, @Service, @Controller, @RestController, @Repository etc.

## For providing default properties, we can use 'properties file' with any name that we want. That properties file keys will be
overridden.

## When we override auto configuration, first our @Configuration class is executed then after auto configuration provided @Configuration
class is executed.

## As part of the starter Jar, we can provide either application.yml or application.properties. Those will be overridden. 
Using either application.yml or application.properties is not recommended.

BEST PRACTICES:
==============
## Use @ConditionalOn... annotations on @Configuration/@Bean

## Do not start your starter name with spring-boot. Start with your company name.
   Ex: hsbc-autoconfigure-boot-starter, hsbc-autoconfigure-spring-boot-starter, etc.

## If your starter provides configuration keys,don't start keys names with spring boot predefined keys such as server, management,
 spring, and so on. Start with your company name.
  Ex: hsbc.server.port, hsbc.management, hsbc.spring.app.name, etc.

## If you use @ConfigurationProperties, use the below dependency that generates meta data in the jar under 
META-INF/spring-configuration-metadata.json 

         <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
## 	Don't add unnecessary dependencies to pom.xml. Mark the dependencies as optional so that you can include your starter in your
 projects more easily. 
   