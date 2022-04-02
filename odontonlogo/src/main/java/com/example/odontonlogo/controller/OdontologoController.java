package com.example.odontonlogo.controller;

import com.example.odontonlogo.dominio.Odontologo;

import com.example.odontonlogo.repository.impl.OdontologoDAOH2;
import com.example.odontonlogo.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

private OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
    //Guardar
    @PostMapping("/registrar")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo ){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    //buscar
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarId(@PathVariable("id") long id){
        Odontologo odontologo = odontologoService.buscarOdontologo(id);

        return ResponseEntity.ok(odontologo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable("id") long id){
        ResponseEntity response = null;

        //si no encuentra
        if(odontologoService.buscarOdontologo(id)==null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            odontologoService.eliminarOdontolgo(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  response;
    }
    @PutMapping("/actualizar")
    public  ResponseEntity<Odontologo> modificar(@RequestBody Odontologo odontologo){
        ResponseEntity response = null;
        //si no encuentra
        if(odontologoService.buscarOdontologo(odontologo.getId()) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(odontologoService.modificarOdontologo(odontologo),HttpStatus.OK);
        }
        return  response;
    }

}
