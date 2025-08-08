package pl.akmf.ksef.sdk;

import com.fasterxml.jackson.databind.Module;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link JacksonConfig}.
 */
@Generated
public class JacksonConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'jacksonConfig'.
   */
  public static BeanDefinition getJacksonConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonConfig.class);
    beanDefinition.setTargetType(JacksonConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(JacksonConfig.class);
    beanDefinition.setInstanceSupplier(JacksonConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'jsonNullableModule'.
   */
  private static BeanInstanceSupplier<Module> getJsonNullableModuleInstanceSupplier() {
    return BeanInstanceSupplier.<Module>forFactoryMethod(JacksonConfig.class, "jsonNullableModule")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(JacksonConfig.class).jsonNullableModule());
  }

  /**
   * Get the bean definition for 'jsonNullableModule'.
   */
  public static BeanDefinition getJsonNullableModuleBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Module.class);
    beanDefinition.setInstanceSupplier(getJsonNullableModuleInstanceSupplier());
    return beanDefinition;
  }
}
