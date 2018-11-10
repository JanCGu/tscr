package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * Allows to get a IProduct.
 * @author Jan
 */
public interface IProductGet {
    /**
     * Retrives all IProducts.
     * @return 
     * @throws javax.naming.ServiceUnavailableException if the service of which it was request was not availablew.
     */
    public List<IProduct> All() throws ServiceUnavailableException;
    
    /**
     * Retrives the IProducts with the same name.
     * @param name
     * @return 
     * @throws javax.naming.ServiceUnavailableException if the service of which it was request was not availablew.
     */
    public List<IProduct> ByName(String name) throws ServiceUnavailableException;
    
    /**
     * Retrives the first occurance of the IProduct with the sku.
     * @param sku
     * @return 
     * @throws javax.naming.ServiceUnavailableException if the service of which it was request was not availablew.
     */
    public IProduct BySku(String sku) throws ServiceUnavailableException;
    
}
