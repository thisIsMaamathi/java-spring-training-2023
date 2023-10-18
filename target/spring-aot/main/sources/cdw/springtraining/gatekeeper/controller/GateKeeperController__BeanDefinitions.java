package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.service.GateKeeperService;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GateKeeperController}.
 */
public class GateKeeperController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'gateKeeperController'.
   */
  private static BeanInstanceSupplier<GateKeeperController> getGateKeeperControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<GateKeeperController>forConstructor(GateKeeperService.class)
            .withGenerator((registeredBean, args) -> new GateKeeperController(args.get(0)));
  }

  /**
   * Get the bean definition for 'gateKeeperController'.
   */
  public static BeanDefinition getGateKeeperControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GateKeeperController.class);
    beanDefinition.setInstanceSupplier(getGateKeeperControllerInstanceSupplier());
    return beanDefinition;
  }
}
