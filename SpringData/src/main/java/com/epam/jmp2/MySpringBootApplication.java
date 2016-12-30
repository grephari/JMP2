package com.epam.jmp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Starter for home task 5: a restful web service provider.
 * 
 * @author Kevin_He
 */
@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class MySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

}
