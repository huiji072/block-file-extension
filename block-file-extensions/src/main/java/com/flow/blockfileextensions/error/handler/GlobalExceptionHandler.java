package com.flow.blockfileextensions.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(ex.getErrorCode().getMessage());
    }
}
