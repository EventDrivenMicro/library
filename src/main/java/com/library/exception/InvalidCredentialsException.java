package com.library.exception;


import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
