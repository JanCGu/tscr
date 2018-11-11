package de.tarent.challenge.config;

import de.tarent.challenge.persistent.ProductStorer;
import de.tarent.challenge.service.IProductSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A Factory allowing to get the ProductServices between layers.
 *
 * @author Jan
 */
@Configuration
@ComponentScan(value = {"de.tarent.challenge.persitent"})
public class ProductSetterConfiguration {

    /**
     * Returns a IProductSetter with access to a perstiant storage.
     *
     * @return
     */
    @Bean
    public static IProductSetter getIProductSetter() {
        return new ProductStorer();
    }
}
