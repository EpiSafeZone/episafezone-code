package com.example.episafezone.controllers;

import com.example.episafezone.exceptions.AlreadySharedWithException;
import com.example.episafezone.exceptions.AttributeMissingException;
import com.example.episafezone.exceptions.FormatUnsupportedException;
import com.example.episafezone.exceptions.ResourceNotFoudException;
import com.example.episafezone.models.ErrorDetails;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoudException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoudException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FormatUnsupportedException.class)
    public ResponseEntity<?> formatUnsupportedException(FormatUnsupportedException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AttributeMissingException.class)
    public ResponseEntity<?> attributeMissingException(AttributeMissingException e, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(
                 HttpStatus.BAD_REQUEST.value(),
                 e.getMessage(),
                 request.getDescription(false)
         );
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadySharedWithException.class)
    public ResponseEntity<?> alreadySharedWithEception(AlreadySharedWithException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
