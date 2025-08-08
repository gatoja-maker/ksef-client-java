package pl.akmf.ksef.sdk;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link TestClientApplication}.
 */
@Generated
public class TestClientApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'testClientApplication'.
   */
  public static BeanDefinition getTestClientApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TestClientApplication.class);
    beanDefinition.setTargetType(TestClientApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(TestClientApplication.class);
    beanDefinition.setInstanceSupplier(TestClientApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
