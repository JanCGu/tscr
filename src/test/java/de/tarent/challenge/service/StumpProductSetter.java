package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import java.util.Set;
import javax.naming.ServiceUnavailableException;

/**
 * An stump implementation of IProductSetter for testing.
 *
 * @author Jan
 */
public class StumpProductSetter implements IProductSetter {

    public boolean Output;
    public Set<IProduct> toUpdate;
    public Set<IProduct> toDelete;

    public StumpProductSetter() {
        this(true);
    }

    public StumpProductSetter(boolean out) {
        Output = out;
    }

    /**
     * Returns Output and stores toUpdate in the class variable.
     *
     * @param toUpdate
     * @return
     * @throws ServiceUnavailableException
     */
    @Override
    public boolean Update(Set<IProduct> toUpdate) throws ServiceUnavailableException {
        this.toUpdate = toUpdate;
        return Output;
    }

    /**
     * Returns Output and stores toDelete in the class variable.
     *
     * @param toDelete
     * @return
     * @throws ServiceUnavailableException
     */
    @Override
    public boolean Delete(Set<IProduct> toDelete) throws ServiceUnavailableException {
        this.toDelete = toDelete;
        return Output;
    }

}
