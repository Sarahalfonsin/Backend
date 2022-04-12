package com.example.odontonlogo.persistencia.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name="Pacientes")
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellido;
    private String nombre;
    private String email;
    private int dni;
    private Date fechaIngreso;
    //Para que se borre esto y la relacion domicilio
    @OneToOne(cascade = CascadeType.ALL)
    //EN LA TABLA PRINCIPAL VA LA la forenky
    @JoinColumn(name="id_domicilio",referencedColumnName="id")
    private Domicilio domicilio;

    //sin fetch y sin cascade te trae el array de turnos
    @OneToMany(mappedBy = "paciente", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    //para que no se haga un bucle infinito porque es una lista, sin json ignore trae array
    @JsonIgnore
    //trae todos los turnos asociados al paciente
    private Set<Turno> turnos = new HashSet<>();

}
