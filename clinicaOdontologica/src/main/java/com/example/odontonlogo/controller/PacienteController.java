package com.example.odontonlogo.controller;


import com.example.odontonlogo.dto.OdontologoDTO;
import com.example.odontonlogo.dto.PacienteDTO;
import com.example.odontonlogo.persistencia.model.Paciente;

import com.example.odontonlogo.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

@Autowired
    PacienteService pacienteService;

//guardar
    @PostMapping()
    public ResponseEntity<?> registrarPaciente (@RequestBody PacienteDTO pacienteDTO){
        pacienteService.crear(pacienteDTO);
        return  new ResponseEntity<>(pacienteDTO,HttpStatus.OK);
    }
    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        PacienteDTO pacienteDTO = pacienteService.buscarID(id);
        return new ResponseEntity<>(pacienteDTO,HttpStatus.OK);
    }
    //buscarEmail
    @GetMapping("/{email}")
    public ResponseEntity<?> buscarEmail(@PathVariable String email) {
        PacienteDTO pacienteDTO = pacienteService.findOneByEmail(email);
        return new ResponseEntity<>(pacienteDTO,HttpStatus.OK);
    }
    //Todos
    @GetMapping()
    public ResponseEntity<?> listarTodos(){
        Set<PacienteDTO> pacienteDTO = pacienteService.listarTodos();
        return  new ResponseEntity<>(pacienteDTO,HttpStatus.OK);
    }
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        ResponseEntity response = null;
        if(pacienteService.buscarID(id) ==null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else {
            pacienteService.eliminar(id);
            response = new ResponseEntity<>("Paciente eliminado con id: " + id, HttpStatus.OK);
        }
        return response;
    }
    //Modificar
    @PutMapping("/actualizar")
    public ResponseEntity<?> modificar(@RequestBody PacienteDTO pacienteDTO){
        ResponseEntity<?> response = null;

        if(pacienteService.buscarID(pacienteDTO.getId()) ==null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            pacienteService.modificar(pacienteDTO);
            response = new ResponseEntity<>("Paciente modificado con id: " + pacienteDTO.getId(), HttpStatus.OK);
        }
        return response;
    }


    }





