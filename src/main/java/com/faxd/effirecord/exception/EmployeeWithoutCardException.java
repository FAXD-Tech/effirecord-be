package com.faxd.effirecord.exception;

public class EmployeeWithoutCardException extends RuntimeException {
    public EmployeeWithoutCardException(Long EmployeeId) {
        super("Sorry, there is no card found for employee id: " + EmployeeId + " !");
    }
}
