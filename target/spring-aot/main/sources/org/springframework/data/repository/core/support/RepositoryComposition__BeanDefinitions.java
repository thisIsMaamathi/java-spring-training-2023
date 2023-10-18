package org.springframework.data.repository.core.support;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.ResolvableType;

/**
 * Bean definitions for {@link RepositoryComposition}.
 */
public class RepositoryComposition__BeanDefinitions {
  /**
   * Bean definitions for {@link RepositoryComposition.RepositoryFragments}.
   */
  public static class RepositoryFragments {
    /**
     * Get the bean instance supplier for 'jpa.GateKeeperRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.VisitorRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier1(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition1() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier1());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.RoleRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier2(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition2() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier2());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.ResidentRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier3(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition3() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier3());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.BlacklistRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier4(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition4() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier4());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.UserRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier5(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition5() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier5());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.ApproveRequestRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier6(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition6() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier6());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'jpa.TokenRepository.fragments#0'.
     */
    private static BeanInstanceSupplier<RepositoryFragmentsFactoryBean> getFragmentsInstanceSupplier7(
        ) {
      return BeanInstanceSupplier.<RepositoryFragmentsFactoryBean>forConstructor(List.class)
              .withGenerator((registeredBean, args) -> new RepositoryFragmentsFactoryBean(args.get(0)));
    }

    /**
     * Get the bean definition for 'fragments#0'.
     */
    public static BeanDefinition getFragmentsBeanDefinition7() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(RepositoryFragmentsFactoryBean.class);
      beanDefinition.setTargetType(ResolvableType.forClass(RepositoryFragmentsFactoryBean.class));
      beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
      beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, Collections.emptyList());
      beanDefinition.setInstanceSupplier(getFragmentsInstanceSupplier7());
      return beanDefinition;
    }
  }
}
