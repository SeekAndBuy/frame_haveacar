package com.seekandbuy.haveacar.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.seekandbuy.haveacar.dao.CustomerUserDao;
import com.seekandbuy.haveacar.domain.User;
import com.seekandbuy.haveacar.exceptions.ProductNotFoundException;
import com.seekandbuy.haveacar.exceptions.TokenNotFoundException;

public class SavePassword {
	private CustomerUserDao userDao;
	private Map<String, Object> codification;
	
	public SavePassword(){
		codification = new HashMap<String, Object>();
	}
	
	public String tokenizerPassword(String password) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		boolean validToken = false;
		
		String token = null;
		while(!validToken) {
			token = myRandom.substring(0,20); //gera uma chave de token
			if(!codification.containsKey(token)) { //se essa chave nao estiver na hash, adiciona ligada o password informado
				validToken = true;
			}
		}
		codification.put(password, token);
		return token;
	}
	
	public String findTokenByPassword(String password){
		
		String token = (String) codification.get(password);
		if(token == null) {
			throw new TokenNotFoundException("Token can not be found. Please, put the correct password!");
		}
		return token;
		
	}
}
