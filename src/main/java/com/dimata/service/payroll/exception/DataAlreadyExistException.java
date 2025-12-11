package com.dimata.service.payroll.exception;

import org.springframework.http.HttpStatus;

public class DataAlreadyExistException extends CoreException {
  public DataAlreadyExistException(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
