package service;

import business.AccountManager;
import business.AsyncService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import model.Account;
import model.Customer;

@Stateless
@RolesAllowed("EMPLOYEE")
public class BankService implements BankServiceRemote {

    @EJB
    private AccountManager accountManager;
    @EJB
    private AsyncService asyncService;
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory cf;
    @Resource(mappedName = "jms/q1")
    private Queue queue;

    @Override
    public Customer createCustomer(String name, String address, String pin, String user) {
        return accountManager.createCustomer(name, address, pin, user);
    }

    @Override
    public Account createAccount(Customer customer, String description) {
        return accountManager.createAccount(customer, description);
    }

    public String sayHello() throws InterruptedException, ExecutionException {
        Future<String> future = asyncService.sayHelloAsync();

        // do something in the meanwhile

        return future.get();
    }

    public void sendAMessage() {
        try {
            Connection con = cf.createConnection();
            Session session = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            TextMessage msg = session.createTextMessage("Hallo Welt");
            producer.send(msg);

        } catch (JMSException ex) {
            Logger.getLogger(BankService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
