package com.example.odontonlogo.dto;

import com.example.odontonlogo.persistencia.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter


public class OdontologoDTO {

    private Long id;

    private int numeroMatricula;

    private String nombre;

    private String apellido;



}
