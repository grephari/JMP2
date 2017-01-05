package com.epam.jmp2.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;


public interface AccidentService {

    // scenario 1
    Accident findOne(String accidentId);
    
    List<RoadAccident> findAll();

    // scenario 2
    Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label);

    // scenario 3
    Long getAllAccidentsByWeatherConditionAndYear(Integer weatherCondition, String year) throws ParseException;

    // scenario 4
    Iterable<RoadAccident> getAllAccidentsByDate(String date);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Iterable<RoadAccident> updateTimePeriod(String date);
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    RoadAccident update(RoadAccident roadAccident);
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	void delete(String indexId);

}
