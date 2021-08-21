package com.webshop.api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.webshop.api.dto.error.Error;
@Data
@NoArgsConstructor
public class ErrorResponse {
    private int status;

    private Error code;

    private String message;

    public ErrorResponse(int status, Error code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
