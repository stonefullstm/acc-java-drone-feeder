package com.futuereh.dronefeeder.controller;

import com.futuereh.dronefeeder.exceptions.EntityNaoExistenteException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// source: https://www.baeldung.com/exception-handling-for-rest-with-spring

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNaoExistenteException.class)
  protected ResponseEntity<HashMap<String, String>> handleNotFound(EntityNaoExistenteException ex) {
    HashMap<String, String> response = new HashMap<String, String>();
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}
