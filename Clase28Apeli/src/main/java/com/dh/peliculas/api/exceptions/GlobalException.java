package com.dh.peliculas.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {


    //pisamos el response default
//PARA BORRAR POR DEFECTO EL QUE GENERA SPRING BOOT, le pasamos lo que va a manejar
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException ex){
        //mensaje de la exepcion
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "GLOBAL");
    }

}
