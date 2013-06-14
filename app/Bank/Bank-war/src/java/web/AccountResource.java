package web;

import business.AccountManager;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import model.Account;

@Path("accounts")
@RequestScoped
public class AccountResource {

    @EJB
    AccountManager accountManager;

    @GET
    @Produces("application/json")
    public List<Account> get() {
        return accountManager.getAccounts();
    }
}
