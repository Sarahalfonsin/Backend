package com.example.odontonlogo.dominio;

import java.time.LocalDate;
import java.util.Date;

public class Paciente {
    private Long id;
    private String apellido;
    private String nombre;
    private String email;
    private int dni;
    private String fechaIngreso;
    private Domicilio domicilio;

    public Paciente(){

    }

    public Paciente(Long id, String apellido, String nombre, String email, int dni, String fechaIngreso, Domicilio domicilio) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Paciente(String apellido, String nombre, String email, int dni, String fechaIngreso, Domicilio domicilio) //Odontologo odontologo
     {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
     }


    public void setDomicilio(Domicilio domicilio) {
       this.domicilio = domicilio;
    }


    public Domicilio getDomicilio() {
        return domicilio;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }



    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }


}
