package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VisitorService}.
 */
public class VisitorService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'visitorService'.
   */
  private static BeanInstanceSupplier<VisitorService> getVisitorServiceInstanceSupplier() {
    return BeanInstanceSupplier.<VisitorService>forConstructor(VisitorRepository.class)
            .withGenerator((registeredBean, args) -> new VisitorService(args.get(0)));
  }

  /**
   * Get the bean definition for 'visitorService'.
   */
  public static BeanDefinition getVisitorServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VisitorService.class);
    beanDefinition.setInstanceSupplier(getVisitorServiceInstanceSupplier());
    return beanDefinition;
  }
}
