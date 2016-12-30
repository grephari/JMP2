package com.epam.jmp2.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.epam.jmp2.dbrepositories.AccidentRepository;
import com.epam.jmp2.dbrepositories.AccidentSeverityRepository;
import com.epam.jmp2.dbrepositories.DistrictAuthorityRepository;
import com.epam.jmp2.dbrepositories.LightConditionRepository;
import com.epam.jmp2.dbrepositories.PoliceForceRepository;
import com.epam.jmp2.dbrepositories.RoadSurfaceConditionRepository;
import com.epam.jmp2.dbrepositories.WeatherConditionRepository;
import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.DistrictAuthority;
import com.epam.jmp2.model.RoadAccident;
import com.epam.jmp2.service.AccidentService;
import com.epam.jmp2.util.DateTimeUtils;

@Component
public class AccidentDBServiceImpl implements AccidentService {

	private static final int DEFAULT_PAGE_SIZE = 20;

	@Autowired
	private AccidentRepository accidentRepository;
	@Autowired
	private AccidentSeverityRepository accidentSeverityRepository;
	@Autowired
	private DistrictAuthorityRepository districtAuthorityRepository;
	@Autowired
	private LightConditionRepository lightConditionRepository;
	@Autowired
	private PoliceForceRepository policeForceRepository;
	@Autowired
	private RoadSurfaceConditionRepository roadSurfaceConditionRepository;
	@Autowired
	private WeatherConditionRepository weatherConditionRepository;

	public DistrictAuthority findDistrictAuthority(Integer code) {
		DistrictAuthority accident = districtAuthorityRepository.findByCode(code);
		return accident;
	}

	@Override
	public List<Accident> findAll(int page) {
		return accidentRepository.findAll(new PageRequest(page, DEFAULT_PAGE_SIZE)).getContent();
	}

	@Override
	public Accident findOne(String accidentId) {
		return accidentRepository.findOne(accidentId);
	}

	@Override
	public RoadAccident getAccidentById(String accidentId) {
		return accidentRepository.queryById(accidentId);
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

		Optional<List<Accident>> optional = accidentRepository.findByDate(DateTimeUtils.formatDate(date));
		List<Accident> list = optional.get();
		// list.stream().forEach(System.out::println);

		Iterable<RoadAccident> itr = accidentRepository.findByAccidentDate(DateTimeUtils.formatDate(date));

		TreeSet<String> listSet = new TreeSet<>();
		for (Accident ac : list) {
			listSet.add(ac.getAccidentIndex());
		}

		TreeSet<String> itrSet = new TreeSet<>();
		for (RoadAccident ra : itr) {
			itrSet.add(ra.getAccidentId());
		}

		// last versions of JpaRepository could understand what do you want just by method name ---> yes it does.
		assert listSet.size() == itrSet.size();
		assert listSet.equals(itrSet);

		return itr;
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
			if (roadAccident.getDate() instanceof LocalDate) {
				accident.setDate(DateTimeUtils.formatLocalDate(roadAccident.getDate()));
				accident.setDayOfWeek(roadAccident.getDayOfWeek().getValue());
			}
			accident.setLatitude(roadAccident.getLatitude());
			accident.setLongitude(roadAccident.getLongitude());
			accident.setNumberOfCasualties(roadAccident.getNumberOfCasualties());
			accident.setNumberOfVehicles(roadAccident.getNumberOfVehicles());
			if (roadAccident.getTime() instanceof LocalTime) {
				accident.setTime(DateTimeUtils.formatLocalTime(roadAccident.getTime()));
			}
			accidentRepository.save(accident);
		} catch (Throwable t) {
			t.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public Accident create(RoadAccident roadAccident) {
		if (roadAccident == null || roadAccident.getAccidentId() == null) {
			return null;
		}
		// Not handling duplicate-id issue for now...
		// Accident accident = accidentRepository.findOne(roadAccident.getAccidentId());
		// if (accident != null) {
		// return null;
		// }
		Accident accident = new Accident();
		accident.setAccidentIndex(roadAccident.getAccidentId());
		accident.setDate(DateTimeUtils.formatLocalDate(LocalDate.now()));
		accident.setDayOfWeek(LocalDate.now().getDayOfWeek().getValue());
		accident.setLatitude(roadAccident.getLatitude());
		accident.setLongitude(roadAccident.getLongitude());
		accident.setNumberOfCasualties(roadAccident.getNumberOfCasualties());
		accident.setNumberOfVehicles(roadAccident.getNumberOfVehicles());
		accident.setTime(DateTimeUtils.formatLocalTime(LocalTime.now()));
		// setting default values for NOT-NULL columns
		accident.setAccidentSeverity(accidentSeverityRepository.findOne(1));
		accident.setLocalDistrictAuthority(districtAuthorityRepository.findByCode(1));
		accident.setLightCondition(lightConditionRepository.findOne(1));
		accident.setPoliceForce(policeForceRepository.findOne(1));
		accident.setRoadSurfaceCondition(roadSurfaceConditionRepository.findOne(1));
		accident.setWeatherCondition(weatherConditionRepository.findOne(1));
		accidentRepository.save(accident);
		return accident;
	}

}