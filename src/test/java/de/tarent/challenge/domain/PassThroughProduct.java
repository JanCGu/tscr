package de.tarent.challenge.domain;

import java.util.Set;

/**
 * A basic IProduct object which passes the name, sku and eans through.
 * Used for testing
 * 
 * @author Jan
 */
public class PassThroughProduct implements IProduct{

    public String name;
    public String sku;
    public Set<String> eans;
    
    public PassThroughProduct(String name,String sku,Set<String> eans)
    {
        this.name = name;
        this.sku = sku;
        this.eans = eans;
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
