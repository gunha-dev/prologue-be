package com.prologue.test.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadBoardNameException extends RuntimeException {
    public BadBoardNameException() {
        super("잘못된 게시판 이름 입니다");
    }

    public BadBoardNameException(String message) {
        super(message);
    }
}
