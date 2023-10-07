package com.faxd.effirecord.exception;

public class PayrollNotFoundException extends RuntimeException {
    public PayrollNotFoundException(Long payrollId) {
        super("Does not find any payroll record with id: " +payrollId);
    }
}
