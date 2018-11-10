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
     * @param provider
     */
    public GetProductService(IProductGet provider) {
        this.provider = provider;
    }

    @Override
    public List<IProduct> All() throws ServiceUnavailableException {
        if (provider == null)
            throw new ServiceUnavailableException("The provider service was not set!");
        return convertProducts(provider.All());
    }

    /**
     * Ensures that the List of IProducts which is passed in is actualy a
     * Domain.Product
     *
     * @param in
     * @return
     */
    private List<IProduct> convertProducts(List<IProduct> in) {
        List<IProduct> ret = new ArrayList<>();
        in.forEach(convert -> ret.add(new Product(convert)));
        return ret;
    }

    @Override
    public List<IProduct> ByName(String name) throws ServiceUnavailableException{    
        if (provider == null)
            throw new ServiceUnavailableException("The provider service was not set!");
        return convertProducts(provider.ByName(name));
    }

    @Override
    public IProduct BySku(String sku) throws ServiceUnavailableException{
        if (provider == null)
            throw new ServiceUnavailableException("The provider service was not set!");
        return new Product(provider.BySku(sku));
    }

}
