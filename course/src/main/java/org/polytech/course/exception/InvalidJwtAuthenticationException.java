package org.polytech.course.exception;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }
}
