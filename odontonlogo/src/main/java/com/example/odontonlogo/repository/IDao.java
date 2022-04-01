package com.example.odontonlogo.repository;

import java.util.List;

public interface IDao<T> {
    //T SON GENERICO SON PARAMETROS
    //Guardar cualquier tipo de objeto
    public T guardar(T t);
    public void eliminar(Long id);
    public T buscarId(Long id);
    public T buscar(String email);
    public List<T> buscarTodos();
    public T modificar(T t );


}
