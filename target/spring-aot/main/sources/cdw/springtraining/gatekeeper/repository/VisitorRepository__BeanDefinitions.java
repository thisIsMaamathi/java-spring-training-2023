package cdw.springtraining.gatekeeper.repository;

import java.lang.Class;
import java.lang.Object;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean__Autowiring1;
import org.springframework.data.repository.query.QueryLookupStrategy;

/**
 * Bean definitions for {@link VisitorRepository}.
 */
public class VisitorRepository__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'visitorRepository'.
   */
  private static BeanInstanceSupplier<JpaRepositoryFactoryBean> getVisitorRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JpaRepositoryFactoryBean>forConstructor(Class.class)
            .withGenerator((registeredBean, args) -> new JpaRepositoryFactoryBean(args.get(0)));
  }

  /**
   * Get the bean definition for 'visitorRepository'.
   */
  public static BeanDefinition getVisitorRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JpaRepositoryFactoryBean.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(JpaRepositoryFactoryBean.class, VisitorRepository.class, Object.class, Object.class));
    beanDefinition.setLazyInit(false);
    beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, "cdw.springtraining.gatekeeper.repository.VisitorRepository");
    beanDefinition.getPropertyValues().addPropertyValue("queryLookupStrategyKey", QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND);
    beanDefinition.getPropertyValues().addPropertyValue("lazyInit", false);
    beanDefinition.getPropertyValues().addPropertyValue("namedQueries", new RuntimeBeanReference("jpa.named-queries#1"));
    beanDefinition.getPropertyValues().addPropertyValue("repositoryFragments", new RuntimeBeanReference("jpa.VisitorRepository.fragments#0"));
    beanDefinition.getPropertyValues().addPropertyValue("transactionManager", "transactionManager");
    beanDefinition.getPropertyValues().addPropertyValue("entityManager", new RuntimeBeanReference("jpaSharedEM_entityManagerFactory"));
    beanDefinition.getPropertyValues().addPropertyValue("escapeCharacter", '\\');
    beanDefinition.getPropertyValues().addPropertyValue("mappingContext", new RuntimeBeanReference("jpaMappingContext"));
    beanDefinition.getPropertyValues().addPropertyValue("enableDefaultTransactions", true);
    InstanceSupplier<JpaRepositoryFactoryBean> instanceSupplier = getVisitorRepositoryInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(JpaRepositoryFactoryBean__Autowiring1::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
