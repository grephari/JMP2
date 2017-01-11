package com.epam.jmp2.dbrepositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.model.RoadAccident;

public interface RoadAccidentRepository extends JpaRepository<RoadAccident, String> {
	
	RoadAccident findOne(String accidentId);

	List<RoadAccident> findByDate(Date date);


}
