package com.exemple.jersey.endpoint;

import com.exemple.jersey.exception.InvalidExerciseCategoryException;
import com.exemple.jersey.exception.MissingArgumentException;
import com.exemple.jersey.filter.Logged;
import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.model.ExerciseCategory;
import com.exemple.jersey.model.User;
import com.exemple.jersey.service.ServiceExercise;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/exercise")
@Produces(MediaType.APPLICATION_JSON)
public class EndpointExercise {

    private ServiceExercise serviceExercise = new ServiceExercise();

    @Context HttpServletRequest req;

    @GET
    public Collection<Exercise> getAllExercises(@QueryParam("category") ExerciseCategory category)
    {

        if(category != null)
        {
            return serviceExercise.getAllExercises(category);
        }
        return serviceExercise.getAllExercises();
    }

    @GET
    @Path("/{id}")
    public Response getExercise(@PathParam("id") long id){
        Exercise ex = serviceExercise.getExercise(id);
        if(ex == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(ex).build();
    }

    @PUT
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addExercise(Exercise ex) {
        User user = (User) req.getSession().getAttribute("user");
        ex.setAuthor(user);
        if(ex.getName() == null)
        {
            throw new MissingArgumentException("name");
        }
        if(ex.getCategory() == null)
        {
            throw new InvalidExerciseCategoryException("null");
        }
        return Response.ok().entity(serviceExercise.addExercise(ex)).build();
    }

    @POST
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateExercise(Exercise ex) {
        User user = (User) req.getSession().getAttribute("user");
        ex.setAuthor(user);
        if(ex.getId() == null)
        {
            throw new MissingArgumentException("id");
        }
        return Response.ok().entity(serviceExercise.updateExercise(ex)).build();
    }

    @DELETE
    @Logged
    @Path("/{id}")
    public Response deleteExercise(@PathParam("id") long id){
        if(serviceExercise.deleteExercise(id))
        {
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/category")
    public ExerciseCategory[] getAllCategory()
    {
        return ExerciseCategory.values();
    }
}
