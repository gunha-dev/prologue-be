package com.prologue.test.config.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponseDto {
    private String message;
    private Date timeStamp;

    public ErrorResponseDto(String message) {
        this.message = message;
        this.timeStamp = new Date();
    }
}
