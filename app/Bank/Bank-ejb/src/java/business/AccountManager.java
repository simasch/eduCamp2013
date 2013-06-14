package business;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;
import model.Customer;

@Stateful
@LocalBean
public class AccountManager {

    @PersistenceContext(unitName = "bank")
    EntityManager em;

    public Customer createCustomer(String name, String address, String pin) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPin(pin);
        em.persist(customer);
        return customer;
    }

    public Account createAccount(Customer customer, String description) {
        Account account = new Account();
        account.setDescription(description);
        account.setBalance(BigDecimal.TEN);
        em.persist(account);

        customer = em.merge(customer);
        customer.getAccounts().add(account);
        return account;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Account> getAccounts() {
        TypedQuery t = em.createNamedQuery(Account.findAll, Account.class);
        return t.getResultList();
    }
}
