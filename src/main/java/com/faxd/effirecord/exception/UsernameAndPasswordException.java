package com.faxd.effirecord.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameAndPasswordException extends AuthenticationException {
    public UsernameAndPasswordException(String msg) {
        super(msg);
    }
}
