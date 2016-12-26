package com.epam.jmp2;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp2.db.DBInitializer;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.impl.AccidentDBServiceImpl;

public class MyLogic {
	
	private static final String DATE_IN_TEST = "2009-01-05";
	private static final String YEAR_IN_TEST = "2010";
	private static final Integer ROAD_CONDITION = 1;
	private static final Integer WEATHER_CONDITION = 1;
	
	public static void main(String[] args) throws ParseException {
		
		Connection connection = DBInitializer.initDatabase();
		DBInitializer.setDataSources(connection);
		// Acquire Context
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:beans.xml");
		// Get RegistrationBean That Defined
		AccidentDBServiceImpl registrationBean = (AccidentDBServiceImpl) context.getBean("accidentDBServiceImpl");

		//FIND BY INDEX
		Accident accidentDetailsFindByIndex = registrationBean.findOne("200901BS70001");
		
		System.out.println("Accident index - "+  accidentDetailsFindByIndex.getAccidentIndex());
		System.out.println("Severity -"+accidentDetailsFindByIndex.getAccidentSeverity());
		
		// Getting weather conditions for this accident
		System.out.println("Weather condition - "+accidentDetailsFindByIndex.getWeatherCondition().getLabel());
		// Getting Road Surface conditions for this accident
		System.out.println("Road surface condition - "+accidentDetailsFindByIndex.getRoadSurfaceCondition().getLabel());
		// Getting light conditions for this accident
		System.out.println("Light condition - "+accidentDetailsFindByIndex.getLightCondition().getLabel());
		
		// Getting Police Force details for this accident
		System.out.println("Police force - "+accidentDetailsFindByIndex.getPoliceForce().getLabel());
		// Getting District Authority Force details for this accident
		System.out.println("Local district authority label - " + accidentDetailsFindByIndex.getLocalDistrictAuthority().getLabel());
		
		System.out.println("Local district authority - "+accidentDetailsFindByIndex.getLocalDistrictAuthority());
		
		// FIND BY DATE
		Iterable<RoadAccident> accidentDetailsFetchByDate = registrationBean.getAllAccidentsByDate(DATE_IN_TEST);
		for(RoadAccident accident : accidentDetailsFetchByDate){
			System.out.println("Accident date by date "+ accident);
		}
		
		// FIND BY ROAD CONDITION
		Iterable<RoadAccident> accidentDetailsFetchRoadCondition = registrationBean.getAllAccidentsByRoadCondition(ROAD_CONDITION);
		for (RoadAccident accident : accidentDetailsFetchRoadCondition) {
			System.out.println("Accident data by road condition- "+ accident);
		}
		
		// GROUP BY ROAD CONDITION AND YEAR
		Long accidentDetailsGroupedByWeatherConditionAndYear = registrationBean.getAllAccidentsByWeatherConditionAndYear(WEATHER_CONDITION, YEAR_IN_TEST);
		System.out.println("Accident data grouped by year and weather condition- "+ accidentDetailsGroupedByWeatherConditionAndYear);
		
		// UPDATE TIME PERIOD OF DAY
		Iterable<RoadAccident> updatedAccidentDetailsBasedOnTimePeriod = registrationBean.updateTimePeriod(DATE_IN_TEST);
		for (RoadAccident accident : updatedAccidentDetailsBasedOnTimePeriod) {
			System.out.println("Updated Accident data by road condition- "+ accident.getTime());
		}

		// Close context
		context.close();
	}
}
