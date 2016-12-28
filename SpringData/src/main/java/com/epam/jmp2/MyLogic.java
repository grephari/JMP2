package com.epam.jmp2;

import java.sql.Connection;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp2.db.DBInitializer;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.RoadSurfaceCondition;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.impl.AccidentDBServiceImpl;

public class MyLogic {
	public static void main(String[] args) {
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		// Acquire Context
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:beans.xml");
		// Get RegistrationBean That Defined
		AccidentDBServiceImpl registrationBean = (AccidentDBServiceImpl) context.getBean("accidentDBServiceImpl");

		Accident accident = registrationBean.findOne("200997UD71707");// 200901BS70001
		
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
			
			
			Iterable<Accident> accidentsByRoadCondition = registrationBean.getAllAccidentsByRoadCondition(2);
			//accidentsByRoadCondition.forEach(System.out::println);
			
			Iterable<Accident> accidentsByWeatherConditionAndYear = registrationBean.getAllAccidentsByWeatherConditionAndYear(1, "2008");
			//accidentsByWeatherConditionAndYear.forEach(System.out::println);
			
			Iterable<RoadAccident> accidentsByDate = registrationBean.getAllAccidentsByDate("1/1/2008");
			accidentsByDate.forEach(System.out::println);
			
			for(RoadAccident ra : accidentsByDate){
				registrationBean.update(ra);
			}
			
			Iterable<RoadAccident> accidentsByDate1 = registrationBean.getAllAccidentsByDate("1/1/2008");
			accidentsByDate1.forEach(System.out::println);
		// Close context
		context.close();
	}
}
