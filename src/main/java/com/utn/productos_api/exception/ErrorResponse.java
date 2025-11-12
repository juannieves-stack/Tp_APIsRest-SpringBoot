package com.utn.productos_api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
// Esta anotación de Jackson es útil para no incluir campos nulos (como validationErrors) en el JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;

    // Usaremos este campo para los detalles de los errores de validación (@Valid)
    private Map<String, String> validationErrors;

    // Constructor para errores generales (404, 500)
    public ErrorResponse(LocalDateTime timestamp, int status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    // Constructor para errores de validación (400)
    public ErrorResponse(LocalDateTime timestamp, int status, String message, String path, Map<String, String> validationErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
    }
}
