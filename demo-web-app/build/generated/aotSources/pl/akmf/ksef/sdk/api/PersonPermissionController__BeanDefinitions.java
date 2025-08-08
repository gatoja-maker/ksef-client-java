package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link PersonPermissionController}.
 */
@Generated
public class PersonPermissionController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'personPermissionController'.
   */
  private static BeanInstanceSupplier<PersonPermissionController> getPersonPermissionControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<PersonPermissionController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new PersonPermissionController(args.get(0)));
  }

  /**
   * Get the bean definition for 'personPermissionController'.
   */
  public static BeanDefinition getPersonPermissionControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PersonPermissionController.class);
    beanDefinition.setInstanceSupplier(getPersonPermissionControllerInstanceSupplier());
    return beanDefinition;
  }
}
