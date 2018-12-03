package com.seekandbuy.haveacar.validator;

import com.seekandbuy.haveacar.domain.CustomerUser;

public class ValidatorCustomerUser implements Validator<CustomerUser>{

	@Override
	public boolean validator(CustomerUser customerUser) {
		
		if(customerUser.getCarCharacteristic() == null)
			return false;
		
		if(customerUser.getName() == null)
			return false;
		
		if(customerUser.getCpf() == null)
			return false;
		
		if(customerUser.getEmail() == null)
			return false;
		
		return true;
	}
}
