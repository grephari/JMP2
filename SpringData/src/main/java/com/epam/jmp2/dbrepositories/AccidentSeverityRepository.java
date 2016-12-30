package com.epam.jmp2.dbrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.AccidentSeverity;

public interface AccidentSeverityRepository extends JpaRepository<AccidentSeverity, Integer> {

	AccidentSeverity findOne(Integer code);

}
