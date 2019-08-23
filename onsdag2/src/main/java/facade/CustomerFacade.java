package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery q1 = em.createQuery("Select c from Customer c where c.lastName = '" + lastName + "'", Customer.class);
            return q1.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
            Long l = (Long) q1.getSingleResult();
            return l.intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery q1 = em.createQuery("Select c from Customer c", Customer.class);
            return q1.getResultList();
        }finally{
            em.close();
        }
    }
    
    public Customer addCustomer(String firstName, String lastName){
        EntityManager em = emf.createEntityManager();
        try{
            Customer c = new Customer(firstName, lastName);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }finally{
            em.close();
        }
    }
    
    public int deleteCustomer(String firstName, String lastName){
        EntityManager em = emf.createEntityManager();
        try{
            Query q1 = em.createQuery("delete from Customer c where c.firstName = '" + firstName + "' and c.lastName = '" + lastName +"'");
            em.getTransaction().begin();
            int deletedCount = q1.executeUpdate();
            em.getTransaction().commit();
            return deletedCount;
        }finally{
            em.close();
        }
    }

    public static void main(String[] args) {
      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);

        // Har fjernet lokale main tests, for at undgå at de egentlige unittests fejlede.
    }
}
