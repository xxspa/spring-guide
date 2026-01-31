package com.newzhxu.bandwagon.result;

import lombok.Data;

@Data
public class BaseR {
    private Long error;
    private String message;
    private String additionalErrorCode;
    private String additionalErrorInfo;
}
