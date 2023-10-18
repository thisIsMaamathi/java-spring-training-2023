package cdw.springtraining.gatekeeper.security;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link JwtAuthenticationFilter}.
 */
public class JwtAuthenticationFilter__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static JwtAuthenticationFilter apply(RegisteredBean registeredBean,
      JwtAuthenticationFilter instance) {
    AutowiredFieldValueResolver.forRequiredField("jwtTokenProvider").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("userDetailsService").resolveAndSet(registeredBean, instance);
    instance.tokenRepository = AutowiredFieldValueResolver.forRequiredField("tokenRepository").resolve(registeredBean);
    return instance;
  }
}
