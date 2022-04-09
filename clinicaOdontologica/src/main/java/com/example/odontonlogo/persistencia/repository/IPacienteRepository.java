package com.example.odontonlogo.persistencia.repository;

import com.example.odontonlogo.persistencia.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {

    @Query("select p from Paciente p where p.email like %:email%")
    //le da la oportunidad d epreguntar si el paciente no es nulo
    Optional<Paciente> findOnePatientByEmail(@Param("email") String email);
}
