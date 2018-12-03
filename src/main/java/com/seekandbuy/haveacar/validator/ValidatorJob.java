package com.seekandbuy.haveacar.validator;

import com.seekandbuy.haveacar.domain.Job;

public class ValidatorJob implements Validator<Job>{

	@Override
	public boolean validator(Job job) {
		
		if(job.getJobCharacteristic() == null)
			return false;
		
		if(job.getDate() == null)
			return false;
		
		if(job.getTituloDaVaga() == null)
			return false;
		
		if(job.getAddress() == null)
			return false;
		
		return true;
	}

}
