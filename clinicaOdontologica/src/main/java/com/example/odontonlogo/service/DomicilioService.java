package com.example.odontonlogo.service;

import com.example.odontonlogo.dto.DomicilioDTO;
import com.example.odontonlogo.dto.OdontologoDTO;
import com.example.odontonlogo.persistencia.model.Domicilio;
import com.example.odontonlogo.persistencia.model.Odontologo;
import com.example.odontonlogo.persistencia.repository.IDomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//interface service y pasa a tener las func
public class DomicilioService implements IDomicilioService {
    private static  final Logger logger = Logger.getLogger(DomicilioService.class);
    @Autowired
    IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    public void guardarDomicilio(DomicilioDTO domicilioDTO){
        //convertimos dto a json
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        domicilioRepository.save(domicilio);
    }

    @Override
    public void crear(DomicilioDTO domicilioDTO) {
        guardarDomicilio(domicilioDTO);
        logger.info("Se creo el  Domicilio")  ;

    }

    @Override
    public DomicilioDTO buscarID(Long id) {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        DomicilioDTO domicilioDTO = null;

        if(domicilio.isPresent()) {
            domicilioDTO = mapper.convertValue(domicilio ,DomicilioDTO.class);
            logger.info("Se Encontro al  Domicilio" );

        }
        return domicilioDTO;
    }

    @Override
    public Set<DomicilioDTO> listarTodos() {
       List<Domicilio> domicilios = domicilioRepository.findAll();

       //recorremos la lista para agregar a domicilios dto
        Set<DomicilioDTO> domiciliosDTO = new HashSet<>();

        for (Domicilio domicilio : domicilios){
            domiciliosDTO.add(mapper.convertValue(domicilio, DomicilioDTO.class));
            logger.info("Se Encontro al  Domicilio "+ domicilio.getCalle() + " "+ domicilio.getLocalidad()+ " fue encontrado");
        }
        return domiciliosDTO;
    }

    @Override
    public void modificar(DomicilioDTO domicilioDTO) {

        guardarDomicilio(domicilioDTO);
        logger.info("Se Modifico el  Domicilio")  ;
    }

    @Override
    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);

        logger.info("Se Borro al  Domicilio")  ;

    }
}
