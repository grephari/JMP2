package com.epam.jmp2.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
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

    public Iterable getAllAccidentsByRoadCondition(Integer label) {
        return accidentRepository.findByRoadSurfaceCondition(label);
    }

    public Iterable getAllAccidentsByWeatherConditionAndYear(
            Integer weatherCondition, String year) {
    	WeatherCondition condition = new WeatherCondition();
    	condition.setCode(weatherCondition);
    	return accidentRepository.findByWeatherCondition(condition).stream().filter(acc -> acc.getDate().contains(year)).collect(Collectors.toList());
    }

    public Iterable<RoadAccident> getAllAccidentsByDate(Date date) {
       // To be filled by mentee
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = dateFormat.format(date);
		List<Accident> accidents = accidentRepository.findByDate(dateStr);
		List<RoadAccident> roadAccidents = new ArrayList<RoadAccident>();
		for (Accident acc : accidents) {
			RoadAccidentBuilder raBuilder = new RoadAccidentBuilder(
					acc.getAccidentIndex());
			raBuilder.withLongitude(acc.getLongitude());
			raBuilder.withLatitude(acc.getLatitude());
			raBuilder.withPoliceForce(acc.getPoliceForce().getLabel());
			raBuilder
					.withAccidentSeverity(acc.getAccidentSeverity().toString());
			raBuilder.withNumberOfVehicles(acc.getNumberOfVehicles());
			raBuilder.withNumberOfCasualties(acc.getNumberOfCasualties());
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd/MM/yyyy");
			raBuilder.withDate(LocalDate.parse(acc.getDate(), formatter));
			raBuilder.withTime(LocalTime.parse(acc.getTime()));
			raBuilder.withDistrictAuthority(acc.getLocalDistrictAuthority()
					.getLabel());
			raBuilder.withLightConditions(acc.getLightCondition().getLabel());
			raBuilder.withWeatherConditions(acc.getWeatherCondition()
					.getLabel());
			raBuilder.withRoadSurfaceConditions(acc.getRoadSurfaceCondition()
					.getLabel());

			RoadAccident newRoadAccident = raBuilder.build();
			roadAccidents.add(newRoadAccident);
		}
		return roadAccidents;

    }

	public Boolean update(RoadAccident roadAccident) {
		Accident accident = accidentRepository.findOne(roadAccident
				.getAccidentId());
		if (accident == null) {
			return false;
		} else {
			LocalTime time = roadAccident.getTime();
			int hour = time.getHour();
			String label = null;
			if (hour >= 6 && hour <= 12) {
				label = "MORNING ";
			} else if (hour > 12 && hour < 18) {
				label = "AFTERNOON";
			} else if (hour >= 18 && hour < 24) {
				label = "EVENING";
			} else if (hour >= 0 && hour < 6) {
				label = "NIGHT";
			}
			accident.setTime(label);
			accidentRepository.save(accident);
			return true;
		}
	}

	public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
}