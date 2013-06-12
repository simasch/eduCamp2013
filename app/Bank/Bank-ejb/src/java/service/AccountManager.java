package service;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;
import model.Customer;

@Stateless
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

    public List<Account> getAccounts(Long customerId) {
        TypedQuery t = em.createNamedQuery(Customer.findAllAccounts, Account.class);
        t.setParameter("id", customerId);
        return t.getResultList();
    }
}
