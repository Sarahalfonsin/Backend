package com.example.odontonlogo.persistencia.repository;

import com.example.odontonlogo.persistencia.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//DAO
public interface IDomicilioRepository extends JpaRepository<Domicilio,Long> {
}
