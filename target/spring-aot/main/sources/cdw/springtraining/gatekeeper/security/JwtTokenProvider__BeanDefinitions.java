package cdw.springtraining.gatekeeper.security;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JwtTokenProvider}.
 */
public class JwtTokenProvider__BeanDefinitions {
  /**
   * Get the bean definition for 'jwtTokenProvider'.
   */
  public static BeanDefinition getJwtTokenProviderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JwtTokenProvider.class);
    InstanceSupplier<JwtTokenProvider> instanceSupplier = InstanceSupplier.using(JwtTokenProvider::new);
    instanceSupplier = instanceSupplier.andThen(JwtTokenProvider__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
