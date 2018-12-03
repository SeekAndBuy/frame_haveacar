package com.seekandbuy.haveacar.validator;

import com.seekandbuy.haveacar.domain.EmployerUser;

public class ValidatorEmployerUser implements Validator<EmployerUser>{

	@Override
	public boolean validator(EmployerUser employerUser) {
		
		if(employerUser.getName() == null)
			return false;
		
		if(employerUser.getCnpj() == null)
			return false;
		
		if(employerUser.getEmail() == null)
			return false;
		
		return true;
	}
}
