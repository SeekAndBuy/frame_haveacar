package com.seekandbuy.haveacar.validator;

import com.seekandbuy.haveacar.domain.CandidateUser;

public class ValidatorCandidateUser implements Validator<CandidateUser>{

	@Override
	public boolean validator(CandidateUser candidateUser) {
		
		if(candidateUser.getJobCharacteristic() == null)
			return false;
		
		if(candidateUser.getName() == null)
			return false;
		
		if(candidateUser.getCpf() == null)
			return false;
		
		if(candidateUser.getEmail() == null)
			return false;
		
		return true;
	}
}
