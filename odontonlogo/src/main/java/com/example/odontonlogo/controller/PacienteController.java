package com.example.odontonlogo.controller;


import com.example.odontonlogo.repository.impl.PacienteDAOH2;
import com.example.odontonlogo.dominio.Paciente;

import com.example.odontonlogo.service.PacienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

private PacienteService pacienteService = new PacienteService(new PacienteDAOH2());

    //Guardar
    @PostMapping("/registrar")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente ){
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    //buscar
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarId(@PathVariable("id") long id){
        Paciente paciente = pacienteService.buscarId(id);

        return ResponseEntity.ok(paciente);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable("id") long id){
        ResponseEntity response = null;

        //si no encuentra
        if(pacienteService.buscarId(id)==null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            pacienteService.eliminar(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  response;
    }



    }





