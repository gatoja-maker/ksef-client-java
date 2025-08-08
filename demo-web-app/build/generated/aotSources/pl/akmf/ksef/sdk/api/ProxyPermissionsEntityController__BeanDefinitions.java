package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link ProxyPermissionsEntityController}.
 */
@Generated
public class ProxyPermissionsEntityController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'proxyPermissionsEntityController'.
   */
  private static BeanInstanceSupplier<ProxyPermissionsEntityController> getProxyPermissionsEntityControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ProxyPermissionsEntityController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new ProxyPermissionsEntityController(args.get(0)));
  }

  /**
   * Get the bean definition for 'proxyPermissionsEntityController'.
   */
  public static BeanDefinition getProxyPermissionsEntityControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProxyPermissionsEntityController.class);
    beanDefinition.setInstanceSupplier(getProxyPermissionsEntityControllerInstanceSupplier());
    return beanDefinition;
  }
}
