package com.sydani.assessment.exception;

import lombok.Data;

@Data
public class ApiException {
    private int status_code = 500;

    private String status = "false";

    private String message;

    public ApiException(String message) {
        this.message = message;
    }
}
