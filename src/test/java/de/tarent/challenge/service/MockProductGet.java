package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import java.util.ArrayList;
import java.util.List;
import javax.naming.ServiceUnavailableException;

/**
 * A Mock of IProductGet for testing.
 *
 * Set Output bevore use.
 *
 * @author Jan
 */
public class MockProductGet implements IProductGet {

    public List<IProduct> Output;
    
    public MockProductGet()
    {
        Output = new ArrayList<>();
    }

    @Override
    public List<IProduct> All() throws ServiceUnavailableException {
        return Output;
    }

    @Override
    public List<IProduct> ByName(String name) throws ServiceUnavailableException {
        return Output;
    }

    @Override
    public IProduct BySku(String sku) throws ServiceUnavailableException {
        if (Output.size() > 0) {
            return Output.get(0);
        }
        return null;
    }

}
