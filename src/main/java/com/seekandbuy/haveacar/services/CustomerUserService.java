package com.seekandbuy.haveacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.seekandbuy.haveacar.dao.CustomerUserDao;
import com.seekandbuy.haveacar.domain.CustomerUser;
import com.seekandbuy.haveacar.domain.User;
import com.seekandbuy.haveacar.exceptions.UserNotFoundException;
import com.seekandbuy.haveacar.validator.ValidatorCustomerUser;

@Service
public class CustomerUserService extends GenericService<CustomerUser>
{	
	@Autowired
	private CustomerUserDao customerUserDao;
	
	public CustomerUserService() {
		super.validateItem = new ValidatorCustomerUser();
	}
	
	@Override
	public List<CustomerUser> listItem()
	{	
		return customerUserDao.findAll();  
	}
	
	@Override
	public Optional<CustomerUser> findItem(Long id)
	{
		Optional<CustomerUser> user = customerUserDao.findById(id);
		
		if(user == null)
		{
			throw new UserNotFoundException("Customer can not be found");
		}
		
		return user;
	}
			
	@Override
	public boolean createItem(CustomerUser user) 
	{	
		user.setId(null); //Garantir que criaremos uma instância nova e não atualizaremos nenhuma
		user.getCarCharacteristic().setId(null);		

		if(this.validateItem(user)) {
			String password = user.getPassword();
				
			String token = auth.tokenizerPassword(password);
			user.setPassword(token);
		
			customerUserDao.save(user);
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
			customerUserDao.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new UserNotFoundException("Customer can not be found");
		}
	}
	
	@Override
	public void updateItem(CustomerUser user)
	{
		verifyExistence(user);
		customerUserDao.save(user);
	}
	
	//Semântica melhor, só verifica existência 
	@Override
	public void verifyExistence(CustomerUser user)
	{
		findItem(user.getId());
	}	
		
	public User findUser(String password, String email)
	{
		User user = findUserByEmail(email);
		
		auth.findTokenByPassword(password);
		
		//Se o usuário é encontrado pelo email, então verifica a senha.
		//Se não houverem exceções, então retorna o usuário
		return user;	
	}
	
	private User findUserByEmail(String email)
	{
		User user;
		
		user = customerUserDao.findUserByEmail(email);
		
		if(user == null)
		{
			throw new UserNotFoundException("Customer can not be found");
		}		
		return user;
	}
}
