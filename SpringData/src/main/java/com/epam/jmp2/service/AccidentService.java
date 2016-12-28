package com.epam.jmp2.service;

import java.util.Date;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;


public interface AccidentService {

    // scenario 1
    Accident findOne(String accidentId);

    // scenario 2
    Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label);

    // scenario 3
    Iterable<RoadAccident> getAllAccidentsByWeatherConditionAndYear(Integer weatherCondition, String year);

    // scenario 4
    Iterable<RoadAccident> getAllAccidentsByDate(String date);

    Boolean update(RoadAccident roadAccident);

}
