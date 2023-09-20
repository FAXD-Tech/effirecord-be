package com.faxd.effirecord.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super("The company with id: " + id + " does not exist!");
    }
}
