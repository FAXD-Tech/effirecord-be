package com.faxd.effirecord.exception;

public class WorkHoursNotFoundException extends RuntimeException {
    public WorkHoursNotFoundException(Long workHoursId) {
        super("Does not find any work hours record with id: " +workHoursId);
    }
}
