package com.epam.jmp2;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.epam.jmp2.db.DBInitializer;

@SpringBootApplication
public class MySpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MySpringBootWebApplication.class);
	}

	public static void main(String[] args) {
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		SpringApplication.run(MySpringBootWebApplication.class, args);
	}

}
