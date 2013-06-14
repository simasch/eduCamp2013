package service;

import javax.ejb.Remote;
import model.Account;
import model.Customer;

@Remote
public interface BankServiceRemote {
    
    Customer createCustomer(String name, String address, String pin);
    
    Account createAccount(Customer customer, String description);
}
