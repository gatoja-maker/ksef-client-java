package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ActiveSessionController}.
 */
@Generated
public class ActiveSessionController__BeanDefinitions {
  /**
   * Get the bean definition for 'activeSessionController'.
   */
  public static BeanDefinition getActiveSessionControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ActiveSessionController.class);
    beanDefinition.setInstanceSupplier(ActiveSessionController::new);
    return beanDefinition;
  }
}
