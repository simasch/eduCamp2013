package counter;

import java.util.logging.Logger;
import javax.ejb.EJB;
import service.BankServiceRemote;

public class Main {

    @EJB
    private static BankServiceRemote bankService;

    public static void main(String[] args) {
        Logger.getAnonymousLogger().info(bankService.sayHello());
    }
}
