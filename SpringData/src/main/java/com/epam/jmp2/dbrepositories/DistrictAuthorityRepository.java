package com.epam.jmp2.dbrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.jmp2.entities.DistrictAuthority;

public interface DistrictAuthorityRepository extends JpaRepository<DistrictAuthority, Integer> {
	DistrictAuthority findByCode(Integer code);
}
