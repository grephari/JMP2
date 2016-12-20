package com.epam.jmp2;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp2.db.DBInitializer;
import com.epam.jmp2.entities.Accident;
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

		System.out.println("===============================================");
		System.out.println("Querying accidents by road surface condition...");
		System.out.println("===============================================");
		Iterable<RoadAccident> accidents = registrationBean.getAllAccidentsByRoadCondition(Integer.valueOf(1));
		for (RoadAccident ra : accidents) {
			System.out.println(ra);
		}

		System.out.println("===================================================");
		System.out.println("Querying accidents by weather condition and year...");
		System.out.println("===================================================");
		accidents = registrationBean.getAllAccidentsByWeatherConditionAndYear(Integer.valueOf(1), "2009");
		for (RoadAccident ra : accidents) {
			System.out.println(ra);
		}

		System.out.println("=============================");
		System.out.println("Querying accidents by date...");
		System.out.println("=============================");
		try {
			accidents = registrationBean.getAllAccidentsByDate(new SimpleDateFormat("yyyy-MM-dd").parse("2009-01-01"));
			for (RoadAccident ra : accidents) {
				System.out.println(ra);
			}
		} catch (ParseException e) {
			System.err.println("Error parsing date...");
		}

		System.out.println("=====================");
		System.out.println("Updating accidents...");

		RoadAccident ra = new RoadAccident();
		ra.setAccidentId(accident.getAccidentIndex());
		ra.setDate(LocalDate.parse(accident.getDate()));
		ra.setDayOfWeek(DayOfWeek.of(accident.getDayOfWeek()));
		ra.setLatitude(accident.getLatitude().floatValue() + 1f);
		ra.setLongitude(accident.getLongitude().floatValue() - 1f);
		ra.setTime(LocalTime.parse(accident.getTime()));
		ra.setNumberOfCasualties(ra.getNumberOfCasualties() + 1);
		ra.setNumberOfVehicles(ra.getNumberOfVehicles() + 1);
		Boolean updDone = registrationBean.update(ra);

		if (updDone) {
			System.out.println("    Update done !    ");
		} else {
			System.out.println("   Update failed..   ");
		}
		System.out.println("=====================");

		// Close context
		context.close();
	}

}
