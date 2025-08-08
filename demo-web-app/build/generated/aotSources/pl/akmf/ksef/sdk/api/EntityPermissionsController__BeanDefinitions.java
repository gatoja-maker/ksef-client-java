package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link EntityPermissionsController}.
 */
@Generated
public class EntityPermissionsController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'entityPermissionsController'.
   */
  private static BeanInstanceSupplier<EntityPermissionsController> getEntityPermissionsControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EntityPermissionsController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new EntityPermissionsController(args.get(0)));
  }

  /**
   * Get the bean definition for 'entityPermissionsController'.
   */
  public static BeanDefinition getEntityPermissionsControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EntityPermissionsController.class);
    beanDefinition.setInstanceSupplier(getEntityPermissionsControllerInstanceSupplier());
    return beanDefinition;
  }
}
