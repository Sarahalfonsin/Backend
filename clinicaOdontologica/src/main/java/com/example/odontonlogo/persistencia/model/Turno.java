package com.example.odontonlogo.persistencia.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;

    //SIN LOS DOS EAGER TRAE ARRAY TURNOS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_odontologo",nullable=false)
    private Odontologo odontologo;


    @ManyToOne(fetch = FetchType.EAGER)
    //es com la forenky
    @JoinColumn(name="id_paciente",referencedColumnName="id",nullable=false)
    private Paciente paciente;


}
