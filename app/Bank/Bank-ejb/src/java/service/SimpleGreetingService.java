package service;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Simple
@Logging
public class SimpleGreetingService implements GreetingService {

    @Inject
    private Event<LoginEvent> event;
    
    @Override
    public String sayHello() {
        event.fire(new LoginEvent());
        return "Hello World";
    }
}
