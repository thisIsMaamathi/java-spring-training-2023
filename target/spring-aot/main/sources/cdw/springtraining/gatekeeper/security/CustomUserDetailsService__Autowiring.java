package cdw.springtraining.gatekeeper.security;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CustomUserDetailsService}.
 */
public class CustomUserDetailsService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CustomUserDetailsService apply(RegisteredBean registeredBean,
      CustomUserDetailsService instance) {
    instance.userRepository = AutowiredFieldValueResolver.forRequiredField("userRepository").resolve(registeredBean);
    return instance;
  }
}
