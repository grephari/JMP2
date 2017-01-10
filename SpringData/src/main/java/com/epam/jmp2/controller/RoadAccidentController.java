package com.epam.jmp2.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.model.RoadAccidentBuilder;
import com.epam.jmp2.service.AccidentService;

@RestController
public class RoadAccidentController {
	
	@Autowired
	private AccidentService accidentService;
	
	@RequestMapping("/helloworld")
	public String getMessage(){
		return "Hello, world";
	}
	
	@RequestMapping("/{accidentId}")
	public RoadAccident findAccidentById(@PathVariable String accidentId){
		Accident ac = accidentService.findOne(accidentId);
		String dateArr[] = ac.getDate().split("/");
		String dateStr = dateArr[2] + "-" + (dateArr[0].length()==1?"0"+dateArr[0]:dateArr[0]) + "-" + (dateArr[1].length()==1?"0"+dateArr[1]:dateArr[1]);
		RoadAccidentBuilder rab = new RoadAccidentBuilder();
		rab.withAccidentSeverity(String.valueOf(ac.getAccidentSeverity()));
		rab.withDate(LocalDate.parse(dateStr));
		rab.withDistrictAuthority(ac.getLocalDistrictAuthority().toString());
		rab.withLatitude(ac.getLatitude());
		rab.withLightConditions(ac.getLightCondition().getLabel());
		rab.withLongitude(ac.getLongitude());
		rab.withNumberOfCasualties(ac.getNumberOfCasualties());
		rab.withNumberOfVehicles(ac.getNumberOfVehicles());
		rab.withPoliceForce(ac.getPoliceForce().getLabel());
		rab.withRoadSurfaceConditions(ac.getRoadSurfaceCondition().getLabel());
		rab.withTime(ac.getTime());  		  		
		rab.withWeatherConditions(ac.getWeatherCondition().getLabel());
		RoadAccident ra = rab.build();
		return ra;
		
	}
	
	@RequestMapping("/roadcondition/{code}")
	public List<RoadAccident> findAccidentByRoadCondition(@PathVariable String code){
		List<RoadAccident> roadAccidentList =  (List<RoadAccident>) accidentService.getAllAccidentsByRoadCondition(Integer.valueOf(code));	
		return roadAccidentList;
		
	}
		
	@RequestMapping("/weathercondition/{code}/{year}")
	public List<RoadAccident> findAccidentsByWeatherConditionAndYear(@PathVariable String code, @PathVariable String year){
		List<RoadAccident> roadAccidentList =  (List<RoadAccident>) accidentService.getAllAccidentsByWeatherConditionAndYear(Integer.valueOf(code), year);	
		return roadAccidentList;
		
	}
	
	@RequestMapping("/update/{year}/{month}/{day}")
	public List<RoadAccident> findAccidentsByDateAndUpdateTimePeriod(@PathVariable String year, @PathVariable String month, @PathVariable String day){
		String date = day + "/" + month + "/" + year;
		List<RoadAccident> roadAccidentList =  (List<RoadAccident>) accidentService.getAllAccidentsByDate(date);
		for(RoadAccident ra : roadAccidentList){
			accidentService.update(ra);
		}
		roadAccidentList =  (List<RoadAccident>) accidentService.getAllAccidentsByDate(date);
		return roadAccidentList;
		
	}

}
