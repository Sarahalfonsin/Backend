package com.example.odontonlogo.service;

import com.example.odontonlogo.repository.IDao;
import com.example.odontonlogo.dominio.Paciente;

import java.util.List;


public class PacienteService {
    private IDao<Paciente> pacienteIDao;



    public  PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardar(Paciente e) {
        return pacienteIDao.guardar(e);
    }


    public void eliminar(Long id) {
        pacienteIDao.eliminar(id);
    }

    public Paciente buscarId(Long id) {
        return pacienteIDao.buscarId(id);
    }

    public Paciente buscar(String email) {
        return pacienteIDao.buscar(email);
    }

    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }


}
