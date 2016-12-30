package com.epam.jmp2.dbrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.PoliceForce;

public interface PoliceForceRepository extends JpaRepository<PoliceForce, Integer> {

	PoliceForce findOne(Integer code);

}
