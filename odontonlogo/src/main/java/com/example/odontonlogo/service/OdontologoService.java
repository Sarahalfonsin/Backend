package com.example.odontonlogo.service;

import com.example.odontonlogo.repository.IDao;
import com.example.odontonlogo.dominio.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIdao;

    //habilita la interface
    public OdontologoService(IDao<Odontologo> odontologoIdao){
        this.odontologoIdao = odontologoIdao;
    }
    public Odontologo guardarOdontologo (Odontologo e){
        return odontologoIdao.guardar(e);
    }
    public void eliminarOdontolgo(Long id){
        odontologoIdao.eliminar(id);
    }
    public Odontologo buscarOdontologoEmail(String email){
        return odontologoIdao.buscar(email);
    }
    public Odontologo buscarOdontologo(Long id){
        return odontologoIdao.buscarId(id);
    }
    public List<Odontologo> buscarTodos(){
        return  odontologoIdao.buscarTodos();
    }
    public Odontologo modificarOdontologo(Odontologo e){
        return odontologoIdao.modificar(e);
    }

}
