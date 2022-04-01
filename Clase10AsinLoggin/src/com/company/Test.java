package com.company;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    private final static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        File log4jfile =  ListaPromedio listaPromedioMayorA5 = null;
        new File("C:\\Users\\sarah\\OneDrive\\Documentos\\Sarah Digital\\B3\\BACKEND\\PROYECTOS\\Clase10AsinLoggin\\src\\com\\company\\logj4.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
        ListaPromedio listaPromedioMayorA10 = null;
        ListaPromedio listaPromedioVacia = null;
        try {
            listaPromedioMayorA5 = new ListaPromedio(Arrays.asList(1, 2, 3, 4, 5, 6));
            listaPromedioMayorA10 = new ListaPromedio(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
            listaPromedioVacia = new ListaPromedio(new ArrayList<>());
        } catch (Exception e) {
            logger.error("La lista es igual a cero ", e);
        }
    }
}