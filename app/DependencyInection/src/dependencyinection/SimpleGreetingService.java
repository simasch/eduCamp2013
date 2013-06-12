/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencyinection;

/**
 *
 * @author Simon
 */
public class SimpleGreetingService implements GreetingService {
    
    @Override
    public String sayHello() {
        return "Hello World!";
    }
    
}
