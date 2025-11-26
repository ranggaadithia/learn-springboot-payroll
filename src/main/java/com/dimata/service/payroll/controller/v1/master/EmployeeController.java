package com.dimata.service.payroll.controller.v1.master;

import com.dimata.service.payroll.dto.response.master.EmployeeResponse;
import com.dimata.service.payroll.service.master.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/employees")
  public List<EmployeeResponse> getEmployees() {
    return employeeService.getEmployees();
  }

}
