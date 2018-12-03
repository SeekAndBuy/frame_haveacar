package com.seekandbuy.haveacar.validator;

import com.seekandbuy.haveacar.domain.Car;

public class ValidatorCar implements Validator<Car>{

	@Override
	public boolean validator(Car car) {
		
		if(car.getCarCharacteristic() == null)
			return false;
		
		if(car.getDate() == null)
			return false;
		
		if(car.getTituloDaVaga() == null)
			return false;
		
		if(car.getAddress() == null)
			return false;
		
		return true;
	}

}
