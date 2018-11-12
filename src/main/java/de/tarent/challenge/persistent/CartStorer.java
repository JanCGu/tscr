package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.service.ICartSetter;
import java.util.Set;
import java.util.stream.Collectors;
import javax.naming.ServiceUnavailableException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan
 */
@Transactional
public class CartStorer implements ICartSetter{

    @Autowired
    private ICartRepository cartRepository;
    
    /**
     * Adds a new cart to the database and updates an existing one.
     * @param toUpdate
     * @return is False if the operation failed.
     * @throws ServiceUnavailableException 
     */
    @Override
    public boolean Update(Set<ICart> toUpdate) throws ServiceUnavailableException {
        if(toUpdate==null || toUpdate.isEmpty())
            return true;
        
        return cartRepository.saveAll(convert(toUpdate)).size()==toUpdate.size();
    }

    /**
     * Deletes the cart from the database.
     * @param toDelete
     * @return is False if the operation failed.
     * @throws ServiceUnavailableException 
     */
    @Override
    public boolean Delete(Set<ICart> toDelete) throws ServiceUnavailableException {
        if(toDelete==null || toDelete.isEmpty())
            return true;
        
        cartRepository.deleteInBatch(convert(toDelete));
        return true;
    }
    
    private Set<CartDTO> convert(Set<ICart> toConvert)
    {
        return toConvert.stream().map(cart -> new CartDTO(cart)).collect(Collectors.toSet());
    }
    
}
