package com.epam.jmp2;

import java.sql.Connection;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.epam.jmp2.db.DBInitializer;

@SpringBootApplication
@ImportResource({"classpath*:beans.xml"})
public class MyLogic {
	
	public static void main(String[] args) throws ParseException {
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		SpringApplication.run(MyLogic.class, args);
	}
}
