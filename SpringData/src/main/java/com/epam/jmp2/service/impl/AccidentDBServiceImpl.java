package com.epam.jmp2.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.dbrepositories.WeatherConditionRepository;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.DistrictAuthority;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.AccidentService;
import com.epam.jmp2.util.DateTimeUtils;

@Component
public class AccidentDBServiceImpl implements AccidentService {

	@Autowired
	private AccidentRepository accidentRepository;

	@Autowired
	private DistrictAuthorityRepository districtAuthorityRepository;

	@Autowired
	private WeatherConditionRepository weatherConditionRepository;

	public DistrictAuthority findDistrictAuthority(Integer code) {
		DistrictAuthority accident = districtAuthorityRepository.findByCode(code);
		return accident;
	}

	public Accident findOne(String accidentId) {
		return accidentRepository.findOne(accidentId);
	}

	@Override
	public Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label) {
		return accidentRepository.queryByRoadSurfaceCondition(label);
	}

	@Override
	public Iterable<RoadAccident> getAllAccidentsByWeatherConditionAndYear(Integer weatherCondition, String year) {
		if (!DateTimeUtils.isValidYear(year)) {
			return Collections.emptyList();
		}
		List<Integer> validWeatherCodes = weatherConditionRepository.findAllCodes();
		if (!validWeatherCodes.contains(weatherCondition)) {
			return Collections.emptyList();
		}
		return accidentRepository.queryByWeatherConditionAndYear(weatherCondition, year);
	}

	@Override
	public Iterable<RoadAccident> getAllAccidentsByDate(Date date) {
		return accidentRepository.findByDate(DateTimeUtils.formatDate(date));
	}

	@Override
	public Boolean update(RoadAccident roadAccident) {
		if (roadAccident == null || roadAccident.getAccidentId() == null) {
			return Boolean.FALSE;
		}
		Accident accident = accidentRepository.findOne(roadAccident.getAccidentId());
		if (accident == null) {
			return Boolean.FALSE;
		}
		try {
			accident.setDate(DateTimeUtils.formatLocalDate(roadAccident.getDate()));
			accident.setDayOfWeek(roadAccident.getDayOfWeek().getValue());
			accident.setLatitude(roadAccident.getLatitude());
			accident.setLongitude(roadAccident.getLongitude());
			accident.setNumberOfCasualties(roadAccident.getNumberOfCasualties());
			accident.setNumberOfVehicles(roadAccident.getNumberOfVehicles());
			accident.setTime(DateTimeUtils.formatLocalTime(roadAccident.getTime()));
			accidentRepository.save(accident);
		} catch (Throwable t) {
			t.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}