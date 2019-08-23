/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author simon
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first web service";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomJson() {
        List<Animal> dogs = new ArrayList();
        Animal chihuahua = new Animal("Chihuahua", "Male", "Small");
        Animal sctBernards = new Animal("Sct Bernards", "Female", "Large");
        Animal greatDane = new Animal("Great Dane", "Male", "Large");
        Animal mix = new Animal("Mix", "Male", "Medium");
        dogs.add(chihuahua);
        dogs.add(sctBernards);
        dogs.add(greatDane);
        dogs.add(mix);
        Random rand = new Random();
        String result = new Gson().toJson(dogs.get(rand.nextInt(dogs.size()-1)));
        return result;
    }

}
