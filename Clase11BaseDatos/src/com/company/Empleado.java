package com.company;
import java.util.Date;

public class Empleado {
    private int id;
    private String nombre;
    private int edad;
    private String empresa;
    private String fecha;

    public Empleado(int id,String nombre, int edad, String empresa, String fecha) {
       this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.empresa = empresa;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
