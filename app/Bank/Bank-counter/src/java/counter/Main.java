package counter;

import javax.ejb.EJB;
import model.Account;
import model.Customer;
import service.BankServiceRemote;

public class Main {

    @EJB
    private static BankServiceRemote bankService;

    public static void main(String[] args) {
        Customer customer = bankService.createCustomer("Fritz Muster", "Fichtenweg 1, 3400 Burgdorf", "1234", "fritz");
        Account account = bankService.createAccount(customer, "Privatkonto");
    }
}
