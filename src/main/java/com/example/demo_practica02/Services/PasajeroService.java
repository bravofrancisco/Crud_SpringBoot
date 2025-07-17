package com.example.demo_practica02.Services;

import com.example.demo_practica02.Model.PasajeroModels;

import java.util.List;
import java.util.Optional;

public interface PasajeroService {

    List<PasajeroModels> getAllPasajeros();
    Optional<PasajeroModels> getPasajeroById(Long id);
    PasajeroModels createPasajero(PasajeroModels pasajero);
    PasajeroModels updatePasajero(Long id, PasajeroModels pasajeroDetails);
    boolean deletePasajero(Long id); // Retorna boolean para indicar éxito/falla
}
