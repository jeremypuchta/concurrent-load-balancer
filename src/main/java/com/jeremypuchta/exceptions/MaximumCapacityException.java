package com.jeremypuchta.exceptions;

public class MaximumCapacityException extends RuntimeException {
    public MaximumCapacityException(String errorMessage) {
        super(errorMessage);
    }
}
