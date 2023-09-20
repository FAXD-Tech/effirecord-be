package com.faxd.effirecord.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("The employee with id: " + id + " does not exist!");
    }
}
