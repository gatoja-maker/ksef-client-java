package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.client.interfaces.KSeFClient;

/**
 * Bean definitions for {@link SessionController}.
 */
@Generated
public class SessionController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'sessionController'.
   */
  private static BeanInstanceSupplier<SessionController> getSessionControllerInstanceSupplier() {
    return BeanInstanceSupplier.<SessionController>forConstructor(KSeFClient.class)
            .withGenerator((registeredBean, args) -> new SessionController(args.get(0)));
  }

  /**
   * Get the bean definition for 'sessionController'.
   */
  public static BeanDefinition getSessionControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionController.class);
    beanDefinition.setInstanceSupplier(getSessionControllerInstanceSupplier());
    return beanDefinition;
  }
}
