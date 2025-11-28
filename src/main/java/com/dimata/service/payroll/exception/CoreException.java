package com.dimata.service.payroll.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CoreException extends RuntimeException {
  private final HttpStatus status;

  protected CoreException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

}
