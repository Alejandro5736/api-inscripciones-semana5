package com.plataforma.educativa.api_inscripciones.controller;

import com.plataforma.educativa.api_inscripciones.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/s3")
@CrossOrigin(origins = "*")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<?> subirArchivoAS3(@RequestParam("file") MultipartFile file) {
        try {
            // Llama al servicio para procesar y cargar el archivo en el bucket
            String urlArchivo = s3Service.subirArchivo(file);

            // Estructuramos una respuesta JSON clara usando .put() correctamente
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("status", "success");
            respuesta.put("mensaje", "Archivo cargado exitosamente en Amazon S3");
            respuesta.put("url", urlArchivo);

            return ResponseEntity.ok(respuesta);

        } catch (IOException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("error", "Error interno al procesar el archivo físico");
            errorResponse.put("detalles", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("error", "Petición inválida");
            errorResponse.put("detalles", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}