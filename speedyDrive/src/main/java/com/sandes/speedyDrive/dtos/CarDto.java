package com.sandes.speedyDrive.dtos;


import com.sandes.speedyDrive.models.ClientModel;

import jakarta.validation.constraints.NotBlank;

public class CarDto {


	@NotBlank
	private String name;
	@NotBlank
	private String model;
	private ClientModel client;
	
	public CarDto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public ClientModel getClient() {
		return client;
	}

	public void setClient(ClientModel client) {
		this.client = client;
	}
	
}
