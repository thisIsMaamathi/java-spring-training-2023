package org.springframework.boot.autoconfigure.mustache;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MustacheProperties}.
 */
public class MustacheProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'mustacheProperties'.
   */
  public static BeanDefinition getMustachePropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MustacheProperties.class);
    beanDefinition.setInstanceSupplier(MustacheProperties::new);
    return beanDefinition;
  }
}
