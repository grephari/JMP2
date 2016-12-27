package com.epam.jmp2.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.AccidentService;

@RestController
public class AccidentDataController {
	
	@Autowired
	AccidentService accidentService;
	
	@RequestMapping(value = "/findOneByIndex/{indexId}",  method = RequestMethod.GET)
	public Accident findOne(@PathVariable String indexId) {
		return accidentService.findOne(indexId);
	}

	@RequestMapping(value = "/findAccidentDetailsByDate/{date}", method = RequestMethod.GET)
	public Iterable<RoadAccident> findAccidentDetailsByDate(@PathVariable String date) {
		return accidentService.getAllAccidentsByDate(date);
	}
	
	@RequestMapping(value = "/findAccidentDetailsByRoadCondition/{roadCondition}", method = RequestMethod.GET)
	public Iterable<RoadAccident> findAccidentDetailsByRoadCondition(@PathVariable Integer roadCondition) {
		return accidentService.getAllAccidentsByRoadCondition(roadCondition);
	}
	
	@RequestMapping(value = "/findAccidentDetailsGroupByWeatherConditionAndYear/{weatherCondition}/{year}", method = RequestMethod.GET)
	public Long findAccidentDetailsGroupByWeatherConditonAndYear(@PathVariable Integer weatherCondition,@PathVariable String year) throws ParseException {
		return accidentService.getAllAccidentsByWeatherConditionAndYear(weatherCondition, year);
	}
	
	@RequestMapping(value = "/updateTime", method = RequestMethod.POST)
	public Iterable<RoadAccident> updateTimePeriod(@RequestParam("date") String date) throws ParseException {
		return accidentService.updateTimePeriod(date);
	}
}
