package de.tarent.challenge.display;

import de.tarent.challenge.config.ProductServiceFactory;
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
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/products")
public class ProductController {

    public ProductController() {
    }

    /**
     * Returns an iterable "list" of ProducModels, which represents the products.
     * Gets all products available.
     * @return
     * @throws ServiceUnavailableException 
     */
    @GetMapping
    public Iterable<ProductModel> retrieveProducts() throws ServiceUnavailableException {
        IProductGetter productService = ProductServiceFactory.GetProductGetter();

        return convert(productService.All());

    }

    /**
     * Returns a ProductModel of a Product by its sku. This product comes from the
     * persitance layer.
     * @param sku
     * @return
     * @throws ServiceUnavailableException 
     */
    @GetMapping("/{sku}")
    public ProductModel retrieveProductBySku(@PathVariable String sku) throws ServiceUnavailableException {
        IProductGetter productService = ProductServiceFactory.GetProductGetter();
        
        return new ProductModel(productService.BySku(sku));
    }
    
    /**
     * the products in the toUpdate are stored in the perstiance layer.
     * This means that they are eather created, if they do not exits or are updated
     * if they do exist.
     * 
     * @param toUpdate
     * @return Returns true if the update was sucessful.
     * @throws ServiceUnavailableException 
     */
    @PostMapping
    public boolean updateProducts(@RequestBody Iterable<ProductModel> toUpdate) throws ServiceUnavailableException{
         IProductSetter productService = ProductServiceFactory.GetProductSetter();
         return productService.Update(convert(toUpdate));
    }
    
    /**
     * Deletes the products from the storage. If the product does not exist,it does nothing.
     * @param toUpdate
     * @return True if the operation was sucessfull.
     * @throws ServiceUnavailableException 
     */
    @DeleteMapping
    public boolean deleteProducts(@RequestBody Iterable<ProductModel> toUpdate) throws ServiceUnavailableException{
         IProductSetter productService = ProductServiceFactory.GetProductSetter();
         return productService.Delete(convert(toUpdate));
    }
    
    private List<IProduct> convert(Iterable<ProductModel> toConvert){
        List<IProduct> ret = new ArrayList<>();
        toConvert.forEach(ret::add);
        return ret;
    }   

    private Iterable<ProductModel> convert(List<IProduct> toConvert) {
        ArrayList<ProductModel> ret = new ArrayList<>();
        toConvert.forEach(product -> ret.add(new ProductModel(product)));
        return ret;
    }

}
