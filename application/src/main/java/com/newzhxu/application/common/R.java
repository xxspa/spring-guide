package com.newzhxu.application.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Optional;

@Data
public class R<T> {
    @Schema(description = "Indicates whether the operation was successful")
    final boolean success;
    @Schema(description = "Response code representing the result of the operation")
    final int code;

    @Schema(description = "Detailed message about the operation result")
    final String message;
    @Schema(description = "The result data of the operation")
    final T result;

    public R(boolean success, int code, String message, T result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> R<T> of(REnum rEnum, String message, T result) {
        return new R<>(rEnum.isSuccess(), rEnum.getCode(),
                Optional.ofNullable(message).orElse(rEnum.getMessage()),
                result);
    }

    public static <T> R<T> of(REnum rEnum, T result) {
        return of(rEnum, rEnum.getMessage(), result);
    }

    public static R<Void> of(REnum rEnum) {
        return of(rEnum, rEnum.getMessage(), null);
    }

    public static <T> R<T> ok(T data) {
        return of(REnum.SUCCESS, data);
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> fail(String message) {
        return of(REnum.ERROR, message, null);
    }

    public static <T> R<T> fail(REnum status) {
        return of(status, null);
    }

}

