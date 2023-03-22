package com.sandes.speedyDrive.repositores;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandes.speedyDrive.models.CarModel;

public interface CarRepository extends JpaRepository<CarModel, UUID> {

	List<CarModel> findByclientIsNull();

}
