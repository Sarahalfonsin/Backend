package com.example.odontonlogo.persistencia.repository;

import com.example.odontonlogo.persistencia.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno,Long> {
}
