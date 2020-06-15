package com.pawfriendz.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponseDTO {
    String timestamp;
    String errorMessage;
    HttpStatus status;

    public ErrorResponseDTO(HttpStatus badRequest, String localizedMessage, List<String> errors) {

    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
