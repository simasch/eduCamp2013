package business;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;
import model.Customer;
import model.Transaction;

@Stateless
@LocalBean
public class TransactionManager {

    @PersistenceContext(unitName = "bank")
    EntityManager em;

    public Transaction withdraw(Account account, BigDecimal amount) throws LimitExceedException {
        // Pessimistic lock does not work with Derby embedded
        //account = em.find(Account.class, id, LockModeType.PESSIMISTIC_WRITE);
        account = em.find(Account.class, account.getId());

        if (amount.compareTo(account.getBalance()) == 1) {
            throw new LimitExceedException();
        }
        account.setBalance(account.getBalance().subtract(amount));
        account = em.merge(account);

        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setCredit(account.getIban());

        em.persist(t);
        return t;
    }

    public Transaction deposit(Account account, BigDecimal amount) {
        // Pessimistic lock does not work with Derby embedded
        //account = em.find(Account.class, id, LockModeType.PESSIMISTIC_WRITE);
        account = em.find(Account.class, account.getId());

        account.setBalance(account.getBalance().add(amount));
        account = em.merge(account);

        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setDebit(account.getIban());

        em.persist(t);
        return t;
    }

    public Transaction transfer(String credit, String debit, BigDecimal amount) {
        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setCredit(credit);
        t.setDebit(debit);

        em.persist(t);
        return t;

    }

    public List<Transaction> getTransactions(String user) {
        TypedQuery t = em.createNamedQuery(Customer.getTransactionsByUser, Transaction.class);
        t.setParameter("user", user);
        return t.getResultList();
    }
}
