package com.devsu.user.util.exception.domain;

public class BadRequestException extends BaseException {

    public BadRequestException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public BadRequestException(String ex, Throwable cause, String errorCode) {
        super(ex, cause, errorCode);
    }
}
