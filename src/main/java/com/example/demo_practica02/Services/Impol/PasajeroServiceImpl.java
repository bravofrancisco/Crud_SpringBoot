package com.example.demo_practica02.Services.Impol;

import com.example.demo_practica02.Model.PasajeroModels;
import com.example.demo_practica02.Repository.PasajeroRepository;
import com.example.demo_practica02.Services.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importar Transactional
import java.util.List;
import java.util.Optional;

@Service // Anotación clave para que Spring reconozca esto como un componente de servicio
public class PasajeroServiceImpl implements PasajeroService {

    @Autowired // Inyecta el repositorio, ¡no el controlador!
    private PasajeroRepository pasajeroRepository;

    @Override
    @Transactional(readOnly = true) // Optimiza para operaciones de solo lectura
    public List<PasajeroModels> getAllPasajeros() {
        return pasajeroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PasajeroModels> getPasajeroById(Long id) {
        return pasajeroRepository.findById(id);
    }

    @Override
    @Transactional // Para operaciones de escritura
    public PasajeroModels createPasajero(PasajeroModels pasajero) {
        // Aquí podrías añadir lógica de negocio adicional antes de guardar
        // Por ejemplo, validaciones más complejas, asignación de valores predeterminados, etc.
        return pasajeroRepository.save(pasajero);
    }

    @Override
    @Transactional
    public PasajeroModels updatePasajero(Long id, PasajeroModels pasajeroDetails) {
        Optional<PasajeroModels> optionalPasajero = pasajeroRepository.findById(id);

        if (optionalPasajero.isEmpty()) {
            // Podrías lanzar una excepción personalizada aquí para un manejo más robusto
            throw new RuntimeException("Pasajero con ID " + id + " no encontrado.");
        }

        PasajeroModels pasajeroToUpdate = optionalPasajero.get();

        // Actualizar campos (excepto id y fechaRegistro) - Lógica de negocio
        pasajeroToUpdate.setNombre(pasajeroDetails.getNombre());
        pasajeroToUpdate.setApellido(pasajeroDetails.getApellido());
        pasajeroToUpdate.setFechaNacimiento(pasajeroDetails.getFechaNacimiento());
        pasajeroToUpdate.setGenero(pasajeroDetails.getGenero());
        pasajeroToUpdate.setNacionalidad(pasajeroDetails.getNacionalidad());
        pasajeroToUpdate.setTelefono(pasajeroDetails.getTelefono());
        pasajeroToUpdate.setCorreo_electronico(pasajeroDetails.getCorreo_electronico());
        pasajeroToUpdate.setDireccion(pasajeroDetails.getDireccion());

        return pasajeroRepository.save(pasajeroToUpdate);
    }

    @Override
    @Transactional
    public boolean deletePasajero(Long id) {
        if (pasajeroRepository.existsById(id)) {
            pasajeroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}