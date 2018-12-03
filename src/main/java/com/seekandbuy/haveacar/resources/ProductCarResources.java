/*Promotion Controller*/
package com.seekandbuy.haveacar.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.seekandbuy.haveacar.domain.CustomerUser;
import com.seekandbuy.haveacar.domain.Car;
import com.seekandbuy.haveacar.exceptions.ProductNotFoundException;
import com.seekandbuy.haveacar.exceptions.UserNotFoundException;
import com.seekandbuy.haveacar.services.CustomerUserService;
import com.seekandbuy.haveacar.services.ProductCarService;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins="http://localhost:4200")
public class ProductCarResources implements GenericResources<Car>
{
	@Autowired
	private ProductCarService productService;
	
	@Autowired
	private CustomerUserService userService;
	
	public ProductCarResources(ProductCarService productService, CustomerUserService userService) 
	{
		this.productService = productService;
		this.userService = userService;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Car>> listItem() {
		return ResponseEntity.status(HttpStatus.OK).body(productService.listItem());
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createItem(@RequestBody Car product) {
		boolean createProduct = productService.createItem(product);
		
		if(!createProduct)	
		{
			return ResponseEntity.badRequest().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("/{id}").buildAndExpand(product.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/createandnotify/", method = RequestMethod.POST)
	public ResponseEntity<Void>  createAndNotify(@RequestBody Car product){
		List<CustomerUser> allUsers = null;	
		allUsers = userService.listItem();
		
		boolean createProduct = productService.createItemAndNotifyUser(product, allUsers);
		
		if(!createProduct)
			return ResponseEntity.badRequest().build();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(product.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Car>> findItem(@PathVariable("id") Long id) {
		Optional<Car> car = null;
		try
		{
			car = productService.findItem(id);
		}catch(ProductNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(car);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
		try
		{
			productService.deleteItem(id);
		}
		catch(ProductNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(@RequestBody Car product, @PathVariable("id") Long id) {
		product.setId(id); // Garantir que o que vai ser atualizado é o que está vindo na URI
		try
		{
			productService.updateItem(product);
		}
		catch(ProductNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/employer/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Car>> getJobByEmployerId(@PathVariable("id") Long id){
		List<Car> salesmanCarsOffered = null;
		
		try
		{
			salesmanCarsOffered = productService.getCarBySalesmanId(id);
		}
		catch(UserNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(salesmanCarsOffered);
	}
	
	@RequestMapping(value = "/bycharacteristics/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Car>> findCarByCandidateCharacteristic(@PathVariable("id") Long id){
		List<Car> productsByCharacteristic = null;
		
		List<Car> allCars = null;
		Optional<CustomerUser> candidate = null;		
		
		try
		{
			candidate = userService.findItem(id);
			CustomerUser user = (CustomerUser) candidate.get();
			
			allCars = productService.listItem();
			
			productsByCharacteristic = productService.listItemByUserCharacteristic(user, allCars);
		}
		catch(UserNotFoundException e)
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(productsByCharacteristic);
	}
	
}


