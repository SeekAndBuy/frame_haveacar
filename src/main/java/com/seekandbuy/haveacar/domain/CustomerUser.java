package com.seekandbuy.haveacar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="CustomerUser")
public class CustomerUser extends User {

	@JsonInclude(Include.NON_NULL)
	@Cascade(CascadeType.PERSIST)
	@ManyToOne
	private CarCharacteristic carCharacteristic;
	
	@JsonInclude(Include.NON_NULL)
	@Cascade(CascadeType.PERSIST)
	@Column(unique = true)
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public CarCharacteristic getCarCharacteristic() {
		return carCharacteristic;
	}
	
	public void setCarCharacteristic(CarCharacteristic carCharacteristic) {
		this.carCharacteristic = carCharacteristic;
	}
	
}
