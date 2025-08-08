package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link SubUnitPermissionsController}.
 */
@Generated
public class SubUnitPermissionsController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'subUnitPermissionsController'.
   */
  private static BeanInstanceSupplier<SubUnitPermissionsController> getSubUnitPermissionsControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<SubUnitPermissionsController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new SubUnitPermissionsController(args.get(0)));
  }

  /**
   * Get the bean definition for 'subUnitPermissionsController'.
   */
  public static BeanDefinition getSubUnitPermissionsControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SubUnitPermissionsController.class);
    beanDefinition.setInstanceSupplier(getSubUnitPermissionsControllerInstanceSupplier());
    return beanDefinition;
  }
}
