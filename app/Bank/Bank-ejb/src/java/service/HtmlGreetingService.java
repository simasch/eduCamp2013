/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Simon
 */
@Html
@ApplicationScoped
@Logging
public class HtmlGreetingService implements GreetingService{

    @Override
    public String sayHello() {
        return "<p>Hello World!</p>";
    }
    
}
