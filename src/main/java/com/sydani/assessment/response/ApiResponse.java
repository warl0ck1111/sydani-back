package com.sydani.assessment.response;


import lombok.Data;

@Data
public class ApiResponse {

    private int status_code;

    private String status;
    private Object data;

    public ApiResponse(int status_code, String status, Object data) {
        this.status_code = status_code;
        this.status = status;
        this.data = data;
    }
}


