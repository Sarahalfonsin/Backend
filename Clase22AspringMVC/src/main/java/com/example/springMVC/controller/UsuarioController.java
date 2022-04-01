package com.example.springMVC.controller;

import com.example.springMVC.dominio.Usuario;
import com.example.springMVC.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();

@GetMapping("/")
    //primer endpoint
    public Usuario crearUsuario(){

    return usuarioService.crearUsuario();
    }
    @GetMapping("crear2")
    public  Usuario crearUsuario2 (){
    Usuario usuario = new Usuario("Joaquin",19);
    return usuario;
}

}
