/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.security.Identity;
import java.security.Principal;
import java.util.Map;
import java.util.Properties;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
import javax.ejb.SessionContext;
import javax.ejb.TimerService;
import javax.transaction.UserTransaction;
import javax.xml.rpc.handler.MessageContext;

/**
 *
 * @author Simon
 */
public class SessionContextProvider {

    public static SessionContext getSessionContext() {
        return new SessionContext() {
            @Override
            public EJBLocalObject getEJBLocalObject() throws IllegalStateException {
                return null;
            }

            @Override
            public EJBObject getEJBObject() throws IllegalStateException {
                return null;
            }

            @Override
            public MessageContext getMessageContext() throws IllegalStateException {
                return null;
            }

            @Override
            public <T> T getBusinessObject(Class<T> arg0) throws IllegalStateException {
                return null;
            }

            @Override
            public Class getInvokedBusinessInterface() throws IllegalStateException {
                return null;
            }

            @Override
            public boolean wasCancelCalled() throws IllegalStateException {
                return false;
            }

            @Override
            public EJBHome getEJBHome() throws IllegalStateException {
                return null;
            }

            @Override
            public EJBLocalHome getEJBLocalHome() throws IllegalStateException {
                return null;
            }

            @Override
            public Properties getEnvironment() {
                return null;
            }

            @Override
            public Identity getCallerIdentity() {
                return null;
            }

            @Override
            public Principal getCallerPrincipal() throws IllegalStateException {
                return new Principal() {
                    @Override
                    public String getName() {
                        return "petra";
                    }
                };
            }

            @Override
            public boolean isCallerInRole(Identity role) {
                return true;
            }

            @Override
            public boolean isCallerInRole(String roleName) throws IllegalStateException {
                return true;
            }

            @Override
            public UserTransaction getUserTransaction() throws IllegalStateException {
                return null;
            }

            @Override
            public void setRollbackOnly() throws IllegalStateException {
            }

            @Override
            public boolean getRollbackOnly() throws IllegalStateException {
                return false;
            }

            @Override
            public TimerService getTimerService() throws IllegalStateException {
                return null;
            }

            @Override
            public Object lookup(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public Map<String, Object> getContextData() {
                return null;
            }
        };
    }
}
