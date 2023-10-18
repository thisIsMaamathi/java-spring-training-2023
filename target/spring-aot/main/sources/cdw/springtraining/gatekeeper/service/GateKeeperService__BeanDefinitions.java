package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GateKeeperService}.
 */
public class GateKeeperService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'gateKeeperService'.
   */
  private static BeanInstanceSupplier<GateKeeperService> getGateKeeperServiceInstanceSupplier() {
    return BeanInstanceSupplier.<GateKeeperService>forConstructor(GateKeeperRepository.class, ResidentRepository.class, BlacklistRepository.class, VisitorRepository.class, JwtTokenProvider.class, UserRepository.class)
            .withGenerator((registeredBean, args) -> new GateKeeperService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'gateKeeperService'.
   */
  public static BeanDefinition getGateKeeperServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GateKeeperService.class);
    beanDefinition.setInstanceSupplier(getGateKeeperServiceInstanceSupplier());
    return beanDefinition;
  }
}
