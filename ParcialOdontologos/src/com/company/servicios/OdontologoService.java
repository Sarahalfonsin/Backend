package com.company.servicios;

import com.company.daos.IDao;
import com.company.entidades.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public void setOdontologoIDao(IDao<Odontologo>odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }
    public void eliminarOdontologo(Long id){
        odontologoIDao.eliminar(id);
    }
    public Odontologo registrarOdontologo(Odontologo e) {
        return odontologoIDao.guardar(e);
    }
    public Odontologo buscarOdontologo(Long id){
        return odontologoIDao.buscar(id);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoIDao.buscarTodos();
    }
}

