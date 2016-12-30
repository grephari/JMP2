package com.epam.jmp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Starter for Home task 6: a comprehensive web server serving both pages and restful web services.
 * 
 * However, I found that if start a spring-web(mvc)-application by spring-boot, no web.xml nor any Java/XML
 * configuration is needed: all can be configured in a default way, or customized in its default properties file
 * "src/main/resources/application.properties"
 * 
 * When launched, go @link http://localhost:8090/RoadAccident/list
 * 
 * @author Kevin_He
 */
@SpringBootApplication
public class MySpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MySpringBootWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootWebApplication.class, args);
	}

}
