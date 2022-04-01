package com.company;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;


public class Test {
    private final static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
        File log4jfile = new File("C:\\Users\\sarah\\OneDrive\\Documentos\\Sarah Digital\\B3\\BACKEND\\PROYECTOS\\Clase10AsinAnimal\\src\\com\\company\\log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());
Leon prueba = new Leon("Sarah",0,true);
Leon prueba2 = new Leon("maeije",12,true);
Tigre prueba3 = new Tigre("sss",5);

prueba.correr();
prueba2.correr();
prueba3.correr();
prueba.edadAlfa();
prueba2.edadAlfa();
prueba3.edad();

    }}
