package com.epam.jmp2.dbrepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp2.entities.RoadSurfaceCondition;
import com.epam.jmp2.model.RoadAccident;

@Repository
public interface RoadSurfaceConditionRepository extends CrudRepository<RoadSurfaceCondition, Integer> {

	Iterable<RoadAccident> getAllAccidentsByLabel(String label);

	RoadSurfaceCondition findOne(Integer code);

}
