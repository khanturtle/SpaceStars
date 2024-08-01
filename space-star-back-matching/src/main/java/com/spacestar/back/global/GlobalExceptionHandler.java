package com.spacestar.back.global;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<?> BaseError(GlobalException e, HttpServletRequest request) {
        log.error("errorStatus: {}, url: {}, message: {}", e.getStatus(), request.getRequestURI(), e.getMessage());

        return new ResponseEntity<>(e.getStatus());
    }

    //    runtime error
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> RuntimeError(RuntimeException e, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage());

        return new ResponseEntity<>(ResponseStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processVaildationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage());
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage());
            builder.append("\n");
        }
        String ErrorMessage = bindingResult.getFieldErrors().stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("Validation error");

        return new ResponseEntity<>(e, ErrorMessage);
    }

}
