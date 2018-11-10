
package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * This interace allows the implementation to update and delete a list of products.
 * 
 * Basicly this interface representsthe create, update and delete part of a repository.
 * Therefore it has no save method!
 * @author Jan
 */
public interface IProductSetter {
    
    /**
     * Updates exsinting products in the list xor creates them if they are new 
     * in perspective to the implementation.
     * @param toSave List of iproducts which are created or updated.
     * @return true if the object is successfully updated.
     */
    public boolean Update(List<IProduct> toUpdate) throws ServiceUnavailableException;
    
    /**
     * Deletes the products in the list.
     * @param toDelete
     * @return Returns true if the operation was sucessful.
     */
    public boolean Delete(List<IProduct> toDelete) throws ServiceUnavailableException;
}
