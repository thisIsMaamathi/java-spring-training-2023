package org.springframework.boot.autoconfigure.mustache;

import com.samskivert.mustache.Mustache;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

/**
 * Bean definitions for {@link MustacheAutoConfiguration}.
 */
public class MustacheAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration'.
   */
  private static BeanInstanceSupplier<MustacheAutoConfiguration> getMustacheAutoConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MustacheAutoConfiguration>forConstructor(MustacheProperties.class, ApplicationContext.class)
            .withGenerator((registeredBean, args) -> new MustacheAutoConfiguration(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'mustacheAutoConfiguration'.
   */
  public static BeanDefinition getMustacheAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MustacheAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(getMustacheAutoConfigurationInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mustacheCompiler'.
   */
  private static BeanInstanceSupplier<Mustache.Compiler> getMustacheCompilerInstanceSupplier() {
    return BeanInstanceSupplier.<Mustache.Compiler>forFactoryMethod(MustacheAutoConfiguration.class, "mustacheCompiler", Mustache.TemplateLoader.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(MustacheAutoConfiguration.class).mustacheCompiler(args.get(0)));
  }

  /**
   * Get the bean definition for 'mustacheCompiler'.
   */
  public static BeanDefinition getMustacheCompilerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setTargetType(Mustache.Compiler.class);
    beanDefinition.setInstanceSupplier(getMustacheCompilerInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mustacheTemplateLoader'.
   */
  private static BeanInstanceSupplier<MustacheResourceTemplateLoader> getMustacheTemplateLoaderInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MustacheResourceTemplateLoader>forFactoryMethod(MustacheAutoConfiguration.class, "mustacheTemplateLoader")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MustacheAutoConfiguration.class).mustacheTemplateLoader());
  }

  /**
   * Get the bean definition for 'mustacheTemplateLoader'.
   */
  public static BeanDefinition getMustacheTemplateLoaderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setTargetType(MustacheResourceTemplateLoader.class);
    beanDefinition.setInstanceSupplier(getMustacheTemplateLoaderInstanceSupplier());
    return beanDefinition;
  }
}
