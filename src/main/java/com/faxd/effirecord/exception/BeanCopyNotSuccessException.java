package com.faxd.effirecord.exception;

public class BeanCopyNotSuccessException extends RuntimeException {
    public BeanCopyNotSuccessException(Exception ex) {
        super("Copying bean information to another bean does not success, message: " + ex.getMessage());
    }
}
