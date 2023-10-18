package cdw.springtraining.gatekeeper.service;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AuthenticationService}.
 */
public class AuthenticationService__BeanDefinitions {
  /**
   * Get the bean definition for 'authenticationService'.
   */
  public static BeanDefinition getAuthenticationServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthenticationService.class);
    InstanceSupplier<AuthenticationService> instanceSupplier = InstanceSupplier.using(AuthenticationService::new);
    instanceSupplier = instanceSupplier.andThen(AuthenticationService__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
