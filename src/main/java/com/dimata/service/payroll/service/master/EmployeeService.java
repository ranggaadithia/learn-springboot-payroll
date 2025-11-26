package com.dimata.service.payroll.service.master;

import com.dimata.service.payroll.dto.response.master.EmployeeResponse;
import com.dimata.service.payroll.repository.master.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public List<EmployeeResponse> getEmployees() {
    List<EmployeeResponse> employees = employeeRepository.getEmployees();

    if (employees.isEmpty()) {
      return List.of();
    }

    return employees;
  }
}
