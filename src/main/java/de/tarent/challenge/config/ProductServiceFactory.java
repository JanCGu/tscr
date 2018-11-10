package de.tarent.challenge.config;

import de.tarent.challenge.persistent.ProductRetiver;
import de.tarent.challenge.service.IProductGet;
import de.tarent.challenge.service.ProductService;

/**
 * A Factory allowing to get the ProductServices between layers.
 * 
 * @author Jan
 */
public class ProductServiceFactory {
    
    /**
     * Returns a IProductGet with access to the DB.
     * @return 
     */
    public static IProductGet GetDBProductService()
    {
        IProductGet dbprovider = new ProductRetiver();
        return new ProductService(dbprovider);
    }
}
