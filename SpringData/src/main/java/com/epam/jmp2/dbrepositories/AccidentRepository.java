package com.epam.jmp2.dbrepositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.Accident;
import com.epam.jmp2.model.RoadAccident;

public interface AccidentRepository extends JpaRepository<Accident, String> {
	
	
    Accident findOne(String accidentId);

 
    List<Accident> findByRoadSurfaceCondition(Integer road_surface);
    
    Iterable findAllAccidentsByRoadCondition(Integer road_cond);


	Iterable<RoadAccident> findAccidentByDate(Date date);
	
}
