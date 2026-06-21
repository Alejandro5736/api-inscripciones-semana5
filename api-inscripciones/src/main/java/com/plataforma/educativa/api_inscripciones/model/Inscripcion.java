package com.plataforma.educativa.api_inscripciones.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEstudiante;
    private String rutEstudiante;
    private String curso;
    private LocalDateTime fechaInscripcion;
    
    // Este campo guardará el enlace del archivo PDF/Comprobante subido a Amazon S3
    private String comprobanteS3Url;

    // Constructor vacío requerido por JPA
    public Inscripcion() {}

    // Constructor completo
    public Inscripcion(Long id, String nombreEstudiante, String rutEstudiante, String curso, LocalDateTime fechaInscripcion, String comprobanteS3Url) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.rutEstudiante = rutEstudiante;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
        this.comprobanteS3Url = comprobanteS3Url;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }

    public String getRutEstudiante() { return rutEstudiante; }
    public void setRutEstudiante(String rutEstudiante) { this.rutEstudiante = rutEstudiante; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDateTime fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public String getComprobanteS3Url() { return comprobanteS3Url; }
    public void setComprobanteS3Url(String comprobanteS3Url) { this.comprobanteS3Url = comprobanteS3Url; }
}