package com.epam.jmp2.service.impl;

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
import com.epam.jmp2.dbrepositories.RoadAccidentRepository;
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
	private RoadAccidentRepository roadAccidentRepository;

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
		Accident accident = accidentRepository.findOne(accidentId);
		return accident;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable getAllAccidentsByRoadCondition(Integer label) {
		Iterable<Accident> accidents = accidentRepository.findByRoadSurfaceCondition(label);
		return accidents;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterable getAllAccidentsByWeatherConditionAndYear(Integer weatherCondition, String year) {
		WeatherCondition condition = new WeatherCondition();
		condition.setCode(weatherCondition);
		return accidentRepository.findByWeatherCondition(condition).stream().filter(acc -> acc.getDate().contains(year))
				.collect(Collectors.toList());
	}

	public Iterable<RoadAccident> getAllAccidentsByDate(Date date) {
		// List<RoadAccident> roadAccidents =
		// roadAccidentRepository.findByDate(date);
		// return roadAccidents;
		List<Accident> accidents = accidentRepository.findByDate(date);
		List<RoadAccident> roadAccidents = new ArrayList<RoadAccident>();
		for (Accident accident : accidents) {
			RoadAccidentBuilder roadAccidentBuilder = new RoadAccidentBuilder(accident.getAccidentIndex());
			roadAccidentBuilder.withLongitude(accident.getLongitude());
			roadAccidentBuilder.withLatitude(accident.getLatitude());
			roadAccidentBuilder.withPoliceForce(accident.getPoliceForce().getLabel());
			roadAccidentBuilder.withAccidentSeverity(accident.getAccidentSeverity().toString());
			roadAccidentBuilder.withNumberOfVehicles(accident.getNumberOfVehicles());
			roadAccidentBuilder.withNumberOfCasualties(accident.getNumberOfCasualties());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			roadAccidentBuilder.withDate(LocalDate.parse(accident.getDate(), formatter));
			roadAccidentBuilder.withTime(LocalTime.parse(accident.getTime()));
			roadAccidentBuilder.withDistrictAuthority(accident.getLocalDistrictAuthority().getLabel());
			roadAccidentBuilder.withLightConditions(accident.getLightCondition().getLabel());
			roadAccidentBuilder.withWeatherConditions(accident.getWeatherCondition().getLabel());
			roadAccidentBuilder.withRoadSurfaceConditions(accident.getRoadSurfaceCondition().getLabel());

			RoadAccident newRoadAccident = roadAccidentBuilder.build();
			roadAccidents.add(newRoadAccident);
		}
		return roadAccidents;

	}

	public Boolean update(RoadAccident roadAccident) {
		RoadAccident newRoadAccident = roadAccidentRepository.findOne(roadAccident.getAccidentId());
		if (!(newRoadAccident == null)) {
			newRoadAccident.setAccidentSeverity(roadAccident.getAccidentSeverity());
			newRoadAccident.setDate(roadAccident.getDate());
			newRoadAccident.setDayOfWeek(roadAccident.getDayOfWeek());
			newRoadAccident.setDistrictAuthority(roadAccident.getDistrictAuthority());
			newRoadAccident.setLatitude(roadAccident.getLatitude());
			newRoadAccident.setLightConditions(roadAccident.getLightConditions());
			newRoadAccident.setLongitude(roadAccident.getLongitude());
			newRoadAccident.setNumberOfCasualties(roadAccident.getNumberOfCasualties());
			newRoadAccident.setNumberOfVehicles(roadAccident.getNumberOfVehicles());
			newRoadAccident.setPoliceForce(roadAccident.getPoliceForce());
			newRoadAccident.setRoadSurfaceConditions(roadAccident.getRoadSurfaceConditions());
			newRoadAccident.setTime(roadAccident.getTime());
			newRoadAccident.setWeatherConditions(roadAccident.getWeatherConditions());
			roadAccidentRepository.save(newRoadAccident);
			return true;
		} else {

			return false;
		}

	}

	public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
}