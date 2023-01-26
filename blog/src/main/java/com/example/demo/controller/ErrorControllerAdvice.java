package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.entity.ErorrInfo;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ErrorControllerAdvice {


  @ExceptionHandler(Exception.class)
  public ErorrInfo processException(Exception ex) {
    log.error("Unexpected error", ex);
    return new ErorrInfo(ex.getMessage());
  }
}
