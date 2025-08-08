package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link SearchPermissionTestEndpoint}.
 */
@Generated
public class SearchPermissionTestEndpoint__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'searchPermissionTestEndpoint'.
   */
  private static BeanInstanceSupplier<SearchPermissionTestEndpoint> getSearchPermissionTestEndpointInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<SearchPermissionTestEndpoint>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new SearchPermissionTestEndpoint(args.get(0)));
  }

  /**
   * Get the bean definition for 'searchPermissionTestEndpoint'.
   */
  public static BeanDefinition getSearchPermissionTestEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SearchPermissionTestEndpoint.class);
    beanDefinition.setInstanceSupplier(getSearchPermissionTestEndpointInstanceSupplier());
    return beanDefinition;
  }
}
