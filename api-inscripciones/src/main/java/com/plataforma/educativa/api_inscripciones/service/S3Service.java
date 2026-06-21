package com.plataforma.educativa.api_inscripciones.service;

import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3Service {

    @Autowired
    private S3Template s3Template; // Inyecta el cliente automático de Spring Cloud AWS 3.x

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public String subirArchivo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("El archivo seleccionado está vacío.");
        }

        // 1. Generar un nombre único usando UUID para que los alumnos no sobrescriban archivos con el mismo nombre
        String nombreOriginal = file.getOriginalFilename();
        String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        String nombreUnico = UUID.randomUUID().toString() + extension;

        // 2. Subir el archivo al Bucket de S3 de forma directa usando flujos (Stream)
        try (InputStream inputStream = file.getInputStream()) {
            var recursoS3 = s3Template.upload(bucketName, nombreUnico, inputStream);
            
            // 3. Retornar la URL pública y definitiva del archivo en internet
            return recursoS3.getURL().toString();
        }
    }
}