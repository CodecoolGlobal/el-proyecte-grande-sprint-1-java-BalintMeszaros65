package com.codecool.procrastination.exceptions;

import java.util.NoSuchElementException;

public class CustomExceptions {
    public static class EmailAlreadyUsedException extends IllegalArgumentException {
        public EmailAlreadyUsedException(String message) {
            super(message);
        }
    }

    public static class MissingAttributeException extends NullPointerException {
        public MissingAttributeException(String message) {
            super(message);
        }
    }

    public static class WrongEmailOrPasswordException extends NoSuchElementException {
        public WrongEmailOrPasswordException(String message) {
            super(message);
        }
    }
}
