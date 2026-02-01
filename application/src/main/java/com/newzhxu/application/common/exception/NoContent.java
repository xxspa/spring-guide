package com.newzhxu.application.common.exception;

public class NoContent extends RuntimeException {

    public NoContent() {
    }

    public NoContent(String message) {
        super(message);
    }

    public NoContent(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContent(Throwable cause) {
        super(cause);
    }

    public NoContent(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
