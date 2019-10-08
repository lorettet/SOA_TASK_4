package com.exemple.jersey.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exemple.jersey.Application;
import com.exemple.jersey.exception.ErrorMessage;
import com.exemple.jersey.model.User;
import com.exemple.jersey.service.ServiceUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Provider
@NeedJWTToken
public class JWTFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_JWT_HEADER_PREFIX = "Bearer ";

    private static final ErrorMessage FORBIDDEN_ErrMESSAGE = new ErrorMessage("Access blocked for all users !!!", 403, "http://myDocs.org");
    private static final ErrorMessage UNAUTHORIZED_ErrMESSAGE = new ErrorMessage("User cannot access,create or modify the resource.", 401, "http://myDocs.org");
    private static final ErrorMessage JWT_ERROR = new ErrorMessage("JWT Token has not been verified", 401, "");
    private static final ErrorMessage JWT_INVALID = new ErrorMessage("JWT Token is not JSON", 400, "");

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if (authHeader != null && authHeader.size() > 0) {
            String jwtToken = authHeader.get(0);

            jwtToken = jwtToken.replaceFirst(AUTHORIZATION_JWT_HEADER_PREFIX, "");
            try {
                Algorithm algorithm = Algorithm.HMAC256(Application.KEY_JWT);
                JWTVerifier verifier = JWT.require(algorithm).
                        .build(); //Reusable verifier instance
                verifier.verify(jwtToken);
            } catch (JWTVerificationException exception) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).entity(JWT_ERROR).build();
                requestContext.abortWith(response);
                return;
            }

            User user;
            ServiceUser serviceUser = new ServiceUser();
            try {
                DecodedJWT jwt = JWT.decode(jwtToken);
                JsonObject jsonToken = new JsonParser().parse(new String(Base64.getDecoder().decode(jwt.getPayload()))).getAsJsonObject();
                user = serviceUser.getUser(jsonToken.get("id").getAsLong());
            } catch (JWTDecodeException exception) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).entity(JWT_INVALID).build();
                requestContext.abortWith(response);
                return;
            }

            if (user != null) {
                String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
                requestContext.setSecurityContext(new CustomSecurityContext(user, scheme));

                Method resMethod = resourceInfo.getResourceMethod();
                if (resMethod.isAnnotationPresent(DenyAll.class)) {
                    Response response = Response.status(Response.Status.FORBIDDEN).entity(FORBIDDEN_ErrMESSAGE).build();
                    requestContext.abortWith(response);
                    return;
                } else if (resMethod.isAnnotationPresent(RolesAllowed.class)) {
                    if (Arrays.asList(resMethod.getAnnotation(RolesAllowed.class).value()).contains(user.getRole().toString()))
                        return;
                    Response response = Response.status(Response.Status.UNAUTHORIZED).entity(UNAUTHORIZED_ErrMESSAGE).build();
                    requestContext.abortWith(response);
                    return;
                } else if (resMethod.isAnnotationPresent(PermitAll.class)) {
                    return;
                } else return;
            }
        }
        ErrorMessage errorMessage = new ErrorMessage("User cannot access the resource.", 401, "http://myDocs.org");
        Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity(errorMessage).build();
        requestContext.abortWith(unauthorizedStatus);
    }
}