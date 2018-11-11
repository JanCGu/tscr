/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tarent.challenge.persistent;

import de.tarent.challenge.domain.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the DTO Product in the persistence layer.
 *
 * @author Jan
 */
@SpringBootTest
public class ProductDTOTest {

    @Test
    public void createProductWithPropertiesTest() {
        ProductTest.testCreationOfProduct(ta -> new ProductDTO(ta.sku, ta.name, ta.eans,ta.price));
    }

    @Test
    public void createProductWithIProductTest() {
        ProductTest.testCreationOfProduct(ta -> new ProductDTO(ta.mockproduct));
    }

}
