package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link OperationStatusController}.
 */
@Generated
public class OperationStatusController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'operationStatusController'.
   */
  private static BeanInstanceSupplier<OperationStatusController> getOperationStatusControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OperationStatusController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new OperationStatusController(args.get(0)));
  }

  /**
   * Get the bean definition for 'operationStatusController'.
   */
  public static BeanDefinition getOperationStatusControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OperationStatusController.class);
    beanDefinition.setInstanceSupplier(getOperationStatusControllerInstanceSupplier());
    return beanDefinition;
  }
}
