package cdw.springtraining.gatekeeper;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link GatekeeperApplication}.
 */
public class GatekeeperApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'gatekeeperApplication'.
   */
  public static BeanDefinition getGatekeeperApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GatekeeperApplication.class);
    beanDefinition.setTargetType(GatekeeperApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(GatekeeperApplication.class);
    beanDefinition.setInstanceSupplier(GatekeeperApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
