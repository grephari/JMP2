package com.epam.jmp2.service.impl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    	return accidentRepository.findOne(accidentId);
    }

    public Iterable<RoadAccident> getAllAccidentsByRoadCondition(Integer label) {
    	return accidentRepository.queryByRoadSurfaceCondition(label);
    }

    public Iterable<RoadAccident> getAllAccidentsByWeatherConditionAndYear(
            Integer weatherCondition, String year) {

		return accidentRepository.queryByWeatherConditionAndYear(weatherCondition, year);
    }

    public Iterable<RoadAccident> getAllAccidentsByDate(Date date) {
    	return accidentRepository.findByDate(new SimpleDateFormat("yyyy-MM-dd").format(date));

    }
    public Boolean update(RoadAccident roadAccident) {
    	Accident accident = accidentRepository.findOne(roadAccident.getAccidentId());
    	try {
			accident.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(roadAccident.getDate()));
			accident.setDayOfWeek(roadAccident.getDayOfWeek().getValue());
			accident.setLatitude(roadAccident.getLatitude());
			accident.setLongitude(roadAccident.getLongitude());
			accident.setNumberOfCasualties(roadAccident.getNumberOfCasualties());
			accident.setNumberOfVehicles(roadAccident.getNumberOfVehicles());
			accident.setTime(DateTimeFormatter.ofPattern("H:mm").format(roadAccident.getTime()));
			accidentRepository.save(accident);
		} catch (Throwable t) {
			t.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
    }

	public DistrictAuthorityRepository getDistrictAuthorityRepository() {
		return districtAuthorityRepository;
	}

	public void setDistrictAuthorityRepository(DistrictAuthorityRepository districtAuthorityRepository) {
		this.districtAuthorityRepository = districtAuthorityRepository;
	}
	
	@Override
	public RoadAccident getAccidentById(String accidentId) {
		return accidentRepository.queryById(accidentId);
	}
	
	@Override
	public List<Accident> findAll(int page) {
		return accidentRepository.findAll(new PageRequest(page, 15)).getContent();
	}
}