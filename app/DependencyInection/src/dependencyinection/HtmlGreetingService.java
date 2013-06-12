/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencyinection;

/**
 *
 * @author Simon
 */
public class HtmlGreetingService implements GreetingService{

    @Override
    public String sayHello() {
        return "<p>Hello World!</p>";
    }
    
}
