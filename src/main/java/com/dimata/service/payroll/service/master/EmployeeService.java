package com.dimata.service.payroll.service.master;

import com.dimata.service.payroll.dto.request.master.employee.EmployeeCreateRequest;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeDetailResponse;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeSummaryResponse;
import com.dimata.service.payroll.exception.employee.EmployeeNotFoundException;
import com.dimata.service.payroll.repository.master.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

   /*
    * Get list of employees
    * @return List<EmployeeSummaryResponse>
   */
  public List<EmployeeSummaryResponse> getEmployees() {
    return employeeRepository.getEmployees();
  }

  /*
    * Get employee by id
    * @param UUID id
    * @return EmployeeDetailResponse
    * @throws EmployeeNotFoundException if employee not found
  */
  public EmployeeDetailResponse getEmployee(UUID id) {
    EmployeeDetailResponse employee = employeeRepository.getEmployee(id);

    if(employee == null) {
      throw new EmployeeNotFoundException(id);
    }

    return employee;
  }

  public EmployeeDetailResponse createEmployee(EmployeeCreateRequest request) {
    return employeeRepository.createEmployee(request);
  }
}
