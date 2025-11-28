package com.dimata.service.payroll.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends CoreException {
  public DuplicateEmailException(String email) {
    super(HttpStatus.CONFLICT, "Employee with email: " + email + " already registered");
  }
}
