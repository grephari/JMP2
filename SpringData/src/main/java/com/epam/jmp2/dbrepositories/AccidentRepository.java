package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.RoadSurfaceCondition;
import com.epam.jmp2.entities.WeatherCondition;

public interface AccidentRepository extends JpaRepository<Accident, String> {
	
	Accident findOne(String accidentIndex);

    List<Accident> findByRoadSurfaceCondition(RoadSurfaceCondition roadSurfaceCondition);
    
    List<Accident> findAccidentsByWeatherCondition(WeatherCondition weatherCondition);
    
    List<Accident> findByDate(String date);


}
