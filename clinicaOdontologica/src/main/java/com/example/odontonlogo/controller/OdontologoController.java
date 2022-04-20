package com.example.odontonlogo.controller;

import com.example.odontonlogo.dto.OdontologoDTO;
import com.example.odontonlogo.exception.BadRequestException;
import com.example.odontonlogo.exception.ResourceNotFoundException;
import com.example.odontonlogo.persistencia.model.Odontologo;

import com.example.odontonlogo.service.IOdontologoService;
import com.example.odontonlogo.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    //que implemente el service
@Autowired
    OdontologoService odontologoService;

//guardar
@PostMapping()
public ResponseEntity<?> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws BadRequestException {
    odontologoService.crear(odontologoDTO);
    //devuelve un 200
    return  new ResponseEntity<>("Se creó el odontologo: " + odontologoDTO.getNombre() + " " + odontologoDTO.getApellido(), HttpStatus.OK);
}

@GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable long id) throws BadRequestException, ResourceNotFoundException {
        OdontologoDTO odontologoDTO = odontologoService.buscarID(id);

    return  new ResponseEntity<>("Se encontro el odontologo: " + odontologoDTO.getNombre() + " " + odontologoDTO.getApellido(), HttpStatus.OK);

}
    //Listar todos
    @GetMapping()
    public ResponseEntity<?> listarTodos(){

    Set<OdontologoDTO> odontologoDTO = odontologoService.listarTodos();

    return  ResponseEntity.ok(odontologoDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable("id") Long id) throws BadRequestException, ResourceNotFoundException {

            odontologoService.eliminar(id);

        return new ResponseEntity<>("Odontólogo eliminado con id: " + id, HttpStatus.OK);

    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> modificar(@RequestBody OdontologoDTO odontologoDTO) throws BadRequestException {

            odontologoService.modificar(odontologoDTO);

        return new ResponseEntity<>("Se modifico al odontologo con id: " + odontologoDTO.getId() , HttpStatus.OK);
    }



}
