package com.company;

import com.company.daos.OdontologoDAOH2;
import com.company.entidades.Odontologo;
import com.company.servicios.OdontologoService;

public class Main {
    public static void main(String[] args) throws Exception {
        Odontologo odontologo1 = new Odontologo(38173,"Sarah","Alfonsin");
        Odontologo odontologo2 = new Odontologo(22633,"Dalva","De Oliveira");
        Odontologo odontologo3 = new Odontologo(81363,"Karyne","Marselle");
        Odontologo odontologo4 = new Odontologo(81913,"Joaquin","Aldana");

        OdontologoService service = new OdontologoService();

        service.setOdontologoIDao(new OdontologoDAOH2());

        //service.registrarOdontologo(odontologo1);
        //service.registrarOdontologo(odontologo2);
        //service.registrarOdontologo(odontologo3);
        //service.registrarOdontologo(odontologo4);

        //service.eliminarOdontologo(9l);
        //service.buscarOdontologo(9l);
        //service.buscarTodos();


    }
}
