package com.example.formmaker.exception;

public final class FormException extends RuntimeException {

    public static RuntimeException FORM_NAME_ALREADY_TAKEN(String title) {
        return new RuntimeException(String.format("Форма с названием %s уже существует", title));
    }

    public static RuntimeException FORM_NOT_FOUND(Long id) {
        return new RuntimeException(String.format("Form not found with id %s", id));
    }

    public static RuntimeException USER_OR_FORM_IS_NULL() {
        return new RuntimeException("User or form is null");
    }
}
