package com.company;

import org.apache.log4j.Logger;

public class Tigre {
    private String nombre;
    private int edad;

    public Tigre(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    private static final org.apache.log4j.Logger logger = Logger.getLogger(Tigre.class);

    public void correr(){
        logger.info("El Tigre esta corriendo " );
    }

    public boolean edad()  {
        if (edad >= 10 ) {
            logger.info("El Tigre tiene " + edad );
            return true;
        }
        if (edad <= 0) {
            logger.error("La edad del Tigre es menor a 0");

        }
        return false;
    }
}
