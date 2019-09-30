package com.exemple.jersey.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class UnknownFoodException extends ClientErrorException {
    private final static String FORMAT_STRING = "This type of food (%s) does not exist in this context";
    public UnknownFoodException(String s) {
        super(String.format(FORMAT_STRING, s), Response.Status.BAD_REQUEST);
    }
}
