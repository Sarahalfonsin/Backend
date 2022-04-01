package com.company.servicios;

import com.company.daos.IDao;
import com.company.daos.OdontologoDAOH2;
import com.company.entidades.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class OdontologoServiceTest {
    private  OdontologoService odontologoServiceTest =  new OdontologoService();
    private IDao<Odontologo> odontologoIDaotest = new OdontologoDAOH2();

    @BeforeEach
    public void cargarEstudiantes(){
        Odontologo odontologo1 = new Odontologo(3876,"Joaquin","Aldana");
        Odontologo odontologo2 = new Odontologo(1234,"Marjorie","Melo");
        Odontologo odontologo3 = new Odontologo(4321,"Andrade","Pereira");


        odontologoServiceTest.setOdontologoIDao(odontologoIDaotest);

        odontologoServiceTest.registrarOdontologo(odontologo1);
        odontologoServiceTest.registrarOdontologo(odontologo2);
        odontologoServiceTest.registrarOdontologo(odontologo3);


    }

    @Test
    void buscarTodos(){
        odontologoServiceTest.buscarTodos();
   }


}
