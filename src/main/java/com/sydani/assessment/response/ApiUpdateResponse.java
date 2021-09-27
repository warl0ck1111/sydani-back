package com.sydani.assessment.response;

import lombok.Data;

@Data
public class ApiUpdateResponse {

    private int status_code;

    private String status;
    private String message;
    private Object data;

    public ApiUpdateResponse(int status_code, String status, String message, Object data) {
        this.status_code = status_code;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
