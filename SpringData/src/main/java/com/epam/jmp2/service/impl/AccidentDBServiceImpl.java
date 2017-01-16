package com.epam.jmp2.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.dbrepositories.RoadSurfaceConditionRepository;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.DistrictAuthority;
import com.epam.jmp2.entities.WeatherCondition;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.model.RoadAccidentBuilder;
import com.epam.jmp2.service.AccidentService;

@Component
public class AccidentDBServiceImpl implements AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;
    
    @Autowired
    private DistrictAuthorityRepository districtAuthorityRepository;
    
    @Autowired
    private RoadSurfaceConditionRepository roadSurfaceConditionRepo; 

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
    	return accidentRepository.findOne(accidentId);
    }

    public Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label) {    	
    	return roadSurfaceConditionRepo.getAllAccidentsByCode(label);
    }

    public Iterable getAllAccidentsByWeatherConditionAndYear(
            Integer weatherCondition, String year) {
    	WeatherCondition weather = new WeatherCondition();
    	weather.setCode(weatherCondition);
    	return accidentRepository.findByWeatherConditionAndDateContaining(weather, year);        
    }

    public Iterable<RoadAccident> getAllAccidentsByDate(String date) {
    	List<Accident> dbData = accidentRepository.findAllByDate(date);
    	List<RoadAccident> result = new ArrayList<>();
    	for(Accident acc : dbData){
    		RoadAccidentBuilder b = new RoadAccidentBuilder()
    				.withAccidentSeverity(acc.getAccidentSeverity())
    				.withDistrictAuthority(acc.getLocalDistrictAuthority().getLabel())
    				.withLightConditions(acc.getLightCondition().getLabel())
    				.withPoliceForce(acc.getPoliceForce().getLabel())
    				.withWeatherConditions(acc.getWeatherCondition().getLabel());

    		RoadAccident rd = new RoadAccident(b);
    		result.add(rd) ;   		
    	}
    	
        return result;

    }
    public Boolean update(RoadAccident roadAccident) {
    	Accident a = new Accident();
    	a.setAccidentIndex(roadAccident.getAccidentId());    	
    	a.setAccidentSeverity(roadAccident.getAccidentSeverity());
    	a.setDate(roadAccident.getDate().toString());
    	a.setDayOfWeek(roadAccident.getDayOfWeek().getValue());
    	a.setLongitude(roadAccident.getLongitude());
    	a.setLatitude(roadAccident.getLatitude());
    	a.setNumberOfCasualties(roadAccident.getNumberOfCasualties());
    	a.setNumberOfVehicles(roadAccident.getNumberOfVehicles());

    	accidentRepository.save(a);
    	return true;
    }

	public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
}