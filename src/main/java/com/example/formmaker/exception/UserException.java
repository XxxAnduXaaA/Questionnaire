package com.example.formmaker.exception;

public final class UserException extends RuntimeException {

    public static RuntimeException EMAIL_ALREADY_TAKEN(String email){
        return new RuntimeException(String.format("Email %s already in use", email));
    }

    public static RuntimeException EMAIL_NOT_FOUND(String email){
        return new RuntimeException(String.format("User with email %s not found", email));
    }

    public static RuntimeException USER_NOT_FOUND(Long id){
        return new RuntimeException(String.format("User with email %d not found", id));
    }
}
