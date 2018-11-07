package com.tricast.managers.exceptions;

public class SportsbookException extends Exception {

    private static final long serialVersionUID = -6642524130938338581L;

    public SportsbookException(String message, Throwable cause) {
        super(message, cause);
    }

    public SportsbookException(String message) {
        super(message);
    }
}
