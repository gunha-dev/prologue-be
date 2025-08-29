package com.prologue.test.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadPasswordException extends RuntimeException {

    public BadPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }

    public BadPasswordException(String message) {
        super(message);
    }
}