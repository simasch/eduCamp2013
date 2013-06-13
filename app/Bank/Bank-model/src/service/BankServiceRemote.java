package service;

import javax.ejb.Remote;

@Remote
public interface BankServiceRemote {
    
    String sayHello();
}
