package de.tarent.challenge.config;

import de.tarent.challenge.persistent.CartStorer;
import de.tarent.challenge.service.ICartSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A Factory allowing to get the CartServices between layers.
 *
 * @author Jan
 */
@Configuration
@ComponentScan(value = {"de.tarent.challenge.persitent"})
public class CartSetterConfiguration {

    /**
     * Returns a ICartSetter with access to a perstiant storage.
     *
     * @return
     */
    @Bean
    public static ICartSetter getICartSetter() {
        return new CartStorer();
    }
}
