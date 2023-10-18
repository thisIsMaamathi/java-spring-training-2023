package cdw.springtraining.gatekeeper.service;

import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link AuthenticationService}.
 */
public class AuthenticationService__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static AuthenticationService apply(RegisteredBean registeredBean,
      AuthenticationService instance) {
    instance.approveRequestRepository = AutowiredFieldValueResolver.forRequiredField("approveRequestRepository").resolve(registeredBean);
    instance.jwtTokenProvider = AutowiredFieldValueResolver.forRequiredField("jwtTokenProvider").resolve(registeredBean);
    instance.authenticationManager = AutowiredFieldValueResolver.forRequiredField("authenticationManager").resolve(registeredBean);
    instance.tokenRepository = AutowiredFieldValueResolver.forRequiredField("tokenRepository").resolve(registeredBean);
    instance.jwtAuthenticationFilter = AutowiredFieldValueResolver.forRequiredField("jwtAuthenticationFilter").resolve(registeredBean);
    return instance;
  }
}
