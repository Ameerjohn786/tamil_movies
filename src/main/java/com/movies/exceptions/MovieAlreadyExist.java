package com.movies.exceptions;

public class MovieAlreadyExist extends Exception {
    public MovieAlreadyExist(String msg) {
        super(msg);
    }
}
