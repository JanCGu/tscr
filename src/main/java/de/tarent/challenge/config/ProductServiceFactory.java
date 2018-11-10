package de.tarent.challenge.config;

import de.tarent.challenge.persistent.ProductRetiver;
import de.tarent.challenge.persistent.ProductStorer;
import de.tarent.challenge.service.ProductGetter;
import de.tarent.challenge.service.IProductSetter;
import de.tarent.challenge.service.IProductGetter;
import de.tarent.challenge.service.ProductSetter;

/**
 * A Factory allowing to get the ProductServices between layers.
 * 
 * @author Jan
 */
public class ProductServiceFactory {
    
    /**
     * Returns a IProductGetter with access to a perstitant storage.
     * @return 
     */
    public static IProductGetter GetProductGetter()
    {
        IProductGetter dbprovider = new ProductRetiver();
        return new ProductGetter(dbprovider);
    }
    
    /**
     * Returns a IProductSetter with acces to a perstiant storage.
     * @return 
     */
    public static IProductSetter GetProductSetter(){
        IProductSetter dbprovider = new ProductStorer();
        return new ProductSetter(dbprovider);
        
    }
}
