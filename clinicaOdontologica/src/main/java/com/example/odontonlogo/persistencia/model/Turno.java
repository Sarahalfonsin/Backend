package com.example.odontonlogo.persistencia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
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
    private Date fecha;
    private Time hora;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_odontologo",nullable=false)
    private Odontologo odontologo;

    @ManyToOne(fetch=FetchType.EAGER)
    //es com la forenky
    @JoinColumn(name="id_paciente",nullable=false)
    private Paciente paciente;


}
