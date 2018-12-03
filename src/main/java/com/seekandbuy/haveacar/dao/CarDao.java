package com.seekandbuy.haveacar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seekandbuy.haveacar.domain.Car;
import com.seekandbuy.haveacar.domain.Product;

@CrossOrigin(origins="http://localhost:4200")
@Repository
public interface CarDao extends GenericDao, JpaRepository<Car, Long>
{
	@Query("SELECT p FROM Car p WHERE USERID(p.Id) = USERID(:userId)")
	public List<Car> getCarBySalesmanId(@Param("userId") Long id);
}
