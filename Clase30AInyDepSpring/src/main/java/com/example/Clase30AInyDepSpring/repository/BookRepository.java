package com.example.Clase30AInyDepSpring.repository;

import com.example.Clase30AInyDepSpring.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//simulamos BD
 @Repository
public class BookRepository {

    public List<Book> traerTodos() {

        List<Book> listaBooks = new ArrayList<>();
        listaBooks.add(new Book(1L,"¿Como Programar en Java?","Sarah Alfonsin"));
        listaBooks.add(new Book(2l,"Servidores : ¿Como administrarlos?","Marjorie Melo"));

                return listaBooks;
    }


}
