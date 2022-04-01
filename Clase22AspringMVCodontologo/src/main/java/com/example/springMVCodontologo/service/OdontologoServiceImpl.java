package com.example.springMVCodontologo.service;

import com.example.springMVCodontologo.dominio.Odontologo;
import org.springframework.stereotype.Service;



import java.util.Arrays;
import java.util.List;

//Esta anotacion nos dice que es un servicio
@Service
public class OdontologoServiceImpl implements OdontologoService{
    @Override
    public List<Odontologo> listaOdontolos() {
        return Arrays.asList(new Odontologo("Marjorie"), new Odontologo("Karyne"));
    }
}
