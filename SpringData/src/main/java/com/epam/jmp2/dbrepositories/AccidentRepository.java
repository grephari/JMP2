package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.WeatherCondition;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, String> {
    Accident findOne(String accidentId);

 
    List<Accident> findByRoadSurfaceCondition(Integer road_surface);

    List<Accident> findByWeatherConditionAndDateContaining(WeatherCondition weather,String year);
    
    List<Accident> findAllByDate(String date);

    

}
