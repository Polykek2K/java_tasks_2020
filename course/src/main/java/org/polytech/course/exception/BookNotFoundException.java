package org.polytech.course.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String msg) {
        super(msg);
    }
}
