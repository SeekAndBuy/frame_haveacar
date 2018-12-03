package com.seekandbuy.haveacar.match;

import java.util.List;

import com.seekandbuy.haveacar.domain.CustomerUser;
import com.seekandbuy.haveacar.domain.Car;
import com.seekandbuy.haveacar.domain.CarCharacteristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchCar extends SearchItems<CustomerUser, Car> {
	
	@Override
	public List<Car> ListAllProductsByUser(CustomerUser user, List<Car> allCars) {

		CarCharacteristic characteristicUser = user.getCarCharacteristic();
		
		//classe para armazenar a tupla <product, quantidade de matchs>
		class CharacteristicAndMatchs{
			public Car car;
			public int matchValue; 
			
			public CharacteristicAndMatchs(Car car, int matchValue) {
				this.car = car;
				this.matchValue =matchValue;
			}
			public Car getCar() {
				return this.car;
			}
			
			public int getMatchValue() {
				return this.matchValue;
			}
		}
		
		class SortbyCar implements Comparator<Car> 
		{ 
		    // Used for sorting in ascending order of 
		    // roll number 
			@Override
			public int compare(Car c1, Car c2) {
				
				double value = c1.getCarCharacteristic().getSalario() - c2.getCarCharacteristic().getSalario();
				
				if(value < 0)
					return 1;
				if(value > 0)
					return -1;
				else
					return 0;
			} 
		}
		
		List<CharacteristicAndMatchs> characteristicMatchs = new ArrayList<CharacteristicAndMatchs>();
		List<Car> productsByUser = new ArrayList<Car>();
		
		Collections.sort(allCars, new SortbyCar());
		
		for(Car p: allCars) {
			
			CarCharacteristic characteristicProduct = (CarCharacteristic) p.getCarCharacteristic();
			int matchSize = this.countMatchs(characteristicUser, characteristicProduct);		
			
			CharacteristicAndMatchs matchCharacter = new CharacteristicAndMatchs(p, matchSize);
			characteristicMatchs.add(matchCharacter);
		}
		
		Collections.sort(characteristicMatchs, 
		Comparator.comparingInt(CharacteristicAndMatchs::getMatchValue).reversed()); //ordenando em ordem decrescente
		
		for(CharacteristicAndMatchs c : characteristicMatchs) //armazenando apenas os produtos em productByUser
		{
			productsByUser.add(c.getCar());
		}
		
		
		return productsByUser;
	}
	
	//Metodo auxiliar para contar os matchs das caracteristicas do produto com as do usuario
	private int countMatchs(CarCharacteristic charaUser, CarCharacteristic charaCar) {
		int equal = 0;
		
		if(charaUser.getEscolaridade().equals(charaCar.getEscolaridade()))
			equal += 2;
		if(charaCar.getSalario() <= charaUser.getSalario())
			equal +=1;
		if(charaUser.getArea().equals(charaCar.getArea()))
			equal += 3;
		if(charaUser.getCargo().equals(charaCar.getCargo()))
			equal += 4;
		if(charaUser.getIdioma().equals(charaCar.getIdioma()))
			equal += 1;
		
		return equal;
	}

}