package com.exemple.jersey.endpoint;

import com.exemple.jersey.model.User;
import com.exemple.jersey.service.ServiceUser;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class EndpointUser {
    ServiceUser serviceUser = new ServiceUser();

    @PUT
    public User register(@QueryParam("login") String login, @QueryParam("password") String password, @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname){
        return serviceUser.addUser(login,password,firstname,lastname);
    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("login") String login, @QueryParam("password") String password, @Context HttpServletRequest req) {
        User user = serviceUser.login(login, password);
        if(user != null){
            req.getSession().setAttribute("user",user);
            return Response.ok().entity(user).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
