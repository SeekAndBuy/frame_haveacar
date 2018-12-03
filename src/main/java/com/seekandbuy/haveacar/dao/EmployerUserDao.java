package com.seekandbuy.haveacar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seekandbuy.haveacar.domain.EmployerUser;

@CrossOrigin(origins="http://localhost:4200")
@Repository
public interface EmployerUserDao extends GenericDao, JpaRepository<EmployerUser, Long>{

	@Query("SELECT u FROM EmployerUser u WHERE u.email = :userEmail")
	public EmployerUser findUserByEmail(@Param("userEmail") String email);
	
}
