package com.epam.jmp2.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/accidents",  method = RequestMethod.GET)
	public List<RoadAccident> findAll() {
		return accidentService.findAll();
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
	
	@RequestMapping(value = "/updateTime", method = RequestMethod.PUT)
	public Iterable<RoadAccident> updateTimePeriod(@RequestParam("date") String date) throws ParseException {
		return accidentService.updateTimePeriod(date);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RoadAccident update(@RequestBody RoadAccident accident) throws ParseException {
		return accidentService.update(accident);
	}
	
	@RequestMapping(value = "/deleteAccidentData/{indexId}", method = RequestMethod.DELETE)
	public List<RoadAccident> deleteAccidentData(@PathVariable String indexId) throws ParseException {
		accidentService.delete(indexId);
		return accidentService.findAll();
	}
}
