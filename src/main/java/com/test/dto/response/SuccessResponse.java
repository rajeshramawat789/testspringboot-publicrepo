package com.test.dto.response;

import lombok.Data;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Data
public class SuccessResponse {

    private String message;

    public SuccessResponse(String message) {
        this.message = message;
    }
}
