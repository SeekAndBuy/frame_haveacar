package com.seekandbuy.haveacar.services;

import com.seekandbuy.haveacar.dao.CarDao;
import com.seekandbuy.haveacar.domain.CustomerUser;
import com.seekandbuy.haveacar.domain.Car;
import com.seekandbuy.haveacar.exceptions.ProductNotFoundException;
import com.seekandbuy.haveacar.match.SearchCar;
import com.seekandbuy.haveacar.notification.NotificationCar;
import com.seekandbuy.haveacar.validator.ValidatorCar;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class ProductCarService extends GenericService<Car>
{	
	@Autowired
	private CarDao productDao;
	
	SearchCar searchCar = new SearchCar();
	
	public ProductCarService()
	{
		super.validateItem = new ValidatorCar();
	}
	
	public List<Car> listItemByUserCharacteristic(CustomerUser user, List<Car> allCars){

		return searchCar.ListAllProductsByUser(user, allCars);
	}
	
	@Override
	public List<Car> listItem()
	{
		return productDao.findAll();  
	}
	
	@Override
	public Optional<Car> findItem(Long id)
	{
		Optional<Car> car = productDao.findById(id);
		
		if(car == null)
		{
			throw new ProductNotFoundException("Car can not be found");
		}
		
		return car;
	}
	
	public boolean createItemAndNotifyUser(Car product, List<CustomerUser> listOfUsers) {
		NotificationCar notificationBeer = new NotificationCar();
		if(this.createItem(product)) {
			notificationBeer.sendNotification(product, listOfUsers);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean createItem(Car product) 
	{
		if(validateItem(product)) {
			product.setId(null); //Garantir que criaremos uma instância nova e não atualizaremos nenhuma	
			product.getCarCharacteristic().setId(null);
			productDao.save(product);
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
			productDao.deleteById(id);
		}
		catch(EmptyResultDataAccessException e)
		{
			throw new ProductNotFoundException("Car can not be found");
		}
	}
	
	@Override
	public void updateItem(Car car)
	{
		verifyExistence(car);
		productDao.save(car);
	}
	
	@Override
	public void verifyExistence(Car car)
	{
		findItem(car.getId());
	}
	
	public List<Car> getCarBySalesmanId(Long id) 
	{
		return productDao.getCarBySalesmanId(id);
	}
	
}
