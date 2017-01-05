package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.entities.RoadSurfaceCondition;
import com.epam.jmp2.entities.WeatherCondition;

public interface AccidentRepository extends JpaRepository<Accident, String> {

	Accident findOne(String accidentId);

    List<Accident> findByRoadSurfaceCondition(RoadSurfaceCondition roadSurfaceCondition);
    
    List<Accident> findAccidentsByWeatherCondition(WeatherCondition weatherCondition);
    
    List<Accident> findByDate(String date);
    
	@Modifying
    @Transactional
    @Query("delete from Accident acc where acc.accidentIndex = ?1")
    void deleteByAccidentIndex(String accidentIndex);
    
}
