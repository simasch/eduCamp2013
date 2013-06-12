/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencyinection;

/**
 *
 * @author Simon
 */
public class GreetingServiceFactory {

    public static GreetingService createGreetingService() {
        return new HtmlGreetingService();
    }
}
