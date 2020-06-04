package org.polytech.course.exception;

public class JournalNotFoundException extends RuntimeException {
    public JournalNotFoundException(String msg) {
        super(msg);
    }
}