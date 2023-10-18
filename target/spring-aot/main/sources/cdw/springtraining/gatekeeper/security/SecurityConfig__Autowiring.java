package cdw.springtraining.gatekeeper.security;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link SecurityConfig}.
 */
public class SecurityConfig__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static SecurityConfig apply(RegisteredBean registeredBean, SecurityConfig instance) {
    instance.jwtAuthenticationFilter = AutowiredFieldValueResolver.forRequiredField("jwtAuthenticationFilter").resolve(registeredBean);
    return instance;
  }
}
