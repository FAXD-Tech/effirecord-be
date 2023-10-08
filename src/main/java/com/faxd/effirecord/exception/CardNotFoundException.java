package com.faxd.effirecord.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(Long cardId) {
        super("Sorry, there is no card found with id: " + cardId + " !");
    }
}
