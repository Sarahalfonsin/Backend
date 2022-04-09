package com.example.odontonlogo.service;


import com.example.odontonlogo.dto.OdontologoDTO;
import com.example.odontonlogo.dto.PacienteDTO;
import com.example.odontonlogo.persistencia.model.Odontologo;
import com.example.odontonlogo.persistencia.repository.IOdontologoRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {

    private static  final Logger logger = Logger.getLogger(OdontologoService.class);

    //PARA ACCEDER A CAPA DATOS NECEsiTO REPOSITORY
    @Autowired
    IOdontologoRepository odontologoRepository;

    //como uso dtos necesitamos mapper
    @Autowired
    ObjectMapper mapper;


    //VAMOS A REUTILIZAR ESTE CODIGO EN CREAR Y EN MODIFICAR
    public void guardarOdontologo(OdontologoDTO odontologoDTO) {
        //convertimos el dto en json
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        //vemos que metodo tiene en repository paa guardar
        odontologoRepository.save(odontologo);

    }

    @Override
    public void crear(OdontologoDTO odontologoDTO) {

        guardarOdontologo(odontologoDTO);
        logger.info("Se GUARDO al  Odontologo" + odontologoDTO.getNombre() + " "+ odontologoDTO.getApellido()  + " de id " + odontologoDTO.getId() );

    }

    @Override
    public OdontologoDTO buscarID(Long id) {
        //le da la oportunidad d epreguntar si el odontologo no es nulo
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);

        OdontologoDTO odontologoDTO = null;

        //is presente es si es diferente de nulo , SI esta presente
        if (odontologo.isPresent()){
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);}

        logger.info("Se Encontro al  Odontologo" + odontologoDTO.getNombre() + " "+ odontologoDTO.getApellido()   );

        return odontologoDTO;
    }

    @Override
    public void modificar(OdontologoDTO odontologoDTO) {
        guardarOdontologo(odontologoDTO);

        logger.info("Se MODIFICO al  Odontologo" + odontologoDTO.getNombre() + " "+ odontologoDTO.getApellido()   );

    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);

        logger.info("Se Borro al  Odontologo")  ;


    }

    @Override
    public Set<OdontologoDTO> listarTodos() {
        List<Odontologo> odontologos =odontologoRepository.findAll();

        //recorremos la lista para agregar a estudaintes dto
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();

        for (Odontologo odontologo : odontologos){
            odontologosDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));
            logger.info("Se Encontro al  Odontologo "+ odontologo.getNombre()+odontologo.getApellido()+ " fue encontrado");
        }
        return odontologosDTO;



    }
}
