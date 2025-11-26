package com.dimata.service.payroll.repository.master;

import com.dimata.service.payroll.dto.response.master.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import static com.dimata.service.payroll.jooq.tables.Employee.EMPLOYEE;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

  private final DSLContext jooq;

  public List<EmployeeResponse> getEmployees() {
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
            .fetchInto(EmployeeResponse.class);
  }

}