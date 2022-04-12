package com.example.odontonlogo.controller;

import com.example.odontonlogo.dto.PacienteDTO;
import com.example.odontonlogo.dto.TurnoDTO;
import com.example.odontonlogo.persistencia.model.Turno;
import com.example.odontonlogo.service.PacienteService;
import com.example.odontonlogo.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService service;

    //Guardar turno
    @PostMapping()
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoDTO turnoDTO) {
       service.guardarTurno(turnoDTO);

        return new ResponseEntity<>(turnoDTO, HttpStatus.OK);
    }

    //Mostrar todos
    @GetMapping()
    public ResponseEntity<?> mostrarTurnos() {
        Set<TurnoDTO> turnoDTO = service.listarTodos();
        return new ResponseEntity<>(turnoDTO, HttpStatus.OK);
    }

    //Listar 1
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id) {
        TurnoDTO turnoDTO = service.buscarID(id);
        return new ResponseEntity<>(turnoDTO, HttpStatus.OK);
    }

    //Actualizar
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDTO turnoDTO) {
        ResponseEntity response = null;
        if (service.buscarID(turnoDTO.getId()) == null) {
            response = new ResponseEntity<>(turnoDTO, HttpStatus.NOT_FOUND);
        } else {
            service.modificar(turnoDTO);
            response = new ResponseEntity<>("Turno modificado con id: " + turnoDTO.getId() + " " + turnoDTO.getOdontologo() + " " + turnoDTO.getPaciente(), HttpStatus.OK);

        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ResponseEntity response = null;
        if (service.buscarID(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            service.eliminar(id);
            response = new ResponseEntity<>("Turno  eliminado", HttpStatus.OK);
        }
        return  response;
    }
}
