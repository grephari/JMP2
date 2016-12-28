package com.epam.jmp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.AccidentService;
import com.epam.jmp2.util.DateTimeUtils;

@RestController
@RequestMapping("roadaccidentmgmt")
public class AccidentController {

	@Autowired
	private AccidentService service;

	@RequestMapping("/index")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		return "Greetings.";
	}

	// GET http://localhost:8080/roadaccidentmgmt/accidents/200901BS70001
	@RequestMapping("/accidents/{accidentId}")
	public RoadAccident getAccidentById(@PathVariable(value = "accidentId") String accidentId) {
		return service.getAccidentById(accidentId);
	}

	// GET http://localhost:8080/roadaccidentmgmt/accidents/ofRoadCondition/1
	@RequestMapping("/accidents/ofRoadCondition/{roadConditionCode}")
	public Iterable<RoadAccident> getAccidentsByRoadCondition(
			@PathVariable(value = "roadConditionCode") Integer roadCondition) {
		return service.getAllAccidentsByRoadCondition(roadCondition);
	}

	// GET http://localhost:8080/roadaccidentmgmt/accidents/ofWeatherCondition/1?year=2009
	@RequestMapping("/accidents/ofWeatherCondition/{weatherConditionCode}")
	public Iterable<RoadAccident> getAccidentsByWeatherConditionAndYear(
			@PathVariable(value = "weatherConditionCode") Integer weatherCondition,
			@RequestParam(value = "year", required = false, defaultValue = "2009") String year) {
		return service.getAllAccidentsByWeatherConditionAndYear(weatherCondition, year);
	}

	// GET http://localhost:8080/roadaccidentmgmt/accidents/ofDate/2009-01-01
	@RequestMapping("/accidents/ofDate/{formattedDate}")
	public Iterable<RoadAccident> getAccidentsByDate(@PathVariable(value = "formattedDate") String formattedDate) {
		return service.getAllAccidentsByDate(DateTimeUtils.toDate(formattedDate));
	}

	// POST http://localhost:8080/roadaccidentmgmt/accidents/200901BS70001
	// SAMPLE REQUEST BODY: 
	//	 {"accidentId":"200901BS70001","longitude":30.1,"latitude":15.123,"numberOfVehicles":1,"numberOfCasualties":1}
	@RequestMapping(value = "/accidents/{accidentId}", method = RequestMethod.POST)
	public RoadAccident updateAccident(@PathVariable("accidentId") String accidentId,
			@RequestBody RoadAccident roadAccident) {
		roadAccident.setAccidentId(accidentId);
		service.update(roadAccident);
		return service.getAccidentById(accidentId);
	}

}
