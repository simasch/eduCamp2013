/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Account;
import model.Customer;

/**
 *
 * @author Simon
 */
@Stateless
@LocalBean
public class AccountManager {

    @PersistenceContext(unitName = "bank")
    EntityManager em;

    public Customer createCustomer(String name) {
        Customer c = new Customer();
        c.setName(name);
        em.persist(c);
        return c;
    }

    public List<Account> getAccounts(Long customerId) {
        TypedQuery t = em.createNamedQuery(Customer.findAllAccounts, Account.class);
        t.setParameter("id", customerId);
        return t.getResultList();
    }
}
