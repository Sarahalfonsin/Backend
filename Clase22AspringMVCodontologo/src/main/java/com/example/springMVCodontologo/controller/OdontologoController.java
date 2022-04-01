package com.example.springMVCodontologo.controller;

import com.example.springMVCodontologo.dominio.Odontologo;
import com.example.springMVCodontologo.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// decirle a Spring que este es nuestro controlle
@Controller
@RestController
//agregar nuestra URL, en este caso/odontólogo.
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    //conexión entre el modelo y el controller.
    @Autowired
    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }

    //transforma a json nuestra vista
    @GetMapping
    public List<Odontologo> getOdontologos(){
        return odontologoService.listaOdontolos();
    }
}
