package de.tarent.challenge.domain;

import java.util.Set;

/**
 * Allows to set sku,name and ean directly!
 * @author Jan
 */
public class MockProduct implements IProduct{

    public String sku;
    public String name;
    public Set<String> eans;
    
    public MockProduct(String sku, String name, Set<String> eans)
    {
        this.sku=sku;
        this.name=name;
        this.eans=eans;
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
    
}
