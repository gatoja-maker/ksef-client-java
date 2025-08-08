package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link QrCodeController}.
 */
@Generated
public class QrCodeController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'qrCodeController'.
   */
  private static BeanInstanceSupplier<QrCodeController> getQrCodeControllerInstanceSupplier() {
    return BeanInstanceSupplier.<QrCodeController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new QrCodeController(args.get(0)));
  }

  /**
   * Get the bean definition for 'qrCodeController'.
   */
  public static BeanDefinition getQrCodeControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(QrCodeController.class);
    beanDefinition.setInstanceSupplier(getQrCodeControllerInstanceSupplier());
    return beanDefinition;
  }
}
