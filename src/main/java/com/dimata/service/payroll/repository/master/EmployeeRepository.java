package com.dimata.service.payroll.repository.master;

import com.dimata.service.payroll.dto.request.master.employee.EmployeeCreateRequest;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeDetailResponse;
import com.dimata.service.payroll.dto.response.master.employee.EmployeeSummaryResponse;
import com.dimata.service.payroll.exception.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import static com.dimata.service.payroll.jooq.tables.Employee.EMPLOYEE;


import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

  private final DSLContext jooq;

  public List<EmployeeSummaryResponse> getEmployees() {
    return jooq
            .select(
                    EMPLOYEE.ID,
                    EMPLOYEE.FULL_NAME,
                    EMPLOYEE.EMAIL,
                    EMPLOYEE.JOB_TITLE
            )
            .from(EMPLOYEE)
            .fetchInto(EmployeeSummaryResponse.class);
  }

  public EmployeeDetailResponse getEmployee(UUID id) {
    return jooq
            .select(
                    EMPLOYEE.ID,
                    EMPLOYEE.FULL_NAME,
                    EMPLOYEE.NICK_NAME,
                    EMPLOYEE.EMAIL,
                    EMPLOYEE.JOB_TITLE,
                    EMPLOYEE.SALARY,
                    EMPLOYEE.STATUS
            )
            .from(EMPLOYEE)
            .where(EMPLOYEE.ID.eq(id))
            .fetchOneInto(EmployeeDetailResponse.class);
  }

  public EmployeeDetailResponse createEmployee(EmployeeCreateRequest employee) {
    UUID id = UUID.randomUUID();

    try{
      jooq
        .insertInto(EMPLOYEE)
        .set(EMPLOYEE.ID, id)
        .set(EMPLOYEE.FULL_NAME, employee.fullName())
        .set(EMPLOYEE.NICK_NAME, employee.nickName())
        .set(EMPLOYEE.EMAIL, employee.email())
        .set(EMPLOYEE.JOB_TITLE, employee.jobTitle())
        .set(EMPLOYEE.SALARY, employee.salary())
        .set(EMPLOYEE.STATUS, employee.status())
        .execute();
    } catch (DataIntegrityViolationException ex) {
        throw new DuplicateEmailException(employee.email());
    }

    return getEmployee(id);
  }

}