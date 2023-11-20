package com.faxd.effirecord.constant;

import lombok.Getter;

@Getter
public enum PermissionType {
    CREATE("create"),
    RETRIEVE("retrieve"),
    UPDATE("update"),
    DELETE("delete"),
    VERIFY("verify");

    private final String value;
    PermissionType(String value) {
        this.value = value;
    }
}
