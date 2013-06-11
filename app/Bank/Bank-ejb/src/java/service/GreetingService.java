/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Simon
 */
@Stateless
@LocalBean
public class GreetingService {

  public String sayHello() {
      return "Hello World!";
  }
}
