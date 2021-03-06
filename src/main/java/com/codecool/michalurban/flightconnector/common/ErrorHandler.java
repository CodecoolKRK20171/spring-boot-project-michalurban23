package com.codecool.michalurban.flightconnector.common;

import com.codecool.michalurban.flightconnector.exception.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatus status,
                                                                      WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEntityUnavailable(Exception ex) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({javax.validation.ConstraintViolationException.class,
            org.springframework.dao.DataIntegrityViolationException.class,
            org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(Exception ex) {

        String errorMessage = "Violation of unique constraint on one or more fields. Either object with specified " +
                "parameter already exists or not all required fields were specified.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errorMessage);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(NullPointerException ex) {

        String errorMessage = "Not all required fields specified";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errorMessage);

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}