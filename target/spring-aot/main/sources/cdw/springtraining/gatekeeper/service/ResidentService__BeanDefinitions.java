package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResidentService}.
 */
public class ResidentService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'residentService'.
   */
  private static BeanInstanceSupplier<ResidentService> getResidentServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ResidentService>forConstructor(ResidentRepository.class, VisitorRepository.class, GateKeeperRepository.class, BlacklistRepository.class)
            .withGenerator((registeredBean, args) -> new ResidentService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'residentService'.
   */
  public static BeanDefinition getResidentServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResidentService.class);
    beanDefinition.setInstanceSupplier(getResidentServiceInstanceSupplier());
    return beanDefinition;
  }
}
