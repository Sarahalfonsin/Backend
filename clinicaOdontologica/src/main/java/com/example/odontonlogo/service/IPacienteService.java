package com.example.odontonlogo.service;

import com.example.odontonlogo.dto.PacienteDTO;

import java.util.List;
import java.util.Set;

public interface IPacienteService extends ICrudService<PacienteDTO> {

    public PacienteDTO findOneByEmail(String email);



}
