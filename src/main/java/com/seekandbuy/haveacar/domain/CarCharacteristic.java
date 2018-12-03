package com.seekandbuy.haveacar.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class CarCharacteristic extends Characteristic{
	
	@JsonInclude(Include.NON_NULL)
	private String brand;
		
	@JsonInclude(Include.NON_NULL)
	private String carModel;
	
	@JsonInclude(Include.NON_NULL)
	private  String year;
	
	@JsonInclude(Include.NON_NULL)
	private  Float price;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	
	
}
