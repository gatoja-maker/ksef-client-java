package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link IndirectPermissionsEntityController}.
 */
@Generated
public class IndirectPermissionsEntityController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'indirectPermissionsEntityController'.
   */
  private static BeanInstanceSupplier<IndirectPermissionsEntityController> getIndirectPermissionsEntityControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<IndirectPermissionsEntityController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new IndirectPermissionsEntityController(args.get(0)));
  }

  /**
   * Get the bean definition for 'indirectPermissionsEntityController'.
   */
  public static BeanDefinition getIndirectPermissionsEntityControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(IndirectPermissionsEntityController.class);
    beanDefinition.setInstanceSupplier(getIndirectPermissionsEntityControllerInstanceSupplier());
    return beanDefinition;
  }
}
