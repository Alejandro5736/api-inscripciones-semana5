package com.plataforma.educativa.api_inscripciones.controller;

import com.plataforma.educativa.api_inscripciones.model.Inscripcion;
import com.plataforma.educativa.api_inscripciones.service.InscripcionService;
import com.plataforma.educativa.api_inscripciones.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> obtenerInscripciones() {
        return ResponseEntity.ok(inscripcionService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<?> registrarInscripcion(
            @RequestParam("nombreEstudiante") String nombreEstudiante,
            @RequestParam("rutEstudiante") String rutEstudiante,
            @RequestParam("curso") String curso,
            @RequestParam("comprobante") MultipartFile comprobante) {
        
        try {
            // 1. Subir de forma inmediata el archivo físico al bucket de S3 y obtener su URL
            String urlComprobanteS3 = s3Service.subirArchivo(comprobante);

            // 2. Mapear los campos de la Semana 4 junto con la nueva URL de la nube
            Inscripcion nuevaInscripcion = new Inscripcion();
            nuevaInscripcion.setNombreEstudiante(nombreEstudiante);
            nuevaInscripcion.setRutEstudiante(rutEstudiante);
            nuevaInscripcion.setCurso(curso);
            nuevaInscripcion.setComprobanteS3Url(urlComprobanteS3);

            // 3. Persistir en la base de datos local
            Inscripcion guardada = inscripcionService.guardarInscripcion(nuevaInscripcion);
            return ResponseEntity.ok(guardada);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error crítico al almacenar en AWS S3: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}