package com.futuereh.dronefeeder.controller;

import com.futuereh.dronefeeder.exceptions.EntityNaoExistenteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.UnexpectedTypeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// source: https://www.baeldung.com/exception-handling-for-rest-with-spring

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNaoExistenteException.class)
  protected ResponseEntity<HashMap<String, String>> handleNotFound(EntityNaoExistenteException ex) {
    HashMap<String, String> response = new HashMap<String, String>();
    response.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<HashMap<String, String>> handleInvalidParam(
      IllegalArgumentException ex) {
    HashMap<String, String> response = new HashMap<String, String>();
    response.put("error", "Parâmetro inválido");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  // @ExceptionHandler(MethodArgumentNotValidException.class)
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    Map<String, List<String>> body = new HashMap<>();

    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

    body.put("errors", errors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

}
