package com.example.formmaker.exception;

public final class FormResultException extends RuntimeException {

    public static RuntimeException NOT_FOUND(Long id) {
        return new RuntimeException(String.format("Form result with id %d not found", id));
    }

    public static RuntimeException DATA_IS_NULL() {
        return new RuntimeException("user или answer не должны быть null");
    }
}
