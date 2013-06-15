package service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.SessionContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/q1", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Q1Mdb implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(Q1Mdb.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage tm = (TextMessage) message;
            LOGGER.info(tm.getText());
        } catch (JMSException ex) {
            Logger.getLogger(Q1Mdb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
