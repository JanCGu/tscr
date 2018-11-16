package de.tarent.challenge.service;

import de.tarent.challenge.domain.ICart;
import java.util.Set;
import javax.naming.ServiceUnavailableException;

/**
 * Basic implementation of the ICartSetter for testing.
 *
 * @author jan
 */
public class StumpCartSetter implements ICartSetter {

    public boolean Outcome;
    public Set<ICart> Input;

    public StumpCartSetter(boolean out) {
        Outcome = out;
    }

    @Override
    public boolean Update(Set<ICart> toUpdate) throws ServiceUnavailableException {
        Input = toUpdate;
        return Outcome;
    }

    @Override
    public boolean Delete(Set<ICart> toDelete) throws ServiceUnavailableException {
        Input = toDelete;
        return Outcome;
    }

}
