package com.example.odontonlogo.dto;

import com.example.odontonlogo.persistencia.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OdontologoDTO {

    private Long id;
    private int numeroMatricula;

    private String nombre;

    private String apellido;

    private Set<Turno> turnos;


}
