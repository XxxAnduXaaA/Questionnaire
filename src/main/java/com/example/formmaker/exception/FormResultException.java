package com.example.formmaker.exception;

public final class FormResultException extends RuntimeException {

    public static RuntimeException notFound(Long id){
        return new RuntimeException(String.format("Form result with id %d not found", id));
    }
}
