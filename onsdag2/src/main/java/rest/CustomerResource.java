/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Customer;
import facade.CustomerFacade;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static Gson gson = new Gson();

    /**
     * Creates a new instance of CustomerResourve
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Customer Page";
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        List<Customer> customers = CustomerFacade.getCustomerFacade(emf).allCustomers();
        return gson.toJson(customers);
    }

    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCustomer() {
        List<Customer> customers = CustomerFacade.getCustomerFacade(emf).allCustomers();
        Random rand = new Random();
        int r = rand.nextInt(customers.size()-1);
        String result = gson.toJson(customers.get(r));
        return result;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerById(@PathParam("id") int id){
        Customer c = CustomerFacade.getCustomerFacade(emf).findByID(id);
        return gson.toJson(c);
    }
}
