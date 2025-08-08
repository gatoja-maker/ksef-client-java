package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link InvoicesController}.
 */
@Generated
public class InvoicesController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'invoicesController'.
   */
  private static BeanInstanceSupplier<InvoicesController> getInvoicesControllerInstanceSupplier() {
    return BeanInstanceSupplier.<InvoicesController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new InvoicesController(args.get(0)));
  }

  /**
   * Get the bean definition for 'invoicesController'.
   */
  public static BeanDefinition getInvoicesControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InvoicesController.class);
    beanDefinition.setInstanceSupplier(getInvoicesControllerInstanceSupplier());
    return beanDefinition;
  }
}
