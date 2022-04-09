package com.example.odontonlogo.service;

import com.example.odontonlogo.repository.IDao;
import com.example.odontonlogo.dominio.Domicilio;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DomicilioService {

    private IDao<Domicilio> domicilioIDao;

    public  DomicilioService(IDao<Domicilio> domicilioIDao) {
        this.domicilioIDao = domicilioIDao;
    }

    public Domicilio guardarDomicilio(Domicilio d) {
        return domicilioIDao.guardar(d);
    }


    public void eliminarDomicilio(Long id) {
        domicilioIDao.eliminar(id);
    }


    public Domicilio buscarDomicilio(Long id) {
        return domicilioIDao.buscarId(id);

    }

    public List<Domicilio> buscarTodos(){
        return domicilioIDao.buscarTodos();
    }

}
