package de.tarent.challenge.domain;

import java.util.Set;
import org.javamoney.moneta.Money;

/**
 * Represents an Interface to Product.
 * A Product represents an enty of something which can be bought in a shop.
 * @author Jan
 */
public interface IProduct {

    Set<String> getEans();

    String getName();

    String getSku();
    
    Money getPrice();
    
    void setAvailable(boolean availability);
    
    boolean getAvailable();
    
}
