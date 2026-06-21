package com.plataforma.educativa.api_inscripciones.repository;

import com.plataforma.educativa.api_inscripciones.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    // Hereda automáticamente todos los métodos CRUD (findAll, save, etc.)
}