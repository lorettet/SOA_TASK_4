package com.exemple.jersey.exception;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class MissingArgumentException extends ClientErrorException {
    private final static String FORMAT_STRING = "Missing argument : \"%s\"";

    public MissingArgumentException(String message) {
        super(String.format(FORMAT_STRING, message), Response.Status.BAD_REQUEST);
    }
}
