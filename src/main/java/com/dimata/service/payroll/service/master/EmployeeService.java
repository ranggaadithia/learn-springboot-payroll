package com.dimata.service.payroll.service.master;

import com.dimata.service.payroll.dto.request.master.employee.EmployeeCreateRequest;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeDetailResponse;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeSummaryResponse;
import com.dimata.service.payroll.exception.DataAlreadyExistException;
import com.dimata.service.payroll.exception.DataNotFoundException;
import com.dimata.service.payroll.repository.master.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

   /**
    * Get list of employees
    * @return List<EmployeeSummaryResponse>
   */
  public List<EmployeeSummaryResponse> getEmployees() {
    return employeeRepository.findAll();
  }

  /**
    * Get employee by id
    * @param id UUID of the employee
    * @return EmployeeDetailResponse
    * @throws DataNotFoundException if employee not found
  */
  public EmployeeDetailResponse getEmployee(UUID id) {
    EmployeeDetailResponse employee = employeeRepository.getById(id);

    if(employee == null) {
      throw new DataNotFoundException("Employee with id " + id + " not found");
    }

    return employee;
  }

  /**
    * Create new employee
    * @param request {@link EmployeeCreateRequest} DTO for creating employee
    * @return EmployeeDetailResponse
    * @throws DataAlreadyExistException if email already exists
  */
  @Transactional
  public EmployeeDetailResponse createEmployee(EmployeeCreateRequest request) {

    if(employeeRepository.existsByEmail(request.email())) {
      throw new DataAlreadyExistException("Employee with email " + request.email() + " already exists");
    }

    return employeeRepository.save(request);
  }
}
