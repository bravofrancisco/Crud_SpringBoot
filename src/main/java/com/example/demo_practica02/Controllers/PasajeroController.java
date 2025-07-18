package com.example.demo_practica02.Controllers;
import com.example.demo_practica02.Model.PasajeroModels;
import com.example.demo_practica02.Repository.PasajeroRepository;
import com.example.demo_practica02.Service.PasajeroService;
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
    private PasajeroService pasajeroService;

    @GetMapping
    private ResponseEntity<List<PasajeroModels>> getAllPasajero(){
        return  ResponseEntity.ok(  pasajeroService.getAllPasajeros());
    }
    @GetMapping("/{id}")
    private ResponseEntity<PasajeroModels>getPasajeroById(@PathVariable Long id){
        Optional<PasajeroModels> pasajeroModels = pasajeroService.getPasajeroById(id);
        return pasajeroModels.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Agregar datos
    @PostMapping
    public PasajeroModels createPasajeroModel(@Valid @RequestBody PasajeroModels pasajeroModels){
        return pasajeroService.createPasajero(pasajeroModels);
    }
    //Eliminar
    @DeleteMapping("/{id}")
    private ResponseEntity<Void>deletePasajeroModel(@PathVariable Long id){
        if (pasajeroService.deletePasajero(id))
        {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }
    //editar
    @PutMapping("/{id}")
    public ResponseEntity<PasajeroModels> updatePasajeroModel(@PathVariable Long id,
                                                              @Valid @RequestBody PasajeroModels pasajeroDetails) {
        Optional<PasajeroModels> updated = pasajeroService.updatePasajero(id, pasajeroDetails);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}