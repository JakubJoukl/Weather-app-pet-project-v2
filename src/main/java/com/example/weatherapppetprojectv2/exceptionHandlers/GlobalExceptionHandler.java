package com.example.weatherapppetprojectv2.exceptionHandlers;

import com.example.weatherapppetprojectv2.dto.ErrorResponseDto;
import com.example.weatherapppetprojectv2.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringTokenizer;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //TODO zvazit logovani aspektem - abych logoval i defaultni exception
    public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UsernameNotFoundException ex, HttpServletRequest request) {
        log.debug("Requestor: {}, username: {}", getClientIpAddress(request), ex.getUsername());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(SameUsernameOrEmailUserExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(SameUsernameOrEmailUserExistsException ex, HttpServletRequest request) {
        log.debug("Requestor: {}, username: {}, email: {}", getClientIpAddress(request), ex.getUsername(), ex.getEmail());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthorityNotFound(AuthorityNotFoundException ex, HttpServletRequest request) {
        log.debug("Requestor: {}, authority: {}", getClientIpAddress(request), ex.getAuthority());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyObservesLocationException.class)
    public ResponseEntity<ErrorResponseDto>  handleUserAlreadyObservesLocation(UserAlreadyObservesLocationException ex, HttpServletRequest request) {
        log.debug("Requestor: {}, username: {}, location: {}", getClientIpAddress(request), ex.getUsername(), ex.getLocation());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(UserDoesNotObserveLocationException.class)
    public ResponseEntity<ErrorResponseDto> handleUserDoesNotObserveLocation(UserDoesNotObserveLocationException ex, HttpServletRequest request) {
        log.debug("Requestor: {}, username: {}, location: {}", getClientIpAddress(request), ex.getUsername(), ex.getLocation());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>  handleLocationNotFound(LocationNotFoundException ex, HttpServletRequest request) {
        log.debug("Requestor: {}, location: {}", getClientIpAddress(request), ex.getLocationName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(ex.getMessage()));
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            // As of https://en.wikipedia.org/wiki/X-Forwarded-For
            // The general format of the field is: X-Forwarded-For: client, proxy1, proxy2 ...
            // we only want the client
            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
        }
    }
}