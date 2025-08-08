package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link AuthController}.
 */
@Generated
public class AuthController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'authController'.
   */
  private static BeanInstanceSupplier<AuthController> getAuthControllerInstanceSupplier() {
    return BeanInstanceSupplier.<AuthController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new AuthController(args.get(0)));
  }

  /**
   * Get the bean definition for 'authController'.
   */
  public static BeanDefinition getAuthControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthController.class);
    beanDefinition.setInstanceSupplier(getAuthControllerInstanceSupplier());
    return beanDefinition;
  }
}
