package de.tarent.challenge.display;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import java.util.ArrayList;
import java.util.List;
import javax.naming.ServiceUnavailableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.tarent.challenge.service.IProductGetter;
import de.tarent.challenge.service.IProductSetter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@Controller
@RequestMapping("/products")
public class ProductController implements IProductController {

    @Autowired
    private IProductGetter productGetter;
    @Autowired
    private IProductSetter productSetter;

    /**
     * Allows to directly set the autowired parameters.
     *
     * This declaration is at least needed for testing.
     *
     * @param productGetterService
     * @param productSetterService
     */
    public ProductController(IProductGetter productGetterService, IProductSetter productSetterService) {
        productGetter = productGetterService;
        productSetter = productSetterService;
    }

    protected ProductController() {
    }

    @GetMapping
    @Override
    public Iterable<ProductModel> retrieveProducts() throws ServiceUnavailableException {
        return convert(productGetter.All());

    }

    @GetMapping("/{sku}")
    @Override
    public ProductModel retrieveProductBySku(@PathVariable String sku) throws ServiceUnavailableException {
        IProduct ret = productGetter.BySku(sku);
        if (ret == null) {
            return null;
        }
        return new ProductModel(ret);
    }

    @PostMapping
    @Override
    public boolean updateProducts(@RequestBody Iterable<ProductModel> toUpdate) throws ServiceUnavailableException {
        if (toUpdate == null) {
            return true;
        }
        return productSetter.Update(convert(toUpdate));
    }

    @DeleteMapping
    @Override
    public boolean deleteProducts(@RequestBody Iterable<ProductModel> toDelete) throws ServiceUnavailableException {
        if (toDelete == null) {
            return true;
        }
        return productSetter.Delete(convert(toDelete));
    }

    /**
     * Converts an iterable to a set.
     *
     * @param toConvert
     * @return
     * @throws DuplicateKeyException if two productmodels in the iterable exist,
     * which are the same.
     */
    private Set<IProduct> convert(Iterable<ProductModel> toConvert) throws DuplicateKeyException {
        if (toConvert == null) {
            return null;
        }
        Set<IProduct> ret = new HashSet<>();
        for (ProductModel product : toConvert) {
            if (ret.contains(product)) {
                throw new DuplicateKeyException("Cant have to products by the same identifier: " + Product.getIdentifierName());
            }

            ret.add(product);
        }
        return ret;
    }

    private Iterable<ProductModel> convert(List<IProduct> toConvert) {
        if (toConvert == null) {
            return null;
        }
        ArrayList<ProductModel> ret = new ArrayList<>();
        toConvert.forEach(product -> ret.add(new ProductModel(product)));
        return ret;
    }

}
