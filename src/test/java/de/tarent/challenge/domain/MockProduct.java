package de.tarent.challenge.domain;

import java.util.Set;
import org.javamoney.moneta.Money;

/**
 * Allows to set sku,name and ean directly!
 * @author Jan
 */
public class MockProduct implements IProduct{

    public String sku;
    public String name;
    public Set<String> eans;
    public Money price;
    
    public MockProduct(String sku, String name, Set<String> eans, Money price)
    {
        this.sku=sku;
        this.name=name;
        this.eans=eans;
        this.price = price;
    }
    
    @Override
    public Set<String> getEans() {
        return eans;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSku() {
        return sku;
    }

    @Override
    public Money getPrice() {
        return price;
    }
    
}
