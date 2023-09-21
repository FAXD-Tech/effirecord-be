package com.faxd.effirecord.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("The project with id: " + id + " does not exist!");
    }
}
