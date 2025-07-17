package com.example.demo_practica02.Repository;

import com.example.demo_practica02.Model.PasajeroModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasajeroRepository extends JpaRepository<PasajeroModels, Long> {

}
