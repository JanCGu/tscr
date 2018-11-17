package de.tarent.challenge.config;

import de.tarent.challenge.persistent.ProductRetiver;
import de.tarent.challenge.service.IProductGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A Factory allowing to get the ProductServices between layers.
 *
 * @author Jan
 */
@Configuration
@ComponentScan(value = {"de.tarent.challenge.persistent"})
public class ProductGetterConfiguration {

    /**
     * Returns a IProductGetter with access to a perstitant storage.
     *
     * @return
     */
    @Bean
    public static IProductGetter getIProductGetter() {
        return new ProductRetiver();
    }
}
