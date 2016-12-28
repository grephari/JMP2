package com.epam.jmp2.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.DistrictAuthority;
import com.epam.jmp2.entities.RoadSurfaceCondition;
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
    	Accident accident = accidentRepository.findOne(accidentId);
        return accident;
    }

    public Iterable getAllAccidentsByRoadCondition(Integer label) {
        // To be filled by mentee
    	RoadSurfaceCondition rsfc = new RoadSurfaceCondition();
		rsfc.setCode(label);
		Iterable<Accident> list = accidentRepository.findByRoadSurfaceCondition(rsfc);
        return list;
    }

    public Iterable getAllAccidentsByWeatherConditionAndYear(
            Integer weatherCondition, String year) {
    	WeatherCondition wc = new WeatherCondition();
    	wc.setCode(weatherCondition);
    	Iterable<Accident> accidents = accidentRepository.findByWeatherCondition(wc).stream().filter(item -> item.getDate().contains(year))
    	                                             .collect(Collectors.toList());
        
        return accidents;
    }

    public Iterable<RoadAccident> getAllAccidentsByDate(String date) {
       // To be filled by mentee
    	Iterable<Accident> accidents = accidentRepository.findByDate(date);
    	List<RoadAccident> roadAccidents = new ArrayList<RoadAccident>();
    	for(Accident ac : accidents){
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
    		ra.setAccidentId(ac.getAccidentIndex());
    		roadAccidents.add(ra);    		
    	}
        return roadAccidents;

    }
    public Boolean update(RoadAccident roadAccident) {
        // To be filled by mentee
    	String time = roadAccident.getTime();
    	String timeLabel = time;
    	if(time.contains(":")){
    		int hour = Integer.valueOf(time.toString().split(":")[0]);   		
        	if(hour>6 && hour<12){
        		timeLabel = "MORNING ";
        	}else if(hour>12 && hour<18){
        		timeLabel = "AFTERNOON";
        	}else if(hour>18 && hour<24){
        		timeLabel = "EVENING";
        	}else if(hour>0 && hour<6){
        		timeLabel = "NIGHT";
        	}
    	}    	
    	   	
    	Accident ac = accidentRepository.findOne(roadAccident.getAccidentId());
    	ac.setTime(timeLabel);
    	accidentRepository.save(ac);
        return true;
    }

	public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
}