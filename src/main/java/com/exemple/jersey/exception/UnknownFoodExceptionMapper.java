package com.exemple.jersey.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnknownFoodExceptionMapper implements ExceptionMapper<UnknownFoodException> {

    public Response toResponse(UnknownFoodException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getResponse().getStatus(), "see /rest/food");
        return Response.status(e.getResponse().getStatus()).entity(errorMessage).build();
    }
}
