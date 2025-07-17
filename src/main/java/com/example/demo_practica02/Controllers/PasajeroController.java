package com.example.demo_practica02.Controllers;
import com.example.demo_practica02.Model.PasajeroModels;
import com.example.demo_practica02.Repository.PasajeroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pasajero")
public class PasajeroController {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @GetMapping
    private ResponseEntity<List<PasajeroModels>> getAllPasajero(){
        return  ResponseEntity.ok(  pasajeroRepository.findAll());
    }
    @GetMapping("/{id}")
    private ResponseEntity<PasajeroModels>getPasajeroById(@PathVariable Long id){
        Optional<PasajeroModels> pasajeroModels = pasajeroRepository.findById(id);
        return pasajeroModels.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Agregar datos
    @PostMapping
    public PasajeroModels createPasajeroModel(@Valid @RequestBody PasajeroModels pasajeroModels){
        return pasajeroRepository.save(pasajeroModels);
    }
    //Eliminar
    @DeleteMapping("/{id}")
    private ResponseEntity<Void>deletePasajeroModel(@PathVariable Long id){
        try {
            if (pasajeroRepository.existsById(id)) {
                pasajeroRepository.deleteById(id);
                return ResponseEntity.noContent().build();

            }else {
                return  ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //editar
    @PutMapping("/{id}")
    public ResponseEntity<PasajeroModels> updatePasajeroModel(@PathVariable Long id,
                                                              @Valid @RequestBody PasajeroModels pasajeroDetails) {
        Optional<PasajeroModels> optionalPasajero = pasajeroRepository.findById(id);

        if (optionalPasajero.isEmpty()) {
            return ResponseEntity.notFound().build();  // 404 si no existe
        }
        PasajeroModels pasajeroToUpdate = optionalPasajero.get();

        // Actualizar campos (excepto id y fechaRegistro)
        pasajeroToUpdate.setNombre(pasajeroDetails.getNombre());
        pasajeroToUpdate.setApellido(pasajeroDetails.getApellido());
        pasajeroToUpdate.setFechaNacimiento(pasajeroDetails.getFechaNacimiento());
        pasajeroToUpdate.setGenero(pasajeroDetails.getGenero());
        pasajeroToUpdate.setNacionalidad(pasajeroDetails.getNacionalidad());
        pasajeroToUpdate.setTelefono(pasajeroDetails.getTelefono());
        pasajeroToUpdate.setCorreo_electronico(pasajeroDetails.getCorreo_electronico());
        pasajeroToUpdate.setDireccion(pasajeroDetails.getDireccion());

        PasajeroModels updatedPasajero = pasajeroRepository.save(pasajeroToUpdate);
        return ResponseEntity.ok(updatedPasajero);
    }
}
