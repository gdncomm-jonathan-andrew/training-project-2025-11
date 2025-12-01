package com.blublu.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

public class UsernameNotFoundHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, Object>> usernameNotFoundHandler(UsernameNotFoundException usernameNotFoundException) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Map.of("code",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "status",
            HttpStatus.INTERNAL_SERVER_ERROR.name(),
            "error",
            usernameNotFoundException.getMessage()));
  }
}
