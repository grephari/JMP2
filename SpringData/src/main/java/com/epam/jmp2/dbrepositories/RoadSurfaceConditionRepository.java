package com.epam.jmp2.dbrepositories;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp2.entities.RoadSurfaceCondition;
import com.epam.jmp2.model.RoadAccident;

public interface RoadSurfaceConditionRepository extends CrudRepository<RoadSurfaceCondition,Integer>{
    Iterable<RoadAccident> getAllAccidentsByLabel(String label);
}
