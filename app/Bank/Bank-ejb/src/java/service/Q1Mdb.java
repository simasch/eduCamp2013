package service;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.SessionContext;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/q1", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Q1Mdb implements MessageListener {

    @Resource
    private SessionContext sc;

    @Override
    public void onMessage(Message message) {
        sc.setRollbackOnly();
    }
}
