package service;

import javax.inject.Inject;

public class Greeter {

    @Inject
    private GreetingService greetingService;
    
    public void foo() {
        greetingService.sayHello();
    }
}
