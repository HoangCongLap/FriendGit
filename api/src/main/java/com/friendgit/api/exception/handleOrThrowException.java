package com.friendgit.api.exception;


public class handleOrThrowException extends RuntimeException {
    public handleOrThrowException(String message) {
        super(message);
    }

    public handleOrThrowException(String message, Throwable cause) {
        super(message, cause);
    }
}