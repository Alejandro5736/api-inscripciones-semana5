package com.plataforma.educativa.api_inscripciones.service;

import com.plataforma.educativa.api_inscripciones.model.Inscripcion;
import com.plataforma.educativa.api_inscripciones.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listarTodas() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {
        if (inscripcion.getFechaInscripcion() == null) {
            inscripcion.setFechaInscripcion(LocalDateTime.now());
        }
        // Aquí conectaremos más adelante el SDK de S3 para subir archivos físicos
        return inscripcionRepository.save(inscripcion);
    }
}