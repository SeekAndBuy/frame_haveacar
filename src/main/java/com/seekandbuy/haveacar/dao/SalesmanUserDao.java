package com.seekandbuy.haveacar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seekandbuy.haveacar.domain.SalesmanUser;

@CrossOrigin(origins="http://localhost:4200")
@Repository
public interface SalesmanUserDao extends GenericDao, JpaRepository<SalesmanUser, Long>{

	@Query("SELECT u FROM SalesmanUser u WHERE u.email = :userEmail")
	public SalesmanUser findUserByEmail(@Param("userEmail") String email);
	
}
