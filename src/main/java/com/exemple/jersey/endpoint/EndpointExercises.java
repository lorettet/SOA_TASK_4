package com.exemple.jersey.endpoint;

import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.service.ServiceExercise;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/exercises")
public class EndpointExercises {

    private ServiceExercise serviceExercise = new ServiceExercise();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Exercise> getAllExercises()
    {
        return serviceExercise.getAllExercises();
    }
}
