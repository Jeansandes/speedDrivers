package com.sandes.speedyDrive.dtos;

import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientDto {

	@NotBlank
	private String name;
	@NotBlank
	private String cpf;
	@NotBlank
	private String address;

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

	
}
