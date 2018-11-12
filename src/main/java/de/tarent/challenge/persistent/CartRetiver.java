package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.ICart;
import de.tarent.challenge.service.ICartGetter;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.ServiceUnavailableException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan
 */
@Transactional
public class CartRetiver implements ICartGetter{
    
    @Autowired
    private ICartRepository cartRepository;

    /**
     * Returns all Carts stored in the database.
     * @return
     * @throws ServiceUnavailableException 
     */
    @Override
    public List<ICart> All() throws ServiceUnavailableException {
        return cartRepository.findAll().stream().map(cart -> (ICart)cart).collect(Collectors.toList());
    }

    /**
     * Returns the first instance of the cart by the id. This should allways be the same
     * as the id is the unique identifier.
     * @param id
     * @return
     * @throws ServiceUnavailableException 
     */
    @Override
    public ICart ById(String id) throws ServiceUnavailableException {
        return cartRepository.findById(id).stream().findFirst().orElseGet(null);
    }
    
}
