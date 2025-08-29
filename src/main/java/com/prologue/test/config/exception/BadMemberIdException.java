package com.prologue.test.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadMemberIdException extends RuntimeException {

    public BadMemberIdException() {
        super("가입되지 않은 사용자 입니다.");
    }

    public BadMemberIdException(String message) {
        super(message);
    }
}
