package com.epam.jmp2.dbrepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.jmp2.entities.DistrictAuthority;

@Repository
public interface DistrictAuthorityRepository extends JpaRepository<DistrictAuthority, Integer> {
	DistrictAuthority findByCode(Integer code);
}
