package com.exemple.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Exercise sayHello()
    {
        return new Exercise(1,"123");
    }
}
