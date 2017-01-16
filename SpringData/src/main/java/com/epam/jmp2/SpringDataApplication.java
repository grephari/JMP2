package com.epam.jmp2;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.epam.jmp2.db.DBInitializer;

@SpringBootApplication
public class SpringDataApplication {

	public static void main(String[] args) {
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		SpringApplication.run(SpringDataApplication.class, args);
	}

}
