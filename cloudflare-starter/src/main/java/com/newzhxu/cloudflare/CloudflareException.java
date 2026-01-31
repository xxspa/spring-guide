package com.newzhxu.cloudflare;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

public class CloudflareException extends RuntimeException {
    private final List<ErrorInfo> errors;
    private final String uri;

    public CloudflareException(List<ErrorInfo> errors, String uri) {
        this.errors = errors;
        this.uri = uri;
    }

    public CloudflareException(String message, List<ErrorInfo> errors, String uri) {
        super(message);
        this.errors = errors;
        this.uri = uri;
    }

    public CloudflareException(String message, Throwable cause, List<ErrorInfo> errors, String uri) {
        super(message, cause);
        this.errors = errors;
        this.uri = uri;
    }

    public CloudflareException(Throwable cause, List<ErrorInfo> errors, String uri) {
        super(cause);
        this.errors = errors;
        this.uri = uri;
    }

    public CloudflareException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorInfo> errors, String uri) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
        this.uri = uri;
    }


    @Data
    @Accessors(chain = true)
    public static class ErrorInfo {
        private int code;
        private String message;
    }

}
