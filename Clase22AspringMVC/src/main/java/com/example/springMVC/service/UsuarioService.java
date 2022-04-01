package com.example.springMVC.service;

import com.example.springMVC.dominio.Usuario;

public class UsuarioService {

    public Usuario crearUsuario(){
        //hardcodeamos usuario
        Usuario usuario = new Usuario("Saruhla",19);
                return usuario;
    }

}
