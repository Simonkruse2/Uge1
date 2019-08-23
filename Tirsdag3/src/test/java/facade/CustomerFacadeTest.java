/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerFacadeTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    private static CustomerFacade instance;

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public CustomerFacadeTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testFindByLastName() {
        String lastName = "b";
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int expResult = 2;
        int result = cf.findByLastName(lastName).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testGetNumberOfCustomers() {
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int expResult = 6;
        int result = cf.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAllCustomers() {
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int expResult = 6;
        int result = cf.allCustomers().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @org.junit.jupiter.api.Test
    public void testAddCustomer() {
        String firstName = "John";
        String lastName = "Johnsen";
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        int numberOfCustomersBeforeCreation = cf.getNumberOfCustomers();
        cf.addCustomer(firstName, lastName);
        int numberOfCustomersAfterCreation = cf.getNumberOfCustomers();
        assertNotEquals(numberOfCustomersBeforeCreation, numberOfCustomersAfterCreation);
        cf.deleteCustomer(firstName, lastName);
    }

}
