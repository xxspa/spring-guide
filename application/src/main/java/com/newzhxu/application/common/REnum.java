package com.newzhxu.application.common;

import lombok.Getter;

@Getter
public enum REnum {
    SUCCESS(true, 200, "Success"),
    NO_CONTENT(true, 204, "Success"),
    ERROR(false, 500, "Error"),
    ;
    private final boolean success;
    private final int code;
    private final String message;

    REnum(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


}
