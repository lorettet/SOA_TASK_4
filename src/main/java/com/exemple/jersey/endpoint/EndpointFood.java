package com.exemple.jersey.endpoint;

import com.exemple.jersey.exception.InvalidFoodException;
import com.exemple.jersey.model.Food;
import com.exemple.jersey.model.FoodCategory;
import com.exemple.jersey.security.NeedAuthentification;
import com.exemple.jersey.service.ServiceFood;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/foods")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class EndpointFood {
    private ServiceFood serviceFood = new ServiceFood();

    @GET
    @NeedAuthentification
    public Collection<Food> getAllFood(@QueryParam("category") FoodCategory category) {
        if (category != null) {
            return serviceFood.getAllFood(category);
        }
        return serviceFood.getAllFood();
    }

    @GET
    @NeedAuthentification
    @Path("/category")
    public FoodCategory[] getAllCategory() {
        return FoodCategory.values();
    }

    @GET
    @NeedAuthentification
    @Path("/{id}")
    public Response getFood(@PathParam("id") long id) {
        Food food = serviceFood.getFood(id);
        if (food == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(food).build();
    }

    @GET
    @NeedAuthentification
    @RolesAllowed("REGISTERED")
    @Path("/{name}/calories")
    public Response getFoodCalorie(@PathParam("name") String name) {
        Food food = serviceFood.getFoodByName(name);
        if (food == null) {
            throw new InvalidFoodException(name);
        }
        return Response.ok().entity("Calories for " + name + " : " + food.getCalories()).build();
    }

    @POST
    @NeedAuthentification
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Food addFood(Food food) {
        return serviceFood.addFood(food);
    }

    @PUT
    @NeedAuthentification
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFood(Food food) {
        return Response.ok().entity(serviceFood.updateExercise(food)).build();
    }

    @DELETE
    @NeedAuthentification
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    public Response deleteFood(@PathParam("id") long id) {
        if (serviceFood.deleteFood(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
