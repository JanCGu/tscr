package de.tarent.challenge.service;

import de.tarent.challenge.domain.ICart;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * Allows to get a ICarts.
 * @author Jan
 */
public interface ICartGetter {
    /**
     * Retrives all ICarts.
     * @return 
     * @throws javax.naming.ServiceUnavailableException if the service of which it was request was not availablew.
     */
    public List<ICart> All() throws ServiceUnavailableException;
    
    /**
     * Retrives the first occurance of the ICarts with the id.
     * @param sku
     * @return 
     * @throws javax.naming.ServiceUnavailableException if the service of which it was request was not availablew.
     */
    public ICart ById(String id) throws ServiceUnavailableException;
    
}
