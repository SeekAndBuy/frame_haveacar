package com.seekandbuy.haveacar.validator;

import com.seekandbuy.haveacar.domain.SalesmanUser;

public class ValidatorSalesmanUser implements Validator<SalesmanUser>{

	@Override
	public boolean validator(SalesmanUser salesmanUser) {
		
		if(salesmanUser.getName() == null)
			return false;
		
		if(salesmanUser.getCnpj() == null)
			return false;
		
		if(salesmanUser.getEmail() == null)
			return false;
		
		return true;
	}
}
