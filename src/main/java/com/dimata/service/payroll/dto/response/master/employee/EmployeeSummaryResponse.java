package com.dimata.service.payroll.dto.response.master.employee;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class EmployeeSummaryResponse {
  private UUID id;
  private String fullName;
  private String jobTitle;
  private String email;
}
