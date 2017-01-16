package com.epam.jmp2;

import java.sql.Connection;
import java.text.ParseException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp2.db.DBInitializer;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.impl.AccidentDBServiceImpl;
@SpringBootApplication
@ImportResource({"classpath*:beans.xml"})
public class MyLogic {

	
	public static void main(String[] args) throws ParseException {
		
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		// Acquire Context
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:beans.xml");
		// Get RegistrationBean That Defined
		AccidentDBServiceImpl accidentService = (AccidentDBServiceImpl) context.getBean("accidentDBServiceImpl");

		//FIND BY INDEX		
		Accident accident = accidentService.findOne("200901BS70001");
		
		System.out.println("Accident index - "+  accident.getAccidentIndex());
		System.out.println("Severity -"+accident.getAccidentSeverity());
		
		// Getting weather conditions for this accident
		System.out.println("Weather condition - "+accident.getWeatherCondition().getLabel());
		// Getting Road Surface conditions for this accident
		System.out.println("Road surface condition - "+accident.getRoadSurfaceCondition().getLabel());
		// Getting light conditions for this accident
		System.out.println("Light condition - "+accident.getLightCondition().getLabel());
		
		// Getting Police Force details for this accident
		System.out.println("Police force - "+accident.getPoliceForce().getLabel());
		// Getting District Authority Force details for this accident
		System.out.println("Local district authority label - " + accident.getLocalDistrictAuthority().getLabel());
		
		System.out.println("Local district authority - "+accident.getLocalDistrictAuthority());
		
		// FIND BY DATE
		Iterable<RoadAccident> accidentDetailsFetchByDate = accidentService.getAllAccidentsByDate("2009-01-05");
		for(RoadAccident accidentByDate : accidentDetailsFetchByDate){
			System.out.println("Accident date by date "+ accidentByDate);
		}
		
		// FIND BY ROAD CONDITION
		Iterable<RoadAccident> accidentDetailsFetchRoadCondition = accidentService.getAllAccidentsByRoadCondition(1);
		for (RoadAccident accidentByRoadCondition : accidentDetailsFetchRoadCondition) {
			System.out.println("Accident data by road condition- "+ accidentByRoadCondition);
		}
		
		// GROUP BY ROAD CONDITION AND YEAR
		Long accidentDetailsGroupedByWeatherConditionAndYear = accidentService.getAllAccidentsByWeatherConditionAndYear(1, "2010");
		System.out.println("Accident data grouped by year and weather condition- "+ accidentDetailsGroupedByWeatherConditionAndYear);
		
		// UPDATE TIME PERIOD OF DAY
		Iterable<RoadAccident> updatedAccidentDetailsBasedOnTimePeriod = accidentService.updateTimePeriod("2010");
		for (RoadAccident accidentByTime : updatedAccidentDetailsBasedOnTimePeriod) {
			System.out.println("Updated Accident data by road condition- "+ accidentByTime.getTime());
		}

		// Close context
		context.close();
	}
}
