package com.exemple.jersey.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFoodExceptionMapper implements ExceptionMapper<InvalidFoodException> {
    public Response toResponse(InvalidFoodException e) {
        ErrorMessage errorMessage = new ErrorMessage( e.getMessage(), e.getResponse().getStatus(),
                "http://localhost:8080/SOA_TASK_3_war_exploded/rest/food"
        );
        return Response.status(e.getResponse().getStatus()).entity(errorMessage).build();
    }
}