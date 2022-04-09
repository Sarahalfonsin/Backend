package com.example.odontonlogo.service;



import com.example.odontonlogo.dto.PacienteDTO;
import com.example.odontonlogo.persistencia.model.Paciente;

import com.example.odontonlogo.persistencia.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {
    private static  final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;


    //VAMOS A REUTILIZAR ESTE CODIGO EN CREAR Y EN MODIFICAR
    public void guardarPaciente(PacienteDTO pacienteDTO) {
        //convertimos el dto en json
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        //vemos que metodo tiene en repository paa guardar
        pacienteRepository.save(paciente);

    }

    @Override
    public void crear(PacienteDTO pacienteDTO) {
        guardarPaciente(pacienteDTO);
        logger.info("Se creo al  paciente" + pacienteDTO.getNombre() + " "+ pacienteDTO.getApellido()   );


    }

    @Override
    public PacienteDTO buscarID(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO=null;
        
        //en el caso de que no sea nulo
        if(paciente.isPresent()){
            pacienteDTO = mapper.convertValue(paciente , PacienteDTO.class);
            logger.info("Se Encontro al  paciente" + pacienteDTO.getNombre() + " "+ pacienteDTO.getApellido()   );

        }
        return pacienteDTO;
        
    }

    @Override
    public Set<PacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        //recorremos la lista para agregar a pacientes dto
        Set<PacienteDTO> pacientesDTO = new HashSet<>();

        for (Paciente paciente : pacientes){
            pacientesDTO.add(mapper.convertValue(paciente,PacienteDTO.class));
            logger.info("El paciente "+ paciente.getNombre()+paciente.getApellido()+ " fue encontrado");
        }
        return pacientesDTO;

    }

    @Override
    public void modificar(PacienteDTO pacienteDTO) {
        guardarPaciente(pacienteDTO);

        logger.info("El paciente "+ pacienteDTO.getNombre()+pacienteDTO.getApellido()+ " fue modificad");

    }

    @Override
    public void eliminar(Long id) {

        pacienteRepository.deleteById(id);
        logger.info("El paciente fue eliminado");


    }

    @Override
    public PacienteDTO findOneByEmail(String email) {
        Optional<Paciente> paciente = pacienteRepository.findOnePatientByEmail(email);
        PacienteDTO pacienteDTO=null;

        if(paciente.isPresent()){
            pacienteDTO = mapper.convertValue(paciente ,PacienteDTO.class);
            logger.info("Se Encontro al  paciente" + pacienteDTO.getNombre() + " "+ pacienteDTO.getApellido() + " " + pacienteDTO.getEmail()   );

        }

        return pacienteDTO;
    }

}
