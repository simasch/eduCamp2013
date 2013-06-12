/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Simon
 */
@Logging
public class LoggingInterceptor {

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        Logger.getAnonymousLogger().log(Level.FINE, 
                ctx.getClass().getClass().getName() + " " + 
                ctx.getMethod().getName() + " " + 
                ctx.getParameters());
        return ctx.proceed();
    }
}
