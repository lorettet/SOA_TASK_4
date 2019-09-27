package com.exemple.jersey.endpoint;

import com.exemple.jersey.model.Exercise;
import com.exemple.jersey.service.ServiceExercise;
import org.glassfish.jersey.jaxb.internal.XmlJaxbElementProvider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Path("/exercises")
@Produces(MediaType.APPLICATION_JSON)
public class EndpointExercises {

    private ServiceExercise serviceExercise = new ServiceExercise();

    @GET
    public Collection<Exercise> getAllExercises()
    {
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
}
