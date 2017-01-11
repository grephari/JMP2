package com.epam.jmp2.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.dbrepositories.RoadAccidentRepository;
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
		//
		// Iterable<RoadAccident> accidentByWeatherCondition =
		// getAccidentRepository()
		// .findAccidentsByWeatherConditionAndYear(weatherCondition, year);
		Iterable<Accident> accidents = accidentRepository.findByWeatherConditionAndYear(weatherCondition, year);
		return accidents;
	}

	public Iterable<RoadAccident> getAllAccidentsByDate(Date date) {
		List<RoadAccident> roadAccidents = roadAccidentRepository.findByDate(date);
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