package com.exemple.jersey.filter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
@Logged
public class LogFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest req;

    public void filter(ContainerRequestContext reqContext) throws IOException {
        if (req.getSession(false) == null)
        {
            reqContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }
}