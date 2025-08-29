package com.prologue.test.config;

import com.prologue.test.config.exception.BadMemberEmailException;
import com.prologue.test.config.exception.BadPasswordException;
import com.prologue.test.config.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadMemberEmailException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(BadMemberEmailException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(BadPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleFailedLoginException(BadPasswordException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseDto);
    }
}
