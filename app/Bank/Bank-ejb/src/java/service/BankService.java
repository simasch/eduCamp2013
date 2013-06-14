package service;

import business.AccountManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Account;
import model.Customer;

@Stateless
public class BankService implements BankServiceRemote {

    @EJB
    private AccountManager accountManager;

    @Override
    public Customer createCustomer(String name, String address, String pin, String user) {
        return accountManager.createCustomer(name, address, pin, user);
    }

    @Override
    public Account createAccount(Customer customer, String description) {
        return accountManager.createAccount(customer, description);
    }
}
