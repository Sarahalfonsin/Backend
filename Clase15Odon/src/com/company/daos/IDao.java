package com.company.daos;

import java.util.List;

public interface IDao <T>{
    //T SON GEENRECISO SON PARAMETROS
    //Guardar cualquier tipo de objeto
    public T guardar(T t);
    public void eliminar(Long id);
    public T buscar(Long id);
    public List<T> buscarTodos();

}
