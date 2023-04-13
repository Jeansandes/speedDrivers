package com.sandes.speedyDrive.dtos;

import java.util.List;

import com.sandes.speedyDrive.models.CarModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientDto {

	@NotBlank()
	private String name;
	@NotBlank()
	@Size(max = 11,min = 11)
	private String cpf;
	@NotBlank()
	private String address;
	private List<CarModel> cars;
	
	public ClientDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CarModel> getCars() {
		return cars;
	}

	public void setCars(List<CarModel> cars) {
		this.cars = cars;
	}
	
	
}
