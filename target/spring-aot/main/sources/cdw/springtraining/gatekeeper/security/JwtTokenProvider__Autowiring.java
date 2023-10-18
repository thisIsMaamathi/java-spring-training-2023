package cdw.springtraining.gatekeeper.security;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link JwtTokenProvider}.
 */
public class JwtTokenProvider__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static JwtTokenProvider apply(RegisteredBean registeredBean, JwtTokenProvider instance) {
    AutowiredFieldValueResolver.forRequiredField("jwtSecret").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("jwtExpirationDate").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
