package com.example.Clase25API.Dao;

import java.util.List;

public interface IDao <T>{
    //Guardar cualquier tipo de objeto
    public T guardar(T t);
    public T modificar(T t );
    public void eliminar(int id);
    public T buscar(int id);
    public List<T> buscarTodos();
}
