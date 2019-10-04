package com.exemple.jersey.security;

import com.exemple.jersey.exception.ErrorMessage;
import com.exemple.jersey.model.Role;
import com.exemple.jersey.model.User;
import com.exemple.jersey.service.ServiceUser;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    private static final ErrorMessage FORBIDDEN_ErrMESSAGE = new ErrorMessage("Access blockedfor all users !!!", 403, "http://myDocs.org");
    private static final ErrorMessage UNAUTHORIZED_ErrMESSAGE = new ErrorMessage("User cannotaccess the resource.", 401, "http://myDocs.org");

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if (authHeader != null && authHeader.size() > 0) {
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
            String decodedString = new String(Base64.getDecoder().decode(authToken));
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();
            ServiceUser serviceUser = new ServiceUser();
            User user = serviceUser.login(username, password);
            if (user != null) {
                String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
                requestContext.setSecurityContext(new CustomSecurityContext(user, scheme));

                Method resMethod = resourceInfo.getResourceMethod();
                if (resMethod.isAnnotationPresent(PermitAll.class)) {
                    return;
                }
                if (resMethod.isAnnotationPresent(DenyAll.class)) {
                    Response response = Response.status(Response.Status.FORBIDDEN).entity(FORBIDDEN_ErrMESSAGE).build();
                    requestContext.abortWith(response);
                }
                if (resMethod.isAnnotationPresent(RolesAllowed.class)) {
                   if (Arrays.asList(resMethod.getAnnotation(RolesAllowed.class).value()).contains(user.getRole().toString())) return;
                    Response response = Response.status(Response.Status.UNAUTHORIZED).entity(UNAUTHORIZED_ErrMESSAGE).build();
                    requestContext.abortWith(response);
                }
            }

            ErrorMessage errorMessage = new ErrorMessage("User cannot access the resource.", 401, "http://myDocs.org");
            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).build();
            requestContext.abortWith(unauthorizedStatus);

        }
    }
}