package com.epam.jmp2.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.DistrictAuthority;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.AccidentService;

@Component
public class AccidentDBServiceImpl implements AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;
    
    @Autowired
    private DistrictAuthorityRepository districtAuthorityRepository;

    public AccidentRepository getAccidentRepository() {
        return accidentRepository;
    }

    public void setAccidentRepository(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }

    public DistrictAuthority findDistrictAuthority(Integer code) {
    	DistrictAuthority accident = districtAuthorityRepository.findByCode(code);
        return accident;
    }

    public Accident findOne(String accidentId) {
        // To be filled by mentee
        return accidentRepository.findOne(accidentId);
    }

    public Iterable getAllAccidentsByRoadCondition(Integer label) {
        // To be filled by mentee

        return null;
    }

    public Iterable getAllAccidentsByWeatherConditionAndYear(
            Integer weatherCondition, String year) {

       // Iterable<RoadAccident> accidentByWeatherCondition = getAccidentRepository()
               // .findAccidentsByWeatherConditionAndYear(weatherCondition, year);
        return null;
    }

    public Iterable<RoadAccident> getAllAccidentsByDate(Date date) {
       // To be filled by mentee
        return null;

    }
    public Boolean update(RoadAccident roadAccident) {
        // To be filled by mentee
        return null;
    }

	public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
}