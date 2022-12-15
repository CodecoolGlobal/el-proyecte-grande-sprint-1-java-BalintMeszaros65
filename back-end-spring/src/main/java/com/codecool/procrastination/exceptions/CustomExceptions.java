package com.codecool.procrastination.exceptions;

import java.util.NoSuchElementException;

public class CustomExceptions {
    public static class EmailAlreadyUsedException extends IllegalArgumentException {
        public EmailAlreadyUsedException(String message) {
            super(message);
        }
    }

    public static class MissingAttributeException extends IllegalArgumentException {
        public MissingAttributeException(String message) {
            super(message);
        }
    }

}
