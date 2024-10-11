package com.kenpxrk.security.exception;

import lombok.NoArgsConstructor;


public class PasswordsDontMatchException extends RuntimeException {

    public PasswordsDontMatchException(){
        super("Passwords don't match!");
    }

    public PasswordsDontMatchException(String message){
        super(message);
    }
}
