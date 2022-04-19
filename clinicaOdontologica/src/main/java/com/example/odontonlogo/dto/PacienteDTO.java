package com.example.odontonlogo.dto;

import com.example.odontonlogo.persistencia.model.Domicilio;
import com.example.odontonlogo.persistencia.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacienteDTO {
    private Long id;
    private String apellido;
    private String nombre;
    private String email;
    private int dni;
    private Date fechaIngreso;
    private Domicilio domicilio;
}
