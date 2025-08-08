package pl.akmf.ksef.sdk.api;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;

/**
 * Bean definitions for {@link TokensController}.
 */
@Generated
public class TokensController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'tokensController'.
   */
  private static BeanInstanceSupplier<TokensController> getTokensControllerInstanceSupplier() {
    return BeanInstanceSupplier.<TokensController>forConstructor(DefaultKsefClient.class)
            .withGenerator((registeredBean, args) -> new TokensController(args.get(0)));
  }

  /**
   * Get the bean definition for 'tokensController'.
   */
  public static BeanDefinition getTokensControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TokensController.class);
    beanDefinition.setInstanceSupplier(getTokensControllerInstanceSupplier());
    return beanDefinition;
  }
}
