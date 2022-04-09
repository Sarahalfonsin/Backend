package com.example.odontonlogo.dto;

import com.example.odontonlogo.persistencia.model.Odontologo;
import com.example.odontonlogo.persistencia.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TurnoDTO {
    private Long id;
    private Date fecha;
    private Time hora;

    private Odontologo odontologo;

    private Paciente paciente;
}
