package counter;

import java.util.logging.Logger;
import javax.ejb.EJB;
import model.Account;
import model.Customer;
import service.BankServiceRemote;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
    @EJB
    private static BankServiceRemote bankService;

    public static void main(String[] args) {
        try {
            Customer customer = bankService.createCustomer("Fritz Muster", "Fichtenweg 1, 3400 Burgdorf", "1234", "fritz");
            Account account = bankService.createAccount(customer, "Privatkonto");

            LOGGER.info("Customer and account created");
        } catch (Exception e) {
            LOGGER.severe("User not authorized");
        }
    }
}
