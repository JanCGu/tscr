package de.tarent.challenge.display;

import de.tarent.challenge.config.ProductServiceFactory;
import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import de.tarent.challenge.service.IProductGet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.ServiceUnavailableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    public ProductController() {
    }

    @GetMapping
    public Iterable<ProductModel> retrieveProducts() throws ServiceUnavailableException {
        IProductGet productService = ProductServiceFactory.GetDBProductService();

        return convertProducts(productService.All());

    }

    @GetMapping("/{sku}")
    public ProductModel retrieveProductBySku(@PathVariable String sku) throws ServiceUnavailableException {
        IProductGet productService = ProductServiceFactory.GetDBProductService();
        
        return new ProductModel(productService.BySku(sku));
    }

    private Iterable<ProductModel> convertProducts(List<IProduct> toConvert) {
        ArrayList<ProductModel> ret = new ArrayList<>();
        toConvert.forEach(product -> ret.add(new ProductModel(product)));
        return ret;
    }

}
