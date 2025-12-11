package com.dimata.service.payroll.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends CoreException {
  public DataNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
