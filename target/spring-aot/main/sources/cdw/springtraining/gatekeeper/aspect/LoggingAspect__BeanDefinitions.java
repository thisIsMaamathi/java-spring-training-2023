package cdw.springtraining.gatekeeper.aspect;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LoggingAspect}.
 */
public class LoggingAspect__BeanDefinitions {
  /**
   * Get the bean definition for 'loggingAspect'.
   */
  public static BeanDefinition getLoggingAspectBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LoggingAspect.class);
    beanDefinition.setInstanceSupplier(LoggingAspect::new);
    return beanDefinition;
  }
}
