package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link CertificateController}.
 */
@Generated
public class CertificateController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'certificateController'.
   */
  private static BeanInstanceSupplier<CertificateController> getCertificateControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CertificateController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new CertificateController(args.get(0)));
  }

  /**
   * Get the bean definition for 'certificateController'.
   */
  public static BeanDefinition getCertificateControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CertificateController.class);
    beanDefinition.setInstanceSupplier(getCertificateControllerInstanceSupplier());
    return beanDefinition;
  }
}
