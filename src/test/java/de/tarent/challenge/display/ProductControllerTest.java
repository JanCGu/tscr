/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.display;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.MockProduct;
import de.tarent.challenge.service.IProductGetter;
import de.tarent.challenge.service.IProductSetter;
import de.tarent.challenge.service.StumpProductGetter;
import de.tarent.challenge.service.StumpProductSetter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.naming.ServiceUnavailableException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.dao.DuplicateKeyException;

/**
 * ProductControllerTest has the unit tests. The integration tests are stored in
 * ProductControllerIntegration.
 *
 * @author Jan
 */
public class ProductControllerTest {

    public ProductControllerTest() {
    }

    /**
     * Ensures that the SKU of the product is unique. The same sku has to be in
     * the same create statement otherwise it will just be updated.
     *
     * @throws javax.naming.ServiceUnavailableException
     */
    @Test
    public void ensureUniqueSKU() throws ServiceUnavailableException {
        ArrayList<ProductModel> list = new ArrayList<>();
        ProductModel test = getTestProduct();
        list.add(test);
        list.add(getTestProduct("DiffrentNameSameSKU"));
        try {
            ProductController pc = getIsolatedProductController();
            pc.updateProducts(list);
            assertTrue("Excpected to fail because of dublicate key(sku)", false);
        } catch (DuplicateKeyException ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void deleteTest() throws ServiceUnavailableException {
        Iterable<ProductModel> toDelete = getProducts();
        StumpProductGetter get = new StumpProductGetter(new ArrayList<>());
        StumpProductSetter set = new StumpProductSetter(true);
        ProductController pc = new ProductController(get, set);
        
        assertTrue("An null or empty operation is true",pc.deleteProducts(null));
        assertTrue("Operation should be successfull!",pc.deleteProducts(toDelete));
        assertTrue("The right models were tried to be deleted",set.toDelete.size()==1 && set.toUpdate==null);
    }
    @Test
    public void updateTest() throws ServiceUnavailableException {
        Iterable<ProductModel> toUpdate = getProducts();
        StumpProductGetter get = new StumpProductGetter(new ArrayList<>());
        StumpProductSetter set = new StumpProductSetter(true);
        ProductController pc = new ProductController(get, set);
        
        assertTrue("An null or empty operation is true",pc.updateProducts(null));
        assertTrue("Operation should be successfull!",pc.updateProducts(toUpdate));
        assertTrue("The right models were tried to be deleted",set.toUpdate.size()==1 && set.toDelete==null);
    }
    

    private Iterable<ProductModel> getProducts() {
        ArrayList<ProductModel> ret = new ArrayList<>();
        Set<String> eans = new HashSet<>();
        eans.add("E1");
        ret.add(new ProductModel("sku", "name", eans));
        
        return ret;
    }

    private ProductController getIsolatedProductController() {
        return getIsolatedProductController(new ArrayList<>(), true);
    }

    private ProductController getIsolatedProductController(List<IProduct> outList, boolean sucess) {
        IProductGetter get = new StumpProductGetter(outList);
        IProductSetter set = new StumpProductSetter(sucess);
        return new ProductController(get, set);
    }

    public static ProductModel getTestProduct() {
        return getTestProduct("TestProduct");
    }

    public static ProductModel getTestProduct(String name) {
        Set<String> eans = new HashSet<>();
        eans.add("1234567890123");
        return new ProductModel("testSKU", name, eans);
    }
}
