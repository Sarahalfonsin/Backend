package com.example.odontonlogo.persistencia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

@Table(name="Odontologos")
@Entity
public class Odontologo {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroMatricula;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "odontologo",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
}
