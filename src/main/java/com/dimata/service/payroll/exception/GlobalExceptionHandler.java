package com.dimata.service.payroll.exception;

import com.dimata.service.payroll.core.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
  /**
   * 1) Semua CoreException (bisnis) termasuk MissingParameterException,
   *    DuplicateEmailException, EmployeeNotFoundException, dll.
   */
  @ExceptionHandler(CoreException.class)
  public ResponseEntity<ApiResponse> handleCoreException(CoreException ex){
    return ResponseEntity
            .status(ex.getStatus())
            .body(ApiResponse.error(ex.getMessage()));
  }

  /**
   * 2) Query param / form param yang wajib tapi tidak dikirim
   *    (contoh: @RequestParam("page") int page)
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
    MissingParameterException coreEx = new MissingParameterException(ex.getParameterName());
    ApiResponse body = new ApiResponse(false, coreEx.getMessage(), null);
    return ResponseEntity.status(coreEx.getStatus()).body(body);
  }

  /**
   * 3) Validasi @Valid pada @RequestBody (EmployeeCreateRequest, dll)
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(err -> {
      errors.put(err.getField(), err.getDefaultMessage());
    });

    ApiResponse body = new ApiResponse(false, "Validation failed", errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  /**
   * 4) Fallback untuk error lain yang tidak tertangkap di atas
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleGenericException(Exception ex){
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error("An unexpected error occurred."));
  }
}
