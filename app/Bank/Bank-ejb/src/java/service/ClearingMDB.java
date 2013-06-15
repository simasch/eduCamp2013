package service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/clearing", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ClearingMDB implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(ClearingMDB.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage tm = (TextMessage) message;
            LOGGER.info(tm.getText());
        } catch (JMSException ex) {
            Logger.getLogger(ClearingMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
