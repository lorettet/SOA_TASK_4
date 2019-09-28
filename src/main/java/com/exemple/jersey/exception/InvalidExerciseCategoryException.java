package com.exemple.jersey.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class InvalidExerciseCategoryException extends ClientErrorException {
    private final static String FORMAT_STRING = "Invalid exercise category : \"%s\"";

    public InvalidExerciseCategoryException(String message) {
        super(String.format(FORMAT_STRING,message), Response.Status.BAD_REQUEST);
    }
}
