package com.luxury_hotel.auth_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<?> handleUserExists(UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage()));
        }

        @ExceptionHandler(TokenRefreshException.class)
        public ResponseEntity<?> handleRefresh(TokenRefreshException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleAll(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal server error"));
        }

        static class ErrorResponse {
            public String message;
            public ErrorResponse(String message) { this.message = message; }
        }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> handleInvalidOtpException(InvalidOtpException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<String> handleOtpExpiredException(OtpExpiredException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
