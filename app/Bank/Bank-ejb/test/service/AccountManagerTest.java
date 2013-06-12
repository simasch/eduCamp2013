/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

public class AccountManagerTest {
    private static EntityManager em;
    private static AccountManager accountManager;

    @BeforeClass
    public static void beforeClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank-test");
        em = emf.createEntityManager();
        
        accountManager = new AccountManager();
        accountManager.em = em;
    }

    @Before
    public void beginTransaction() {
        em.getTransaction().begin();
    }
    
    @After
    public void endTransaction() {
        em.getTransaction().commit();
    }
    
    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = accountManager.createCustomer("Peter Muster");
        
        Assert.assertNotNull(customer.getId());
    }
    
}