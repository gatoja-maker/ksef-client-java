package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link EuEntityPermissionsController}.
 */
@Generated
public class EuEntityPermissionsController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'euEntityPermissionsController'.
   */
  private static BeanInstanceSupplier<EuEntityPermissionsController> getEuEntityPermissionsControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EuEntityPermissionsController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new EuEntityPermissionsController(args.get(0)));
  }

  /**
   * Get the bean definition for 'euEntityPermissionsController'.
   */
  public static BeanDefinition getEuEntityPermissionsControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EuEntityPermissionsController.class);
    beanDefinition.setInstanceSupplier(getEuEntityPermissionsControllerInstanceSupplier());
    return beanDefinition;
  }
}
