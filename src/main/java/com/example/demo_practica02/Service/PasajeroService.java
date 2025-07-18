package com.example.demo_practica02.Service;

import com.example.demo_practica02.Model.PasajeroModels;
import com.example.demo_practica02.Repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    public List<PasajeroModels> getAllPasajeros() {
        return pasajeroRepository.findAll();
    }

    public Optional<PasajeroModels> getPasajeroById(Long id) {
        return pasajeroRepository.findById(id);
    }

    public PasajeroModels createPasajero(PasajeroModels pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    public boolean deletePasajero(Long id) {
        if (pasajeroRepository.existsById(id)) {
            pasajeroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PasajeroModels> updatePasajero(Long id, PasajeroModels pasajeroDetails) {
        Optional<PasajeroModels> optionalPasajero = pasajeroRepository.findById(id);

        if (optionalPasajero.isPresent()) {
            PasajeroModels pasajeroToUpdate = optionalPasajero.get();
            pasajeroToUpdate.setNombre(pasajeroDetails.getNombre());
            pasajeroToUpdate.setApellido(pasajeroDetails.getApellido());
            pasajeroToUpdate.setFechaNacimiento(pasajeroDetails.getFechaNacimiento());
            pasajeroToUpdate.setGenero(pasajeroDetails.getGenero());
            pasajeroToUpdate.setNacionalidad(pasajeroDetails.getNacionalidad());
            pasajeroToUpdate.setTelefono(pasajeroDetails.getTelefono());
            pasajeroToUpdate.setCorreo_electronico(pasajeroDetails.getCorreo_electronico());
            pasajeroToUpdate.setDireccion(pasajeroDetails.getDireccion());

            return Optional.of(pasajeroRepository.save(pasajeroToUpdate));
        }

        return Optional.empty();
    }
}
