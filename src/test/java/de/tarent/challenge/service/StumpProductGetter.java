package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import java.util.ArrayList;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * A Stump implementation of the IProductGetter for testing.
 *
 * @author Jan
 */
public class StumpProductGetter implements IProductGetter {

    public List<IProduct> Output;

    public StumpProductGetter() {
        this(new ArrayList<IProduct>());
    }

    public StumpProductGetter(List<IProduct> out) {
        Output = out;
    }

    @Override
    public List<IProduct> All() throws ServiceUnavailableException {
        return Output;
    }

    @Override
    public List<IProduct> ByName(String name) throws ServiceUnavailableException {
        return Output;
    }

    /**
     * Allows returns the first element of Output
     *
     * @param sku
     * @return
     * @throws ServiceUnavailableException
     */
    @Override
    public IProduct BySku(String sku) throws ServiceUnavailableException {
        if (Output.size() > 0) {
            return Output.get(0);
        }
        return null;
    }

}
