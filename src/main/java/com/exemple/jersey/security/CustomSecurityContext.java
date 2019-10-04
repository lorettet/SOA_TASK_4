package com.exemple.jersey.security;

import com.exemple.jersey.model.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class CustomSecurityContext implements SecurityContext {
    private User user;
    private String scheme;

    public CustomSecurityContext(User user, String scheme) {
        this.user = user;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return this.user.getRole().toString().equals(s);
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
