package com.exemple.jersey.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidUserSexExceptionMapper implements ExceptionMapper<InvalidUserSexException> {
    public Response toResponse(InvalidUserSexException e) {
        ErrorMessage errorMessage = new ErrorMessage(
                e.getMessage(),
                e.getResponse().getStatus(),
                "http://localhost:8080/SOA_TASK_3_war_exploded/rest/user/userSex"
        );
        return Response.status(e.getResponse().getStatus()).entity(errorMessage).build();
    }
}
