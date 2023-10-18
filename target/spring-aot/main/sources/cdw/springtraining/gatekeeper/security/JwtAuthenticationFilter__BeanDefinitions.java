package cdw.springtraining.gatekeeper.security;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JwtAuthenticationFilter}.
 */
public class JwtAuthenticationFilter__BeanDefinitions {
  /**
   * Get the bean definition for 'jwtAuthenticationFilter'.
   */
  public static BeanDefinition getJwtAuthenticationFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JwtAuthenticationFilter.class);
    InstanceSupplier<JwtAuthenticationFilter> instanceSupplier = InstanceSupplier.using(JwtAuthenticationFilter::new);
    instanceSupplier = instanceSupplier.andThen(JwtAuthenticationFilter__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
