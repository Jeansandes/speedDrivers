package com.sandes.speedyDrive.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;

import com.sandes.speedyDrive.dtos.CarDto;
import com.sandes.speedyDrive.models.CarModel;

public class CarClientSave {

	
	public void transferData(CarDto carsdto, CarModel carModel) {
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
	}
}
