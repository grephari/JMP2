package com.epam.jmp2.service;

import java.text.ParseException;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;


public interface AccidentService {

    // scenario 1
    Accident findOne(String accidentId);

    // scenario 2
    Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label);

    // scenario 3
    Long getAllAccidentsByWeatherConditionAndYear(Integer weatherCondition, String year) throws ParseException;

    // scenario 4
    Iterable<RoadAccident> getAllAccidentsByDate(String date);

    Iterable<RoadAccident> updateTimePeriod(String date);

}
