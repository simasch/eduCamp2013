package business;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Account;
import model.Customer;
import model.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

public class TransactionManagerTest {

    private static EntityManager em;
    private static TransactionManager transactionManager;
    private static AccountManager accountManager;

    @BeforeClass
    public static void beforeClass() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank-test");
        em = emf.createEntityManager();

        transactionManager = new TransactionManager();
        transactionManager.em = em;

        accountManager = new AccountManager();
        accountManager.em = em;
    }
    private Account account;

    @Before
    public void beginTransaction() {
        em.getTransaction().begin();
    }

    @After
    public void endTransaction() {
        em.getTransaction().commit();
    }

    @Test
    public void testWithDraw() throws Exception {
        createAccount();
        Transaction transaction = transactionManager.withdraw(account, BigDecimal.TEN);
        Assert.assertNotNull(transaction.getId());
    }

    @Test(expected = LimitExceedException.class)
    public void testWithDrawExceedLimit() throws Exception {
        createAccount();
        Transaction transaction = transactionManager.withdraw(account, new BigDecimal("20"));
        Assert.assertNotNull(transaction.getId());
    }

    @Test
    public void testDeposit() throws Exception {
        createAccount();
        Transaction transaction = transactionManager.deposit(account, BigDecimal.TEN);
        Assert.assertNotNull(transaction.getId());
    }

    private void createAccount() {
        Customer customer = accountManager.createCustomer("Petra Müller", "Bahnhofstrasse 1, 3000 Bern", "5678", "petra");
        Assert.assertNotNull(customer.getId());

        account = accountManager.createAccount(customer, "Privatkonto");
        Assert.assertNotNull(account.getId());
    }
}