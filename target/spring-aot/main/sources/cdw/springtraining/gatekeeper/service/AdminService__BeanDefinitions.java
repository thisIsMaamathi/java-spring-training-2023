package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.repository.ApproveRequestRepository;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.RoleRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AdminService}.
 */
public class AdminService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'adminService'.
   */
  private static BeanInstanceSupplier<AdminService> getAdminServiceInstanceSupplier() {
    return BeanInstanceSupplier.<AdminService>forConstructor(ResidentRepository.class, BlacklistRepository.class, GateKeeperRepository.class, ApproveRequestRepository.class, UserRepository.class, RoleRepository.class)
            .withGenerator((registeredBean, args) -> new AdminService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'adminService'.
   */
  public static BeanDefinition getAdminServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AdminService.class);
    beanDefinition.setInstanceSupplier(getAdminServiceInstanceSupplier());
    return beanDefinition;
  }
}
