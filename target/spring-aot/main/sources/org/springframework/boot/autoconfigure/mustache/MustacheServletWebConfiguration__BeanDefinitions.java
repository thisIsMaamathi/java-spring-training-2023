package org.springframework.boot.autoconfigure.mustache;

import com.samskivert.mustache.Mustache;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;

/**
 * Bean definitions for {@link MustacheServletWebConfiguration}.
 */
public class MustacheServletWebConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'mustacheServletWebConfiguration'.
   */
  public static BeanDefinition getMustacheServletWebConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MustacheServletWebConfiguration.class);
    beanDefinition.setInstanceSupplier(MustacheServletWebConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mustacheViewResolver'.
   */
  private static BeanInstanceSupplier<MustacheViewResolver> getMustacheViewResolverInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MustacheViewResolver>forFactoryMethod(MustacheServletWebConfiguration.class, "mustacheViewResolver", Mustache.Compiler.class, MustacheProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(MustacheServletWebConfiguration.class).mustacheViewResolver(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'mustacheViewResolver'.
   */
  public static BeanDefinition getMustacheViewResolverBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setTargetType(MustacheViewResolver.class);
    beanDefinition.setInstanceSupplier(getMustacheViewResolverInstanceSupplier());
    return beanDefinition;
  }
}
