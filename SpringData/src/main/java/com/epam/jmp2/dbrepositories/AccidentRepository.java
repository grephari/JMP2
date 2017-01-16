package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.WeatherCondition;

public interface AccidentRepository extends JpaRepository<Accident, String> {
	
    public Accident findOne(String accidentId);

    public List<Accident> findByRoadSurfaceCondition(Integer road_surface);
    
    public List<Accident> findByWeatherCondition(WeatherCondition weatherCondition);
    
    public List<Accident> findByDate(String date);

}
