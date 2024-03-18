package com.alitofigh.security.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.LazyInitializationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by A_Tofigh at 3/8/2024
 */

@ControllerAdvice
public class MyCustomExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public void handleExceptionTest2() {
        System.out.println("this method was called");
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleInvocationTargetException(RuntimeException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LazyInitializationException.class)
    public ResponseEntity<Object> handleLazyInitializationException(LazyInitializationException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), requestUri);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
