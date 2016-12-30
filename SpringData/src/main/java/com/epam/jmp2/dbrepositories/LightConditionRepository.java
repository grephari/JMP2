package com.epam.jmp2.dbrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.LightCondition;

public interface LightConditionRepository extends JpaRepository<LightCondition, Integer> {

	LightCondition findOne(Integer code);

}
