package com.globallogic.evaluacion.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globallogic.evaluacion.controller.EvaluacionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseException> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("Error al insertar usuario: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseException(409, ex.getMessage()));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ResponseException> handleInvalidDataException(InvalidDataException ex) {
        log.error("Datos Invalidos: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseException(400, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handleGeneralException(Exception ex) {
        log.error("Error general al intentar crear un usuario: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseException(500, "Error general :"));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ResponseException> handleJsonProcessingException(JsonProcessingException ex) {
        log.error("Error interno del servidor al procesar JSON", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseException(500, "Error interno del servidor al procesar JSON"));
    }
}
