package dependencyinection;

import java.util.logging.Logger;

public class DependencyInection {

    public static void main(String[] args) {
        GreetingService greetingService = GreetingServiceFactory.createGreetingService();
        Logger.getAnonymousLogger().info(greetingService.sayHello());
    }
}
