package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link OnlineSessionController}.
 */
@Generated
public class OnlineSessionController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'onlineSessionController'.
   */
  private static BeanInstanceSupplier<OnlineSessionController> getOnlineSessionControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OnlineSessionController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new OnlineSessionController(args.get(0)));
  }

  /**
   * Get the bean definition for 'onlineSessionController'.
   */
  public static BeanDefinition getOnlineSessionControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OnlineSessionController.class);
    beanDefinition.setInstanceSupplier(getOnlineSessionControllerInstanceSupplier());
    return beanDefinition;
  }
}
