package com.dimata.service.payroll.exception;

import org.springframework.http.HttpStatus;

public class MissingParameterException extends CoreException {
  public MissingParameterException(String parameterName) {
    super(
            HttpStatus.BAD_REQUEST,
            "Missing parameter: " + parameterName
    );
  }
}
