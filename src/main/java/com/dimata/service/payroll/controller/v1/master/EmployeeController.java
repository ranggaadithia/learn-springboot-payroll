package com.dimata.service.payroll.controller.v1.master;

import com.dimata.service.payroll.dto.request.master.employee.EmployeeCreateRequest;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeDetailResponse;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeSummaryResponse;
import com.dimata.service.payroll.service.master.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/master")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/employees")
  public List<EmployeeSummaryResponse> getEmployees() {
    return employeeService.getEmployees();
  }

  @GetMapping("/employee/{id}")
  public EmployeeDetailResponse getEmployee(@PathVariable UUID id) {
    return employeeService.getEmployee(id);
  }

  @PostMapping("/employee")
  public EmployeeDetailResponse createEmployee(@Valid @RequestBody EmployeeCreateRequest request) {
    return employeeService.createEmployee(request);
  }

}
