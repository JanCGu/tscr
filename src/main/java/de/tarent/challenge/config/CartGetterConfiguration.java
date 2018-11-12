package de.tarent.challenge.config;

import de.tarent.challenge.persistent.CartRetiver;
import de.tarent.challenge.service.ICartGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A Factory allowing to get the CartService between layers.
 *
 * @author Jan
 */
@Configuration
@ComponentScan(value = {"de.tarent.challenge.persitent"})
public class CartGetterConfiguration {

    /**
     * Returns a ICartGetter with access to a perstitant storage.
     *
     * @return
     */
    @Bean
    public static ICartGetter getICartGetter() {
        return new CartRetiver();
    }
}
