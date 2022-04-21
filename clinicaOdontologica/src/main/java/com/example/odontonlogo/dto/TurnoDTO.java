package com.example.odontonlogo.dto;

import com.example.odontonlogo.persistencia.model.Odontologo;
import com.example.odontonlogo.persistencia.model.Paciente;
import com.example.odontonlogo.persistencia.model.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;

    private Odontologo odontologo;

    private Paciente paciente;
}
