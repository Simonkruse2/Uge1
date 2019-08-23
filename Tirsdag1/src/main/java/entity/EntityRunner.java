package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author simon
 */
public class EntityRunner {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        EntityManager em = emf.createEntityManager();
        try {
            Book b1 = new Book("Learn JPA1");
            Book b2 = new Book("Learn JPA2");
            Book b3 = new Book("Learn JPA3");
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
