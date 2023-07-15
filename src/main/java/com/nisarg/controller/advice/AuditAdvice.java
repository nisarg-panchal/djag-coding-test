package com.nisarg.controller.advice;

import java.util.Date;
import org.springdoc.api.ErrorMessage;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class AuditAdvice {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return new ErrorMessage(
        ex.getMessage() + HttpStatus.NOT_FOUND.value() + new Date() + request.getDescription(false));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {

    return new ErrorMessage(
        ex.getMessage() + HttpStatus.NOT_FOUND.value() + new Date() + request.getDescription(false));
  }
}