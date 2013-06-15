package business;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;
import model.Customer;
import model.Transaction;

@Stateless
@LocalBean
@RolesAllowed({"EMPLOYEE", "EBANKING", "ATM"})
public class TransactionManager {

    @PersistenceContext(unitName = "bank")
    EntityManager em;
    @Resource
    SessionContext sc;
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory cf;
    @Resource(mappedName = "jms/clearing")
    private Queue queue;

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
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCredit(credit);
        transaction.setDebit(debit);

        em.persist(transaction);
        
        try {
            sendTransactionToClearing(transaction);
        } catch (JMSException ex) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return transaction;
    }

    public List<Transaction> getTransactions() {
        TypedQuery t = em.createNamedQuery(Customer.getTransactionsByUser, Transaction.class);
        t.setParameter("user", sc.getCallerPrincipal().getName());
        return t.getResultList();
    }

    private void sendTransactionToClearing(Transaction transaction) throws JMSException {
        Connection con = cf.createConnection();
        Session session = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(queue);
        TextMessage msg = session.createTextMessage(
                transaction.getDebit() + ","
                + transaction.getCredit() + ","
                + transaction.getAmount());
        producer.send(msg);
        session.close();
        con.close();
    }
}
