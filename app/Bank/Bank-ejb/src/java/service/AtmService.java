package service;

import business.AccountManager;
import java.math.BigDecimal;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@Stateless
@WebService(serviceName = "AtmService")
@RolesAllowed({"ATM"})
public class AtmService {

    @EJB
    AccountManager accountManager;

    @WebMethod(operationName = "getBalance")
    public BigDecimal getBalance(@WebParam(name = "iban") String iban,
            @WebParam(name = "pin") String pin) {
        return accountManager.getBalance(iban, pin);
    }
}
