package com.sandes.speedyDrive.repositores;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandes.speedyDrive.models.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, UUID>{

}
