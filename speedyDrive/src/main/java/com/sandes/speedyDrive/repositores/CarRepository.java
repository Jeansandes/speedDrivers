package com.sandes.speedyDrive.repositores;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sandes.speedyDrive.models.CarModel;

public interface CarRepository extends JpaRepository<CarModel, UUID> {

	Page<CarModel> findByclientIsNull(Pageable pageable);



}
