package com.exemple.jersey.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class InvalidFoodException extends ClientErrorException {
    private final static String FORMAT_STRING = "Invalid food : %s";

    public InvalidFoodException(String message) {
        super(String.format(FORMAT_STRING, message), Response.Status.BAD_REQUEST);
    }
}