package com.epam.jmp2.dbrepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.Accident;

public interface AccidentRepository extends JpaRepository<Accident, String> {
    Accident findOne(String accidentId);

 
    List<Accident> findByRoadSurfaceCondition(Integer road_surface);


}
