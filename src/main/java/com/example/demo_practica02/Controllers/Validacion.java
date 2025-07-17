package com.example.demo_practica02.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Validacion {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex){
        String mensajeError = "Ocurrio un error inesperado. por favor intente mas tarde";
        return  new ResponseEntity<>(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
