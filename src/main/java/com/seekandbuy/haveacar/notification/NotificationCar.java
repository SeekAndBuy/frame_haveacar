package com.seekandbuy.haveacar.notification;

import java.util.List;

import com.seekandbuy.haveacar.domain.CustomerUser;
import com.seekandbuy.haveacar.domain.Car;
import com.seekandbuy.haveacar.domain.CarCharacteristic;

public class NotificationCar extends Notification<CustomerUser, Car>{

	@Override
	public void sendNotification(Car product, List<CustomerUser> listOfUsers) {
		int whenNotification = 4;
		CarCharacteristic carCharacteristic = product.getCarCharacteristic();
		
		for(CustomerUser u: listOfUsers) {
			int matchs = this.countMatchs(u.getCarCharacteristic(), carCharacteristic);
			if(matchs>whenNotification) {
				sendMail.sendNotification("seekandbuyorganization@gmail.com",
						"12345678organization", u.getEmail(), "Seek and Buy", "Hey there! \n\n We have a new car for you =]");
			}
		}
		
	}
	
	private int countMatchs(CarCharacteristic charaUser, CarCharacteristic charaCar) {
		int equal = 0;
		
		if(charaUser.getEscolaridade().equals(charaCar.getEscolaridade()))
			equal += 1;
		if(charaUser.getArea().equals(charaCar.getArea()))
			equal += 1;
		if(charaUser.getCargo().equals(charaCar.getCargo()))
			equal += 1;
		if(charaUser.getIdioma().equals(charaCar.getIdioma()))
			equal += 1;
		
		return equal;
	}

}