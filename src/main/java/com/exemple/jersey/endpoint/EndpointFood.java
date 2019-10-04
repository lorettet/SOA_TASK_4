package com.exemple.jersey.endpoint;

import com.exemple.jersey.exception.InvalidFoodException;
import com.exemple.jersey.model.Food;
import com.exemple.jersey.model.FoodCategory;
import com.exemple.jersey.service.ServiceFood;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/food")
@Produces(MediaType.APPLICATION_JSON)
public class EndpointFood {
    private ServiceFood serviceFood = new ServiceFood();

    @GET
    public Collection<Food> getAllFood(@QueryParam("category")FoodCategory category) {
        if(category != null) {
            return serviceFood.getAllFood(category);
        }
        return serviceFood.getAllFood();
    }

    @GET
    @Path("/category")
    public FoodCategory[] getAllCategory()
    {
        return FoodCategory.values();
    }

    @GET
    @Path("/{id}")
    public Response getFood(@PathParam("id") long id) {
        Food food = serviceFood.getFood(id);
        if(food == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(food).build();
    }

    @GET
    @Path("/{name}/calories")
    public Response getFoodCalorie(@PathParam("name") String name) {
        Food food = serviceFood.getFoodByName(name);
        if(food == null) {
            throw new InvalidFoodException(name);
        }
        return Response.ok().entity("Calories for "+name+" : "+food.getCalories()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Food addFood(Food food){
        return serviceFood.addFood(food);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFood (Food food) {
        return Response.ok().entity(serviceFood.updateExercise(food)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFood(@PathParam("id") long id){
        if(serviceFood.deleteFood(id))
        {
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
