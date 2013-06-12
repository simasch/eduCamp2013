package business;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import model.Account;
import model.Transaction;

@Stateless
@LocalBean
public class TransactionManager {

    @PersistenceContext(unitName = "bank")
    EntityManager em;

    public Transaction withdraw(Account account, BigDecimal amount) throws LimitExceedException {
        account = em.find(Account.class, account.getId(), LockModeType.PESSIMISTIC_WRITE);
        
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
        account = em.find(Account.class, account.getId(), LockModeType.PESSIMISTIC_WRITE);

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
}
