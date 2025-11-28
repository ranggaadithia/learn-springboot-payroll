package com.dimata.service.payroll.exception.employee;

import com.dimata.service.payroll.exception.CoreException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class EmployeeNotFoundException extends CoreException {
  public EmployeeNotFoundException(UUID id) {
    super(HttpStatus.NOT_FOUND, "Employee with id: " + id + " not found");
  }
}
