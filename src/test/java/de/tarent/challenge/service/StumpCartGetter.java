package de.tarent.challenge.service;

import de.tarent.challenge.domain.ICart;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * An Basisc implementation of the ICartGetter for testing.
 *
 * @author jan
 */
public class StumpCartGetter implements ICartGetter {

    public List<ICart> Output;

    public StumpCartGetter(List<ICart> out) {
        Output = out;
    }

    @Override
    public List<ICart> All() throws ServiceUnavailableException {
        return Output;
    }

    /**
     * Returns the first element of Output or null if it has no elements.
     *
     * @param id
     * @return
     * @throws ServiceUnavailableException
     */
    @Override
    public ICart ById(String id) throws ServiceUnavailableException {
        if (Output != null && Output.size() > 0) {
            return Output.get(0);
        }
        return null;
    }

}
