package cdw.springtraining.gatekeeper.controller;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link AuthController}.
 */
public class AuthController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static AuthController apply(RegisteredBean registeredBean, AuthController instance) {
    instance.authenticationService = AutowiredFieldValueResolver.forRequiredField("authenticationService").resolve(registeredBean);
    instance.jwtAuthenticationFilter = AutowiredFieldValueResolver.forRequiredField("jwtAuthenticationFilter").resolve(registeredBean);
    return instance;
  }
}
