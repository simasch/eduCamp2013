package atm;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;
import service.AtmService_Service;

public class Main {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/AtmService/AtmService?wsdl")
    private static AtmService_Service service;

    public static void main(String[] args) {
        BigDecimal balance = getBalance("CH101", "1234");

        Logger.getAnonymousLogger().log(Level.INFO, "Balance: {0}", balance);
    }

    private static BigDecimal getBalance(java.lang.String iban, java.lang.String pin) {
        service.AtmService port = service.getAtmServicePort();
        
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "atm");
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "1234");

        return port.getBalance(iban, pin);
    }
}
