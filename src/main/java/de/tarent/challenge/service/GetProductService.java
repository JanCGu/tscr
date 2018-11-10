package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import java.util.ArrayList;
import java.util.List;
import javax.naming.ServiceUnavailableException;
import org.springframework.stereotype.Service;

/**
 * This Service is basicly a proxy and a adapter to the persitance layer for
 * getting a IProduct.
 *
 * @author Jan
 */
@Service
public class GetProductService implements IProductGet {

    private final IProductGet provider;

    /**
     * Initalises the Productservice with a provider where to retive the
     * information.
     *
     * Use the Factory in config for inatialisation!
     *
     * @param provider
     */
    public GetProductService(IProductGet provider) {
        this.provider = provider;
    }

    /**
     * Gets all Products from the persitance layer and returns a domain conform list.
     * @return
     * @throws ServiceUnavailableException 
     */
    @Override
    public List<IProduct> All() throws ServiceUnavailableException {
        testProvider();
        return DomainProductConverter.Convert(provider.All());
    }

    /**
     * Returns a product domain conform list of the products with the name from the 
     * persitance layer.
     * @param name
     * @return
     * @throws ServiceUnavailableException 
     */
    @Override
    public List<IProduct> ByName(String name) throws ServiceUnavailableException {
        testProvider();
        return DomainProductConverter.Convert(provider.ByName(name));
    }

    /**
     * Gets the product by sku from the persitance layer and returns a 
     * product from the domain.
     * @param sku
     * @return
     * @throws ServiceUnavailableException 
     */
    @Override
    public IProduct BySku(String sku) throws ServiceUnavailableException {
        testProvider();
        return new Product(provider.BySku(sku));
    }

    private void testProvider() throws ServiceUnavailableException {
        if (provider == null) {
            throw new ServiceUnavailableException("The provider service was not set!");
        }
    }

}
