package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.service.VisitorService;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VisitorController}.
 */
public class VisitorController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'visitorController'.
   */
  private static BeanInstanceSupplier<VisitorController> getVisitorControllerInstanceSupplier() {
    return BeanInstanceSupplier.<VisitorController>forConstructor(VisitorService.class)
            .withGenerator((registeredBean, args) -> new VisitorController(args.get(0)));
  }

  /**
   * Get the bean definition for 'visitorController'.
   */
  public static BeanDefinition getVisitorControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VisitorController.class);
    beanDefinition.setInstanceSupplier(getVisitorControllerInstanceSupplier());
    return beanDefinition;
  }
}
