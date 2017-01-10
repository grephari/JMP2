package com.epam.jmp2.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.RoadSurfaceCondition;
import com.epam.jmp2.entities.WeatherCondition;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.model.RoadAccidentBuilder;
import com.epam.jmp2.model.TimeOfDay;
import com.epam.jmp2.service.AccidentService;
import com.epam.jmp2.util.Util;

@Component
public class AccidentDBServiceImpl implements AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;
    
    @Autowired
    private DistrictAuthorityRepository districtAuthorityRepository;

    public Accident findOne(String accidentId) {
        return accidentRepository.findOne(accidentId);
    }
    
    public Iterable<RoadAccident> getAllAccidentsByDate(String date) {
    	List<Accident> accidentByWeatherCondition = accidentRepository.findByDate(date);
        return getRoadAccidentFromAccident(accidentByWeatherCondition);
    }

    public Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label) {
    	RoadSurfaceCondition roadSurfaceCondition = new RoadSurfaceCondition();
    	roadSurfaceCondition.setCode(label);
    	List<Accident> accidentByRoadSurfaceCondition = accidentRepository.findByRoadSurfaceCondition(roadSurfaceCondition);
    	return getRoadAccidentFromAccident(accidentByRoadSurfaceCondition);
    }
    
	public Long getAllAccidentsByWeatherConditionAndYear(Integer weatherCondition, String year) throws ParseException {
    	WeatherCondition weatherConditionItem = new WeatherCondition();
    	weatherConditionItem.setCode(weatherCondition);
    	
        List<Accident> accidentList = accidentRepository.findAccidentsByWeatherCondition(weatherConditionItem);
        List<Accident> yearFromDate = getYear(accidentList);
        
		Map<String, Long> result = (yearFromDate).stream().collect(Collectors.groupingBy(Accident::getYear, Collectors.counting()));
        return result.get(year);
    }

    public Iterable<RoadAccident> updateTimePeriod(String date) {
    	List<Accident> accidentData = accidentRepository.findByDate(date);
    	if(accidentData != null && !accidentData.isEmpty()){
    		for(Accident accidentItem :accidentData){
    			String updateDayPeriod = TimeOfDay.getTimeOfDay(LocalTime.parse(accidentItem.getTime()));
    			accidentItem.setTime(updateDayPeriod);
    			accidentRepository.save(accidentItem);
    		}
    	}
    	return getRoadAccidentFromAccident(accidentRepository.findByDate(date));
    }

    public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
	
    private List<RoadAccident> getRoadAccidentFromAccident(List<Accident> accidentList) {
    	List<RoadAccident> roadaccidentByRoadSurfaceConditionList = new ArrayList<>(); 
    	if(accidentList != null && !accidentList.isEmpty()){
    		for(Accident accident : accidentList){
        		RoadAccidentBuilder roadAccidentByRoadSurface = new RoadAccidentBuilder();
        		roadAccidentByRoadSurface.withAccidentId(accident.getAccidentIndex());
        		roadAccidentByRoadSurface.withAccidentSeverity(accident.getAccidentSeverity().toString());
        		roadAccidentByRoadSurface.withDate(LocalDate.parse(accident.getDate()));
        		roadAccidentByRoadSurface.withDistrictAuthority(accident.getLocalDistrictAuthority().getLabel());
        		roadAccidentByRoadSurface.withLatitude(accident.getLatitude());
        		roadAccidentByRoadSurface.withLightConditions(accident.getLightCondition().getLabel());
        		roadAccidentByRoadSurface.withLongitude(accident.getLongitude());
        		roadAccidentByRoadSurface.withNumberOfCasualties(accident.getNumberOfCasualties());
        		roadAccidentByRoadSurface.withNumberOfVehicles(accident.getNumberOfCasualties());
        		roadAccidentByRoadSurface.withPoliceForce(accident.getPoliceForce().getLabel());
        		roadAccidentByRoadSurface.withRoadSurfaceConditions(accident.getRoadSurfaceCondition().getLabel());
        		roadAccidentByRoadSurface.withWeatherConditions(accident.getWeatherCondition().getLabel());
        		roadaccidentByRoadSurfaceConditionList.add(roadAccidentByRoadSurface.build());
        	}
    	}
		return roadaccidentByRoadSurfaceConditionList;
	}

    private List<Accident> getYear(List<Accident> accidentByWeatherConditionList) throws ParseException {
    	if(accidentByWeatherConditionList != null && !accidentByWeatherConditionList.isEmpty()){
    		for(Accident accident : accidentByWeatherConditionList){
        		String year = Util.parseStringDateToGetYear(accident.getDate());
        		accident.setYear(year);
        	}	
    	}
    	return accidentByWeatherConditionList;
	}
}