package com.example.odontonlogo.service;

import com.example.odontonlogo.dto.OdontologoDTO;
import com.example.odontonlogo.dto.TurnoDTO;

import com.example.odontonlogo.persistencia.model.Odontologo;
import com.example.odontonlogo.persistencia.model.Turno;
import com.example.odontonlogo.persistencia.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    ITurnoRepository turnoRepository;

    @Autowired
    ObjectMapper mapper;

    //VAMOS A REUTILIZAR ESTE CODIGO EN CREAR Y EN MODIFICAR
    public void guardarTurno(TurnoDTO turnoDTO) {
        //convertimos el dto en json
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        //vemos que metodo tiene en repository paa guardar
        turnoRepository.save(turno);

    }


    @Override
    public void crear(TurnoDTO turnoDTO) {
        guardarTurno(turnoDTO);
    }

    @Override
    public TurnoDTO buscarID(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        TurnoDTO turnoDTO = null;

        if(turno.isPresent()){
            turnoDTO = mapper.convertValue(turno,TurnoDTO.class);
        }
        return turnoDTO;
    }

    @Override
    public Set<TurnoDTO> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        //recorremos la lista para agregar a pacientes dto
        Set<TurnoDTO> turnosDto = new HashSet<>();

        for( Turno turno : turnos){
            turnosDto.add(mapper.convertValue(turno,TurnoDTO.class));
        }
        return turnosDto;
    }

    @Override
    public void modificar(TurnoDTO turnoDTO) {
        guardarTurno(turnoDTO);
    }

    @Override
    public void eliminar(Long id) {

        turnoRepository.deleteById(id);
    }
}
