package com.company;

import com.company.daos.DomicilioDAOH2;
import com.company.daos.PacienteDAOH2;
import com.company.entidades.Domicilio;
import com.company.entidades.Pacientes;
import com.company.servicios.PacientesService;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        Domicilio domicilioSarah= new Domicilio("gabino",2795,"devotou","caba");
        Domicilio domicilioDalva= new Domicilio("benjamim brasil",1600,"ceara","fortaleza");
        Domicilio domicilioabc= new Domicilio("bcd",321,"buenas","noches");

        Pacientes paciente1 = new Pacientes("Sarah","Alfonsin",93958974,new Date(),domicilioSarah);
        Pacientes paciente2 = new Pacientes("DALVA", "DE OLIVEIRA", 93958973, new Date(),domicilioDalva);
        Pacientes paciente3 = new Pacientes("abc", "cd2", 44554177, new Date(),domicilioabc);

        PacientesService servicio1 = new PacientesService();
        PacientesService servicioDom = new PacientesService();

        servicio1.setPacientesServiceIDao(new PacienteDAOH2());
        servicioDom.setDomicilioService(new DomicilioDAOH2());


        //servicio1.guardarPaciente(paciente1);
        //servicio1.guardarPaciente(paciente2);
        //servicio1.guardarPaciente(paciente3);

        //Elimino el paciente 3
        //servicio1.eliminarPaciente(3L);
        //Elimino domicilio 3
        //servicioDom.eliminarDomicilio(3l);

        //servicioDom.buscarDomicilio(2l);
        //servicio1.buscarPaciente(2l);
        servicio1.buscarTodos();

    }
}
