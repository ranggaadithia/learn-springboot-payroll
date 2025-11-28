package com.dimata.service.payroll.core.response;

import com.dimata.service.payroll.exception.CoreException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response
  ) {

    if (body instanceof ApiResponse) {
      return body;
    }

    // Kalau body berupa String → harus ditangani khusus
    if (body instanceof String) {
      return "{\"success\":true,\"message\":\"Success\",\"data\":\"" + body + "\"}";
    }

    return ApiResponse.success(body);
  }

}
