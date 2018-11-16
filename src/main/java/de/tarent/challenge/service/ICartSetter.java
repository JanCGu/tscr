package de.tarent.challenge.service;

import de.tarent.challenge.domain.ICart;
import java.util.Set;
import javax.naming.ServiceUnavailableException;

/**
 * This interace allows the implementation to update and delete a list of carts.
 *
 * Basicly this interface representsthe create, update and delete part of a
 * repository. Therefore it has no save method!
 *
 * @author Jan
 */
public interface ICartSetter {

    /**
     * Updates exsinting carts in the list xor creates them if they are new in
     * perspective to the implementation.
     *
     * @param toUpdate Set of iproducts which are created or updated.
     * @return true if the object is successfully updated.
     */
    public boolean Update(Set<ICart> toUpdate) throws ServiceUnavailableException;

    /**
     * Deletes the carts in the list.
     *
     * @param toDelete
     * @return Returns true if the operation was sucessful.
     */
    public boolean Delete(Set<ICart> toDelete) throws ServiceUnavailableException;
}
