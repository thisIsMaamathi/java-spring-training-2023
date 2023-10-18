package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.service.ResidentService;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResidentController}.
 */
public class ResidentController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'residentController'.
   */
  private static BeanInstanceSupplier<ResidentController> getResidentControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ResidentController>forConstructor(ResidentService.class)
            .withGenerator((registeredBean, args) -> new ResidentController(args.get(0)));
  }

  /**
   * Get the bean definition for 'residentController'.
   */
  public static BeanDefinition getResidentControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResidentController.class);
    beanDefinition.setInstanceSupplier(getResidentControllerInstanceSupplier());
    return beanDefinition;
  }
}
