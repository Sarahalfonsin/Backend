package com.example.Clase30AInyDepSpring.controller;

import com.example.Clase30AInyDepSpring.model.Book;
import com.example.Clase30AInyDepSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    //como hacemos que tenga accesso a nuestros datos

    //el AUTOWIRED inyecta la dependencia sin necesidad
    //de crear un nuevo objeto
    @Autowired
    BookRepository repository;

    @GetMapping("/Books")
    public List<Book> traerTodos(){
       return  repository.traerTodos();
    }


}

