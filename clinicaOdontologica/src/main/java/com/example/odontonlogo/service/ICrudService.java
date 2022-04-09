package com.example.odontonlogo.service;

import com.example.odontonlogo.dto.OdontologoDTO;

import java.util.Set;

public interface ICrudService<T> {
    public void crear(T t);

    public T buscarID(Long id);

    Set<T> listarTodos();

    public void modificar(T t);

    public void eliminar(Long id);
}
