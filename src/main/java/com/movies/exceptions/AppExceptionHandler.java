package com.movies.exceptions;

import com.movies.constant.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @Autowired
    ApplicationConstant applicationConstant;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleException(IllegalArgumentException e) {
        return new ResponseEntity<Object>(applicationConstant.ILLEGAL_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieAppException.class)
    public ResponseEntity<Object> movieNotFound(MovieAppException e) {
        return new ResponseEntity<Object>(applicationConstant.MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieAlreadyExist.class)
    public ResponseEntity<Object> movieAlreadyExists(MovieAlreadyExist e) {
        return new ResponseEntity<Object>(applicationConstant.MOVIE_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
    }
}
