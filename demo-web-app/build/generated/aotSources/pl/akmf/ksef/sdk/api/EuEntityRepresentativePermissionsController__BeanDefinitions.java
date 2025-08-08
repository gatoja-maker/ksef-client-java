package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link EuEntityRepresentativePermissionsController}.
 */
@Generated
public class EuEntityRepresentativePermissionsController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'euEntityRepresentativePermissionsController'.
   */
  private static BeanInstanceSupplier<EuEntityRepresentativePermissionsController> getEuEntityRepresentativePermissionsControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EuEntityRepresentativePermissionsController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new EuEntityRepresentativePermissionsController(args.get(0)));
  }

  /**
   * Get the bean definition for 'euEntityRepresentativePermissionsController'.
   */
  public static BeanDefinition getEuEntityRepresentativePermissionsControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EuEntityRepresentativePermissionsController.class);
    beanDefinition.setInstanceSupplier(getEuEntityRepresentativePermissionsControllerInstanceSupplier());
    return beanDefinition;
  }
}
