package com.example.Projet_IRT31.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // <--- Dit à Spring : "C'est ici qu'on gère les pépins"
public class GlobalExceptionHandler {

    // Cette méthode s'active dès qu'une RuntimeException (ex: ID introuvable) est lancée
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        // On prépare une jolie réponse JSON
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage()); // Le message "Offre introuvable..."
        body.put("status", HttpStatus.NOT_FOUND.value());

        // On renvoie une erreur 404 (Not Found) proprement
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Vous pourrez ajouter d'autres méthodes ici pour d'autres types d'erreurs
}