package com.exemple.jersey.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidExerciseCategoryExceptionMapper implements ExceptionMapper<InvalidExerciseCategoryException>{
    public Response toResponse(InvalidExerciseCategoryException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getResponse().getStatus(), "http://localhost:8080/SOA_TASK_3_war_exploded/rest/exercises/category");
        return Response.status(e.getResponse().getStatus()).entity(errorMessage).build();
    }
}
