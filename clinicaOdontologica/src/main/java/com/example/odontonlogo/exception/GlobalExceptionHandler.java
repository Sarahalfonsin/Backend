package com.example.odontonlogo.exception;


import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static  final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BadRequestException.class})
    public  ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex){
        //mensaje de la exception
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public  ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException ex){
        //mensaje de la exception
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
