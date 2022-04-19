package com.example.odontonlogo.service;

import com.example.odontonlogo.dto.OdontologoDTO;
import com.example.odontonlogo.exception.BadRequestException;
import com.example.odontonlogo.exception.ResourceNotFoundException;

import java.util.Set;

public interface ICrudService<T> {
    public void crear(T t) throws BadRequestException;

    public T buscarID(Long id) throws BadRequestException, ResourceNotFoundException;

    Set<T> listarTodos();

    public void modificar(T t) throws BadRequestException;

    public void eliminar(Long id) throws BadRequestException, ResourceNotFoundException;
}
