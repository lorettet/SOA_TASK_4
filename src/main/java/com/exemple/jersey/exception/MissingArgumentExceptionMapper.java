package com.exemple.jersey.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingArgumentExceptionMapper implements ExceptionMapper<MissingArgumentException>{
    public Response toResponse(MissingArgumentException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getResponse().getStatus(), "");
        return Response.status(e.getResponse().getStatus()).entity(errorMessage).build();
    }
}
