package service;

import javax.ejb.Stateless;

@Stateless
public class BankService implements BankServiceRemote {

    @Override
    public String sayHello() {
        return "Hello Bank";
    }
}
