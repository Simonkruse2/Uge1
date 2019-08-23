package rest.service;

import DTO.EmployeeDTO;
import com.google.gson.Gson;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    private static EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    private static Gson gson = new Gson();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String def(){
        return "employee page";
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<Employee> employees = facade.getAllEmployees();
        List<EmployeeDTO> employeedtos = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            employeedtos.add(new EmployeeDTO(employee));
        }
        return gson.toJson(employeedtos);
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById(@PathParam("id") long id) {
        Employee e = facade.getEmployeeById(id);
        return gson.toJson(new EmployeeDTO(e));
    }
    
    @GET
    @Path("highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaidEmployee(){
        return gson.toJson(facade.getEmployeeWithHighestSalary());
    }
    
    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeesByName(@PathParam("name") String name){
        List<Employee> employees = facade.getEmployeesByName(name);
        List<EmployeeDTO> employeedtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeedtos.add(new EmployeeDTO(employee));
        }
        return gson.toJson(employeedtos);
    }
}
