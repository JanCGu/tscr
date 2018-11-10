/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the Product domain.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductTest {

    /**
     * Tests the functionaly to create a domain Product through Strings.
     */
    @Test
    public void ProductCreateByStringTest() {
        testProduct(new Product(null, null, null), createAsserts());
    }

    private ArrayList<AssertProductTo> createAsserts() {
        ArrayList<AssertProductTo> toAssert = new ArrayList<>();
        toAssert.add(new AssertProductTo("sku", "name"));
        toAssert.add(new AssertProductTo("", ""));

        String keyboard = "qwertzuiopüasdfghjklöäyxcvbnm";
        keyboard += keyboard.toUpperCase();
        keyboard += "1234567890";
        keyboard += "!\"§$%&/()=?´`^°<>|,.-;:_+#*'~";
        return toAssert;
    }

    /**
     * Tests the functionality to create a domain Product by an IProduct.
     */
    @Test
    public void ProductCreateByIProductTest() {
        ArrayList<AssertProductTo> toAssert = createAsserts();
        toAssert.forEach(ta -> {
            PassThroughProduct ptp = new PassThroughProduct(
                    ta.name,
                    ta.sku,
                    ta.eans);
            ta.product = new Product(ptp);
        });

        IProduct nullIP = new Product(null, null, null);

        try
        {
            Product p = new Product(nullIP);
            assertTrue(true,"this should not work!");
        }
        catch(NullPointerException ex)
        {
            assertTrue(true);
        }
        
        testProduct(new Product(null, null, null), toAssert);
    }

    private void testProduct(Product nullProduct, ArrayList<AssertProductTo> additonal) {
        assertNullProduct(nullProduct);
        additonal.forEach(assertto -> assertto.assertProduct());
    }

    private void assertNullProduct(Product nullProduct) {
        assertNull(nullProduct.getSku());
        assertNull(nullProduct.getName());
        assertNull(nullProduct.getSku());
    }

    private void testProductCreation(String sku, String name, Set<String> eans) {
        Product p = new Product(sku, name, eans);

    }
}

/**
 * Helper class to create a Product based on the sku, name and eans.
 *
 * @author Jan
 */
class AssertProductTo {

    public Product product;
    public String sku;
    public String name;
    public Set<String> eans;
    public boolean expectedToFail;

    /**
     * Product will be created based on sku and name. The eans will be an empty
     * HashSet<String>.
     *
     * @param sku
     * @param name
     */
    public AssertProductTo(String sku, String name) {
        this(sku, name, new HashSet<>());
    }

    public AssertProductTo(String sku, String name, Set<String> eans) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        product = new Product(sku, name, eans);
    }

    /**
     * Compares the stored product to the stored sku,name and eans. If
     * expectedToFail is true the comparision must be false in order for the
     * assert to work.
     */
    public void assertProduct() {
        boolean equal = true;

        equal &= product.getSku().equals(sku);
        equal &= product.getName().equals(name);
        equal &= product.getEans() == eans;

        if (expectedToFail) {
            assertTrue(equal);
        } else {
            assertFalse(equal);
        }

    }
}
