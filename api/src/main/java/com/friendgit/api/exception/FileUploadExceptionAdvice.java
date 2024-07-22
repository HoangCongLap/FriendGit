package com.friendgit.api.exception;

import com.friendgit.api.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        Response response = Response.builder()
                .message("File to large!")
                .build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }
}