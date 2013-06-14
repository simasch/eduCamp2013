package business;

import java.security.Identity;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
import javax.ejb.SessionContext;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import javax.xml.rpc.handler.MessageContext;
import model.Account;
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
        accountManager.sc = SessionContextProvider.getSessionContext();
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
        Customer customer = accountManager.createCustomer("Peter Muster", "Bahnhofstrasse 1, 3000 Bern", "1234", "peter");
        Assert.assertNotNull(customer.getId());
    }

    @Test
    public void testCreateAccount() throws Exception {
        Customer customer = accountManager.createCustomer("Petra Müller", "Bahnhofstrasse 1, 3000 Bern", "5678", "petra");
        Assert.assertNotNull(customer.getId());

        Account account = accountManager.createAccount(customer, "Privatkonto");
        Assert.assertNotNull(account.getId());
    }

    @Test
    public void testGetAccounts() throws Exception {
        Customer customer = accountManager.createCustomer("Petra Müller", "Bahnhofstrasse 1, 3000 Bern", "5678", "petra");
        Assert.assertNotNull(customer.getId());

        Account account = accountManager.createAccount(customer, "Privatkonto");
        Assert.assertNotNull(account.getId());

        List<Account> accounts = accountManager.getAccounts();
        Assert.assertTrue(accounts.size() > 0);
    }
}