package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.IProduct;
import java.util.List;
import de.tarent.challenge.service.IProductGetter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Allows to get Products form the database.
 *
 * @author Jan
 */
@Transactional
public class ProductRetiver implements IProductGetter {

    @Autowired
    private IProductRepository productRepository;

    /**
     * Returns all products from the database.
     *
     * @return
     */
    @Override
    public List<IProduct> All() {
        List<ProductDTO> ret = productRepository.findAll();
        if (ret.isEmpty()) {
            return null;
        }

        return ProductDTOConverter.convertProductDTO(ret);
    }

    /**
     * Returns a list of products from the database having the same name as
     * specified.
     *
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
     *
     * @param sku
     * @return The IProduct might be null if no matching Product was found.
     */
    @Override
    public IProduct BySku(String sku) {
        List<ProductDTO> rets = productRepository.findBySku(sku);
        if (rets.isEmpty()) {
            return null;
        }
        return rets.stream().findFirst().get();
    }

}
