package facades;

import entities.Employee;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("select e from Employee e where e.name = :name ", Employee.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery query = em.createQuery("select e from Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeWithHighestSalary() {
        List<Employee> employees = getAllEmployees();
        Collections.sort(employees);
        Employee e = employees.get(0);
        return e;
    }

    public Employee createEmployee(String name, String address, int salary) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee e = new Employee(name, address, salary);
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade EF = new EmployeeFacade();
        EF.createEmployee("Simon", "123", 50000);
        EF.createEmployee("Karl", "3456", 40000);
        EF.createEmployee("John", "12asdsadsad3", 30000);
        EF.createEmployee("Arne", "asdsad123", 20000);
        EF.createEmployee("Franz", "12gsdfdsf3", 10500);
    }

}
