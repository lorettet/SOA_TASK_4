package com.exemple.jersey.endpoint;

import com.exemple.jersey.exception.InvalidUserSexException;
import com.exemple.jersey.filter.UserFilterBean;
import com.exemple.jersey.model.Role;
import com.exemple.jersey.model.User;
import com.exemple.jersey.model.UserSex;
import com.exemple.jersey.service.ServiceUser;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collection;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class EndpointUser {

    private ServiceUser serviceUser = new ServiceUser();

    @RolesAllowed("VISITOR")
    @GET
    public Collection<User> getUsers(@QueryParam("age") long age, @BeanParam UserFilterBean filterBean) {
        if (age > 0) {
            return serviceUser.getAllUsersForAge(age);
        } else if (filterBean.getMinAge() > 0 &&
                (filterBean.getSex() == UserSex.MAN || filterBean.getSex() == UserSex.WOMAN) &&
                filterBean.getMinWeight() > 0) {
            return serviceUser.getUsersGroup(filterBean);
        } else {
            return serviceUser.getAllUsers();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") long id) {
        User user = serviceUser.getUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(user).build();
    }

    @GET
    @Path("/{userSex}/weight")
    public Response getWeightCompareToSex(@PathParam("userSex") String sex) {
        UserSex userSex = checkUserSex(sex);
        return Response.ok().entity(serviceUser.getAllUserWeightForASex(userSex)).build();
    }

    @GET
    @Path("/{userSex}/age")
    public Response getAgeCompareToSex(@PathParam("userSex") String sex) {
        UserSex userSex = checkUserSex(sex);
        return Response.ok().entity(serviceUser.getAllUserAgeForASex(userSex)).build();
    }

    private UserSex checkUserSex(String sex) {
        if (!sex.equals(UserSex.WOMAN.name()) && !sex.equals(UserSex.MAN.name())) {
            throw new InvalidUserSexException(sex);
        }
        UserSex userSex = null;
        if (sex.equals(UserSex.WOMAN.name())) userSex = UserSex.WOMAN;
        else userSex = UserSex.MAN;
        return userSex;
    }

    @POST
    @Path("/{id}")
    public Response updateUser(@PathParam("id") long id, User user) {
        user.setId(id);
        if (serviceUser.updateUser(user) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") long id) {
        serviceUser.deleteUser(id);
    }

    @PUT
    public User register(
            @QueryParam("login") String login,
            @QueryParam("password") String password,
            @QueryParam("firstname") String firstname,
            @QueryParam("lastname") String lastname,
            @QueryParam("age") long age,
            @QueryParam("weight") int weight,
            @QueryParam("sex") UserSex sex
    ) {
        return serviceUser.addUser(login, password, firstname, lastname, age, weight, sex, Role.VISITOR);
    }

    @GET
    @Path("/login")
    public Response login(
            @QueryParam("login") String login,
            @QueryParam("password") String password,
            @Context HttpServletRequest req
    ) {
        User user = serviceUser.login(login, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            return Response.ok().entity(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
