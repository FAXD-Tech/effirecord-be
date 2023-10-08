package com.faxd.effirecord.exception;

public class CardHasExistedException extends RuntimeException {
    public CardHasExistedException(Long EmployeeId) {
        super("Sorry, the cards has existed for employee id: " + EmployeeId + " !");
    }
}
