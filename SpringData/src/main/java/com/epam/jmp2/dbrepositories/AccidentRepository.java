package com.epam.jmp2.dbrepositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.WeatherCondition;

public interface AccidentRepository extends JpaRepository<Accident, String> {
	Accident findOne(String accidentId);

	List<Accident> findByRoadSurfaceCondition(Integer road_surface);

//	List<Accident> findByWeatherConditionAndYear(Integer weatherCondition, String year);

	List<Accident> findByDate(Date date);

	Collection<Accident> findByWeatherCondition(WeatherCondition condition);


}
