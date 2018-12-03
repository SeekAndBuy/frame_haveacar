package com.seekandbuy.haveacar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seekandbuy.haveacar.domain.CustomerUser;

@CrossOrigin(origins="http://localhost:4200")
@Repository
public interface CustomerUserDao extends GenericDao, JpaRepository<CustomerUser, Long>{

	@Query("SELECT u FROM CustomerUser u WHERE u.email = :userEmail")
	public CustomerUser findUserByEmail(@Param("userEmail") String email);
	
}
