package com.faxd.effirecord.exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(String name) {
        super("Sorry, Does not support the bank: " + name + " !");
    }
}
