package org.polytech.course.exception;

public class BookTypeNotFoundException extends RuntimeException{
    public BookTypeNotFoundException(String msg) {
        super(msg);
    }
}
