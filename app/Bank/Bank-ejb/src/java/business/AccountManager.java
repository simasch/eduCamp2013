package business;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
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
@RolesAllowed({"EMPLOYEE"})
public class AccountManager {

    @PersistenceContext(unitName = "bank")
    EntityManager em;
    @Resource
    SessionContext sc;

    public Customer createCustomer(String name, String address, String pin, String user) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPin(pin);
        customer.setUsername(user);
        em.persist(customer);
        return customer;
    }

    public Account createAccount(Customer customer, String description) {
        Account account = new Account();
        account.setDescription(description);
        account.setBalance(BigDecimal.TEN);
        em.persist(account);
        account.setIban("CH" + account.getId());

        customer = em.merge(customer);
        customer.getAccounts().add(account);
        return account;
    }

    @RolesAllowed({"EMPLOYEE", "EBANKING"})
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Account> getAccounts() {
        TypedQuery t = em.createNamedQuery(Customer.getAccountsByUser, Account.class);
        t.setParameter("user", sc.getCallerPrincipal().getName());
        return t.getResultList();
    }
}
