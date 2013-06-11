package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Customer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class PersistenceTest {
    private static EntityManager em;
    
    @BeforeClass
    public static void beforeClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank-test");
        em = emf.createEntityManager();
    }
    
    @Test
    public void saveCustomer() {
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        
        Customer customer = new Customer();
        customer.setName("Peter Muster");
        
        em.persist(customer);

        trx.commit();
        
        assertNotNull(customer.getId());
    }

}