package com.seekandbuy.haveacar.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="Car")
public class Car extends Product {
	
	@JsonInclude(Include.NON_NULL)
	@Cascade(CascadeType.PERSIST)
	@ManyToOne
	private CarCharacteristic carCharacteristic;
	
	@JsonInclude(Include.NON_NULL)
	@Cascade(CascadeType.PERSIST)
	@ManyToOne
	private SalesmanUser user;
		
	@JsonInclude(Include.NON_NULL)
	private String tituloDaVaga;	
	
	public String getTituloDaVaga() {
		return tituloDaVaga;
	}

	public void setTituloDaVaga(String tituloDaVaga) {
		this.tituloDaVaga = tituloDaVaga;
	}

	public CarCharacteristic getCarCharacteristic() {
		return carCharacteristic;
	}
	
	public void setCarCharacteristic(CarCharacteristic carCharacteristic) {
		this.carCharacteristic = carCharacteristic;
	}

	public SalesmanUser getUser() {
		return user;
	}

	public void setUser(SalesmanUser user) {
		this.user = user;
	}
}
