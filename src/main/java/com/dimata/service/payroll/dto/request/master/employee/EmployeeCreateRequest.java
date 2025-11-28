package com.dimata.service.payroll.dto.request.master.employee;

import com.dimata.service.payroll.jooq.enums.EmployeeStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record EmployeeCreateRequest(

        @NotBlank
        String fullName,

        String nickName,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String jobTitle,

        @NotNull
        @Positive
        BigDecimal salary,

        @NotNull
        EmployeeStatus status
) {
}
