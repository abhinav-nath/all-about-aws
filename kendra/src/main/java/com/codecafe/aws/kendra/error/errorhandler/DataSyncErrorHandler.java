package com.codecafe.aws.kendra.error.errorhandler;

import com.codecafe.aws.kendra.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import software.amazon.awssdk.services.kendra.model.ConflictException;

@ControllerAdvice
public class DataSyncErrorHandler {

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ErrorResponse> handleConflictException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

}