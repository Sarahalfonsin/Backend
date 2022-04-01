package com.company.servicios;

import com.company.daos.IDao;
import com.company.entidades.Domicilio;
import com.company.entidades.Pacientes;

import java.util.List;

public class PacientesService {
    private IDao<Pacientes> pacientesIDao;
    private IDao<Domicilio> domicilioIDao ;


    public void setPacientesServiceIDao(IDao<Pacientes> pacientesIDao) {
        this.pacientesIDao = pacientesIDao;
    }
    public void setDomicilioService(IDao<Domicilio> domicilioIDao) {
        this.domicilioIDao = domicilioIDao;
    }
    public void eliminarDomicilio(Long id){
        domicilioIDao.eliminar(id);
    }
    public Pacientes guardarPaciente(Pacientes e){
        //delegamos responsibility de guardar al DAO
        return pacientesIDao.guardar(e);

    }
    public void eliminarPaciente(Long id){
        pacientesIDao.eliminar(id);
    }
    public Pacientes buscarPaciente(Long id){
        return pacientesIDao.buscar(id);
    }
    public Domicilio buscarDomicilio(Long id){
        return domicilioIDao.buscar(id);
    }
    public List<Pacientes> buscarTodos(){
        return pacientesIDao.buscarTodos();
    }
}
