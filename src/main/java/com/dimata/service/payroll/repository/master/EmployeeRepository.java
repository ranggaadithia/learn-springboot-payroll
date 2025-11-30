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

  public List<EmployeeSummaryResponse> findAll() {
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

  public EmployeeDetailResponse getById(UUID id) {
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

  public Boolean existsByEmail(String email) {
    return jooq.fetchExists(
            jooq.selectOne()
              .from(EMPLOYEE)
              .where(EMPLOYEE.EMAIL.eq(email))
    );
  }

  public EmployeeDetailResponse save(EmployeeCreateRequest employee) {
    UUID id = UUID.randomUUID();
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

    return getById(id);
  }

}