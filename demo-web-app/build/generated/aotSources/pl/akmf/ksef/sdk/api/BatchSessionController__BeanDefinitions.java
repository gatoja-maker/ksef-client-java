package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link BatchSessionController}.
 */
@Generated
public class BatchSessionController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'batchSessionController'.
   */
  private static BeanInstanceSupplier<BatchSessionController> getBatchSessionControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<BatchSessionController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new BatchSessionController(args.get(0)));
  }

  /**
   * Get the bean definition for 'batchSessionController'.
   */
  public static BeanDefinition getBatchSessionControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BatchSessionController.class);
    beanDefinition.setInstanceSupplier(getBatchSessionControllerInstanceSupplier());
    return beanDefinition;
  }
}
