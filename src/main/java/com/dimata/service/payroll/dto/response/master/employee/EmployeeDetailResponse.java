package com.dimata.service.payroll.dto.response.master.employee;

import com.dimata.service.payroll.enums.master.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class EmployeeDetailResponse {
  private UUID id;
  private String fullName;
  private String nickName;
  private String email;
  private String jobTitle;
  private Integer salary;
  private EmployeeStatus status;
}
