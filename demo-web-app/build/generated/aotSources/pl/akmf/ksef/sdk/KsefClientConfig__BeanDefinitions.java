package pl.akmf.ksef.sdk;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.core.env.Environment;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link KsefClientConfig}.
 */
@Generated
public class KsefClientConfig__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'ksefClientConfig'.
   */
  private static BeanInstanceSupplier<KsefClientConfig> getKsefClientConfigInstanceSupplier() {
    return BeanInstanceSupplier.<KsefClientConfig>forConstructor(Environment.class)
            .withGenerator((registeredBean, args) -> new KsefClientConfig$$SpringCGLIB$$0(args.get(0)));
  }

  /**
   * Get the bean definition for 'ksefClientConfig'.
   */
  public static BeanDefinition getKsefClientConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KsefClientConfig.class);
    beanDefinition.setTargetType(KsefClientConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(KsefClientConfig.class);
    beanDefinition.setInstanceSupplier(getKsefClientConfigInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'init'.
   */
  private static BeanInstanceSupplier<DefaultKsefClient> getInitInstanceSupplier() {
    return BeanInstanceSupplier.<DefaultKsefClient>forFactoryMethod(KsefClientConfig.class, "init")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(KsefClientConfig.class).init());
  }

  /**
   * Get the bean definition for 'init'.
   */
  public static BeanDefinition getInitBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultKsefClient.class);
    beanDefinition.setInstanceSupplier(getInitInstanceSupplier());
    return beanDefinition;
  }
}
