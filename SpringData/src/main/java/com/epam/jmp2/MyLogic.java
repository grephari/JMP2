package com.epam.jmp2;

import java.sql.Connection;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp2.db.DBInitializer;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.service.impl.AccidentDBServiceImpl;

public class MyLogic {
	public static void main(String[] args) {
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		// Acquire Context
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:beans.xml");
		// Get RegistrationBean That Defined
		AccidentDBServiceImpl registrationBean = (AccidentDBServiceImpl) context.getBean("accidentDBServiceImpl");

		Accident accident = registrationBean.findOne("200901BS70001");
		
			System.out.println(accident.getAccidentIndex());
			System.out.println(accident.getAccidentSeverity());
			
			// Getting weather conditions for this accident
			System.out.println(accident.getWeatherCondition().getLabel());
			// Getting Road Surface conditions for this accident
			System.out.println(accident.getRoadSurfaceCondition().getLabel());
			// Getting light conditions for this accident
			System.out.println(accident.getLightCondition().getLabel());
			
			// Getting Police Force details for this accident
			System.out.println(accident.getPoliceForce().getLabel());
			// Getting District Authority Force details for this accident
			System.out.println(accident.getLocalDistrictAuthority().getLabel());
			
			System.out.println(accident.getLocalDistrictAuthority());
			
		// Close context
		context.close();
	}
}
