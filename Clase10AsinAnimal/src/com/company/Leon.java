package com.company;

import org.apache.log4j.Logger;

public class Leon {
    private String nombre;
    private int edad;
    private boolean esAlfa;

    public Leon(String nombre, int edad, boolean esAlfa) {
        this.nombre = nombre;
        this.edad = edad;
        this.esAlfa = esAlfa;
    }

    private static final org.apache.log4j.Logger logger = Logger.getLogger(Leon.class);

    public void correr(){
        logger.info("El Leon esta corriendo " );
    }

    public boolean edadAlfa() throws Exception {
        if (edad > 10 && esAlfa ) {
            logger.info("El Leon tiene " + edad + " y es Alfa");
            return true;
        }
        if (edad <= 0) {
            logger.error("La edad del Leon es menor a 0");
        }
        return false;
    }
}
