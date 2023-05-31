package com.sandes.speedyDrive.dtos;

import java.util.List;

import com.sandes.speedyDrive.models.ClientModel;

import jakarta.validation.constraints.NotBlank;

public class AddressDto {
	@NotBlank
	private String city;
	@NotBlank
	private String district;
	@NotBlank
	private String street;
	@NotBlank
	private int houseNumber;
	@NotBlank
	private String complement;
	private List<ClientModel> clients;
	
	public AddressDto() {}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public List<ClientModel> getClients() {
		return clients;
	}

	public void setClients(List<ClientModel> clients) {
		this.clients = clients;
	}
	
	

}
