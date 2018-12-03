package com.seekandbuy.haveacar.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.seekandbuy.haveacar.dao.SalesmanUserDao;
import com.seekandbuy.haveacar.domain.SalesmanUser;
import com.seekandbuy.haveacar.domain.User;
import com.seekandbuy.haveacar.exceptions.UserNotFoundException;
import com.seekandbuy.haveacar.validator.ValidatorSalesmanUser;

@Service
public class SalesmanUserService extends GenericService<SalesmanUser>
{	
	@Autowired
	private SalesmanUserDao SalesmanUserDao;
	
	public SalesmanUserService() {
		super.validateItem = new ValidatorSalesmanUser();
	}
	
	@Override
	public List<SalesmanUser> listItem()
	{	
		return SalesmanUserDao.findAll();  
	}
	
	@Override
	public Optional<SalesmanUser> findItem(Long id)
	{
		Optional<SalesmanUser> user = SalesmanUserDao.findById(id);
		
		if(user == null)
		{
			throw new UserNotFoundException("Salesman can not be found");
		}
		
		return user;
	}
	
	public static <T> List<T> toList(Optional<T> option) 
	{
	    if (option.isPresent())
	        return Collections.singletonList(option.get());
	    else
	        return Collections.emptyList();
	}
		
	@Override
	public boolean createItem(SalesmanUser user) 
	{	
		user.setId(null);
		
		if(this.validateItem(user)) {
			String password = user.getPassword();
			String token = auth.tokenizerPassword(password);
			user.setPassword(token);
			SalesmanUserDao.save(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void deleteItem(Long id) 
	{
		try 
		{
			SalesmanUserDao.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new UserNotFoundException("Salesman can not be found");
		}
	}
	
	@Override
	public void updateItem(SalesmanUser user)
	{
		verifyExistence(user);
		SalesmanUserDao.save(user);
	}
	
	//Semântica melhor, só verifica existência 
	@Override
	public void verifyExistence(SalesmanUser user)
	{
		findItem(user.getId());
	}	
		
	public User findUser(String password, String email)
	{
		User user = this.findUserByEmail(email);
		
		auth.findTokenByPassword(password);
		
		//Se o usuário é encontrado pelo email, então verifica a senha.
		//Se não houverem exceções, então retorna o usuário
		return user;	
	}
	
	private User findUserByEmail(String email)
	{
		User user;
		
		user = SalesmanUserDao.findUserByEmail(email);
		
		if(user == null)
		{
			throw new UserNotFoundException("Salesman can not be found");
		}		
		return user;
	}
}
