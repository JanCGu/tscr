package de.tarent.challenge.service;

import de.tarent.challenge.domain.IProduct;
import de.tarent.challenge.domain.Product;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan
 */
public class ProductGetterTest {

    /**
     * Test of All method, of class ProductService.
     */
    @Test
    public void testAll()  throws Exception {
        System.out.println("All");
        MockProductGet mock = new MockProductGet();
        ProductGetter in = prepare(mock);
        assertEquals(mock.Output,in.All());

    }

    /**
     * Test of ByName method, of class ProductService.
     */
    @Test
    public void testByName() throws Exception {
        System.out.println("ByName");
        MockProductGet mock = new MockProductGet();
        ProductGetter in = prepare(mock);
        assertEquals(mock.Output,in.ByName("does not matter here"));

    }

    /**
     * Test of BySku method, of class ProductService.
     */
    @Test
    public void testBySku() throws Exception {
        System.out.println("BySku");
        MockProductGet mock = new MockProductGet();
        ProductGetter in = prepare(mock);

        IProduct skuProduct = in.BySku("does not matter here");
        assertEquals(mock.Output.get(0),skuProduct);
    }

    private ProductGetter prepare(MockProductGet mock) {
        Set<String> eans = new HashSet<>();
        eans.add("ean1");
        eans.add("ean2");
        mock.Output.add(new Product("sku1", "name1", eans));
        mock.Output.add(new Product("sku2", "name2", eans));

        return new ProductGetter(mock);
    }

}
