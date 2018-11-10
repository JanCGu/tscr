package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.IProduct;
import java.util.List;
import de.tarent.challenge.service.IProductGetter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan
 */
public class ProductRetiver implements IProductGetter {

    @Autowired
    private IProductRepository productRepository;

    public ProductRetiver() {

    }

    /**
     * Returns all products from the database.
     * @return 
     */
    @Override
    public List<IProduct> All() {
        return ProductDTOConverter.convertProductDTO(productRepository.findAll());
    }

    /**
     * Returns a list of products from the database having the same name as specified.
     * @param name
     * @return 
     */
    @Override
    public List<IProduct> ByName(String name) {
        return ProductDTOConverter.convertProductDTO(productRepository.findByName(name));
    }

    /**
     * Returns the first occurance of the product in the database with the sku.
     * As the sku is a unique identifier there should only be one!
     * @param sku
     * @return 
     */
    @Override
    public IProduct BySku(String sku) {
        return productRepository.findBySku(sku).stream().findFirst().get();
    }

}
